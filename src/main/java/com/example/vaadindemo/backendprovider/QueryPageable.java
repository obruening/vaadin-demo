package com.example.vaadindemo.backendprovider;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;

public class QueryPageable<T, V> implements Pageable {

	private Query<T, V> query;

	public QueryPageable(Query<T, V> query) {
		this.query = query;
	}

	@Override
	public Pageable previousOrFirst() {

		return null;
	}

	@Override
	public Pageable next() {

		return null;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sort getSort() {

		List<QuerySortOrder> sortOrders = query.getSortOrders();
		if ((sortOrders != null) && (!sortOrders.isEmpty())) {
			QuerySortOrder querySortOrder = sortOrders.get(0);
			SortDirection direction = querySortOrder.getDirection();
			String column = querySortOrder.getSorted();
			Sort sort = new Sort(direction == SortDirection.ASCENDING ? Sort.Direction.ASC : Sort.Direction.DESC, column);
			return sort;

		} else {
			Sort defaultSort = new Sort(Sort.Direction.ASC, "id");
			return defaultSort;
		}
	}

	@Override
	public int getPageSize() {
		return query.getLimit();
	}

	@Override
	public int getPageNumber() {
		return 0;
	}

	@Override
	public long getOffset() {
		return query.getOffset();
	}

	@Override
	public Pageable first() {
		return null;
	}

}
