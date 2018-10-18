
package com.tft.framework.common.bean;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Service 分页支持
 * @author not attributable
 * @version 1.0
 */
public class TftPage {

	/**
	 * 当前页中存放的记录
	 */
	private Object data;

	private Integer pageSize;

	private Integer pageIndex;//规定从0开始

	private int pageCount;

	private long recordCount;

	public int getPageCount() {
		return pageCount;
	}

	public TftPage() {

	}
	public TftPage(Object data){
		this.data = data;
	}

	public TftPage(int pageSize, int pageIndex, int recordCount) {

		this.setPageSize(pageSize);
		this.setPageIndex(pageIndex);
		this.setRecordCount(recordCount);
	}

	public void setPageCount(int pageCount) {

		this.pageCount = pageCount;
	}

	public Integer getPageIndex() {

		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {

		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {

		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}

	public long getRecordCount() {

		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public void setData(Object data) {

		this.data = data;
	}

	public Object getData() {

		return data;
	}

	public String toString() {

		String strOut = "\n Page Count:" + this.getPageCount();
		strOut = strOut + "\n Page Size:" + this.getPageSize();
		strOut = strOut + "\n Current page:" + this.getPageIndex();
		strOut = strOut + "\n Record Count:" + this.getRecordCount();
		return strOut;
	}

	public PageRequest toQueryPage(TftSort sort){
		return PageRequest.of(pageIndex,pageSize,sort.toSort());
	}
	public PageRequest toQueryPage(){
		return PageRequest.of(pageIndex,pageSize);
	}

	public TftPage setPageInfo(Page pageInfo){
		this.data = pageInfo.getContent();
		this.pageSize = pageInfo.getSize();
		this.pageIndex = pageInfo.getNumber();
		this.pageCount = pageInfo.getTotalPages();
		this.recordCount = pageInfo.getTotalElements();
		return this;
	}
}
