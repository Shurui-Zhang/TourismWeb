package cn.tedu.store.bean;

import java.io.Serializable;
import java.util.List;

public class PageBean{

	 	private Integer currentPage;
	    private int pageSize;
	    private int totalPage;
	    private List<?> dataList;
	    private int star;
		public Integer getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}
		public int getPageSize() {
			return pageSize;
		}
		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}
		public int getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public List<?> getDataList() {
			return dataList;
		}
		public void setDataList(List<?> dataList) {
			this.dataList = dataList;
		}
		public int getStar() {
			return star;
		}
		public void setStar(int star) {
			this.star = star;
		}
	    
	    

}
