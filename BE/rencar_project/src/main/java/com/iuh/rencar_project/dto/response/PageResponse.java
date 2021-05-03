/**
 * @author trant
 * @created_date Apr 22, 2021
 * @version 1.0
 */
package com.iuh.rencar_project.dto.response;

import java.io.Serializable;
import java.util.List;

public class PageResponse<T> implements Serializable {

	private static final long serialVersionUID = -2697443791427055523L;

	private List<T> content;

	private int totalPage;

	private int currentPage;

	public PageResponse(List<T> content, int totalPage, int currentPage) {
		super();
		this.content = content;
		this.totalPage = totalPage;
		this.currentPage = currentPage + 1;
	}

	public PageResponse() {
		super();
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
