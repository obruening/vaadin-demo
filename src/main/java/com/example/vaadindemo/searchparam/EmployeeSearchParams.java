package com.example.vaadindemo.searchparam;

public class EmployeeSearchParams extends PersonSearchParams {
	
	public EmployeeSearchParams() {};
	
	public EmployeeSearchParams(PersonSearchParams personSearchParams) {
		super(personSearchParams);
	}

	public EmployeeSearchParams(EmployeeSearchParams searchParams) {
		super(searchParams);
		this.setJob(searchParams.getJob());
	}

	private String job;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
}
