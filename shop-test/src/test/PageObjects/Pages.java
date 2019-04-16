
public class Pages extends BasePage{

	
	String search = "fulltext";
	String searchReset = "#container > div > div.admin__data-grid-header > div:nth-child(1) > div.admin__data-grid-filters-current._show > div.admin__current-filters-list-wrap > ul > li > button";
	
	String filtersBar = "action-default";
	String filtersFromIDFilter = "fromIDFilter";
	String filtersToIDFilter = "toIDFilter";
	String filterReset = "(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[2]/following::button[1]";
	
	String columnsSubmit = "(.//*[normalize-space(text()) and normalize-space(.)='Submit'])[1]/following::button[1]";
	String columnsTitle = "(.//*[normalize-space(text()) and normalize-space(.)='Title'])[1]/following::label[1]";
	String columnsColumns = "(.//*[normalize-space(text()) and normalize-space(.)='Columns'])[1]/following::div[4]";
	
	String sortID = "sortID";
	String sortLayout = "sortLayout";
	String sortTitle = "sortTitle";
	String sortURLKey = "sortURLKey";
	
	public void searchBar(query) {
		waitAndFind(id(search)).click();
		waitAndFind(id(search)).sendKeys(String query);
	}
	
	public void searchReset() {
		waitAndFind(cssSelector(searchReset)).click();
	}
}
