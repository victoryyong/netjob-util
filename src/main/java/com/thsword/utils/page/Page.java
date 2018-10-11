package com.thsword.utils.page;

public class Page implements java.io.Serializable {

	private static final long serialVersionUID = -9912011199996705L;

    //升序
    public static final String DIR_TYPE_ASC="asc";
    //降序
    public static final String DIR_TYPE_DESC="desc";
    
	/** 每页默认10条*/
	protected int pageSize = 10;
	/** 当前页*/
	protected int currentPage = 1;
	/** 总页数*/
	protected int totalPages = 0;
	/** 总数据数 */
	protected int totalRows = 0;
	/** 页的起始行数 */
	protected int pageStartRow = 0;
	/** 页显示数据的终止行数 */
	protected int pageEndRow = 0;
	/** 是否分页 */
	protected boolean pagination = false;
	/** 是否有上一页 */
	boolean hasPreviousPage = false;
	/** 是否有下下一页 */
	boolean hasNextPage = false;
	/** 上一页号 */
	private int prePage;
	/** 下一页号 */
	private int nextPage;
	/** sort排序*/
	private String sort="";
	/** 排序方式*/
	private String dir="";

	public Page() {
	}

	public Page(final int pageSize) {
		// 设置参数
		this.init(0, pageSize, 1,"","");
	}

	public Page(final int pageSize,final String sort,final String dir) {
		// 设置参数
		this.init(0, pageSize, 1,sort,dir);
	}
	
	public Page(final int currentPage,final int pageSize,final String sort,final String dir) {
		// 设置参数
		this.init(currentPage, pageSize, 1,sort,dir);
	}
	
	public Page(final int currentPage, final int pageSize) {
		// 设置参数
		setParameter(0, pageSize, currentPage,sort,dir);
	}

	public Page(final int currentPage, final int pageSize, final int totalRows,final String sort,final String dir) {
		// 设置参数
		setParameter(totalRows, pageSize, currentPage,sort,dir);
	}

	/**
	 * 初始化分页参数
	 * 
	 * @param totalRows总行数
	 * @param pageSize页大小
	 */
	public void init(final int totalRows, final int pageSize,final String sort,final String dir) {
		// 设置参数
		setParameter(totalRows, pageSize, 1,sort,dir);
	}

	/**
	 * 初始化分页参参数
	 * 
	 * @param totalRows总行数
	 * @param pageSize页大小
	 * @param currentPage当前页
	 */
	public void init(final int totalRows, final int pageSize,
			final int currentPage,final String sort,final String dir) {
		// 设置参数
		setParameter(totalRows, pageSize, currentPage,sort,dir);
	}

	public Page getInstance(Page page) {
		// 设置参数
		page.setParameter(page.getTotalRows(), page.getPageSize(),
				page.getCurrentPage(),page.getSort(),page.getDir());
		return page;
	}
	/**
	 * 设置分页参数
	 * 
	 * @param totalRows总记录数
	 * @param pageSize页大小
	 * @param currentPage当前页
	 */
	private void setParameter(final int totalRows, final int pageSize,
			final int currentPage,final String sort,final String dir) {
		//按sort排序
		this.sort=sort;
		//排序方式
		this.dir=dir;
		// 当前页
		this.currentPage = currentPage;
		// 页大小
		this.pageSize = pageSize;
		// 总记录数
		this.totalRows = totalRows;
		// 计算总页数
		if ((totalRows % pageSize) == 0) {
			this.totalPages = totalRows / pageSize;
		} else {
			this.totalPages = totalRows / pageSize + 1;
		}
		// 计算页行数信数
		if (this.currentPage * this.pageSize < this.totalRows) { // 判断是否为最后一行
			this.pageEndRow = this.currentPage * this.pageSize;
			this.pageStartRow = this.pageEndRow - this.pageSize;
		} else {
			this.pageEndRow = this.totalRows;
			this.pageStartRow = this.pageSize * (this.totalPages - 1);
		}
		// 是否分页
		this.pagination = true;
		// 是否有下页
		if (this.currentPage < this.totalPages) {
			this.hasNextPage = true;
			// 下一页号
			this.nextPage = this.currentPage + 1;
		} else {
			this.hasNextPage = false;
			// 下一页号
			this.nextPage = this.currentPage;
		}
		// 是否有上一页
		if (1 < this.currentPage) {
			this.hasPreviousPage = true;
			// 上一页号
			this.prePage = this.currentPage - 1;
		} else {
			this.hasPreviousPage = false;
			// 上一页号
			this.prePage = this.currentPage;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public boolean isPagination() {
		return pagination;
	}

	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
