package com.example.vaadindemo.util;

import com.vaadin.ui.Grid;

public class Util {
	
	public static <T> Grid<T> configGrid(Grid<T> grid) {
		grid.setWidth("800px");
		return grid;
	}

}
