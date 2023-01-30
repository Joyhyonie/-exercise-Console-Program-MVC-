package com.joyhyonie.common;

public class SearchCriteria { // 검색 기준

	private String condition; 	// 검색 조건
	private String value; 		// 검색하려는 단어
	
	public SearchCriteria() {}
	
	public SearchCriteria(String value) {
		super();
		this.value = value;
	}

	public SearchCriteria(String condition, String value) {
		super();
		this.condition = condition;
		this.value = value;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SearchCriteria [condition=" + condition + ", value=" + value + "]";
	}
}
