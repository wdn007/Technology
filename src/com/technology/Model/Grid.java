package com.technology.Model;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private Long total = 0L;
	private List rows = new ArrayList();

	public Grid() {
		super();
	}

	public Grid(Long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
