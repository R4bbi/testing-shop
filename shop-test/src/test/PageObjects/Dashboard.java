
public class Dashboard extends BasePage {
	
	String contentNaviBtn = "//*[@id=\\\"menu-magento-backend-content\\\"]";
	String contentPagesBtn = "Pages";
	String contentBlocksBtn = "Blocks";

	
	public void goToContent() {
		waitAndFind(xpath(contentNaviBtn)).click();
	
	}
	
	public void goToPages() {
		waitAndFind(linkText(contentPagesBtn)).click();
	}
	
	public void goToBlocks() {
		waitAndFind(linkText(contentBlocksBtn)).click();
	}

}
