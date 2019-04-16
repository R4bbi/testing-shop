
public class Pages {

	
	String search = "fulltext";
	
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
	
}
