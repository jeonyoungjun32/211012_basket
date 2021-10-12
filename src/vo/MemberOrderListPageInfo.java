package vo;
//주문 내역 리스트 페이지뷰 만들기위한 vo
public class MemberOrderListPageInfo {
	private int page;
	private int maxPage;
	private int startPage;
	private int endPage;
	private int orderListCount;	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getOrderListCount() {
		return orderListCount;
	}
	public void setOrderListCount(int orderListCount) {
		this.orderListCount = orderListCount;
	}
	
}
