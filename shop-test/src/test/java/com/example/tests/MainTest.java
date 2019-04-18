package com.example.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  //Locators
  
  String loginInputId = "username";
  String passwdInputId = "login";
  String signInBtn = "//*[@id=\"login-form\"]/fieldset/div[3]/div[1]/button";
  
  String contentNaviBtn = "//*[@id=\\\"menu-magento-backend-content\\\"]";
  String contentPagesBtn = "Pages";
  String contentBlocksBtn = "Blocks";
  
  String search = "fulltext";
  String searchReset = "#container > div > div.admin__data-grid-header > div:nth-child(1) > div.admin__data-grid-filters-current._show > div.admin__current-filters-list-wrap > ul > li > button";
	
  String filtersBar = "action-default";
  String filtersFromIDFilter = "page_id[from]";
  String filtersToIDFilter = "page_id[to]";
  String filterAccept = "(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[2]/following::button[1]";
	
  String columnsSubmit = "(.//*[normalize-space(text()) and normalize-space(.)='Submit'])[1]/following::button[1]";
  String columnsTitle = "(.//*[normalize-space(text()) and normalize-space(.)='Title'])[1]/following::label[1]";
  String columnsColumns = "(.//*[normalize-space(text()) and normalize-space(.)='Columns'])[1]/following::div[4]";
	
  String sortID = "//*[@id=\"container\"]/div/div[4]/table/thead/tr/th[2]";
  String sortLayout = "//*[@id=\"container\"]/div/div[4]/table/thead/tr/th[5]";
  String sortTitle = "//*[@id=\"container\"]/div/div[4]/table/thead/tr/th[3]";
  String sortURLKey = "//*[@id=\"container\"]/div/div[4]/table/thead/tr/th[4]";
  
  //WaitForElement Method
  
  public WebElement waitAndFind(By by) {
      //System.out.println("Szukam po " + by.toString());
      try {
          Thread.sleep(500);
      } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }

      WebDriverWait wait = new WebDriverWait(driver, 15);
      WebElement elementToBeFound = wait.until(ExpectedConditions.elementToBeClickable(by));
      //jak znajdzie to zaczekam pó³ sekundy (czasem skrypt jest szybszy ni¿ przegl¹darka)
      return elementToBeFound;
  }
  
  //TestMethods
  
  public void loginAs(String login, String pass) {
		
		waitAndFind(By.id(loginInputId)).sendKeys(login);
		waitAndFind(By.id(passwdInputId)).sendKeys(pass);
		waitAndFind(By.xpath(signInBtn)).click();
  }
  public void goToContent() {
		waitAndFind(By.xpath("//*[@id=\"menu-magento-backend-content\"]")).click();
	
  }
	
  public void goToPages() throws InterruptedException {
		waitAndFind(By.linkText(contentPagesBtn)).click();
		Thread.sleep(9000);
  }
	
  public void goToBlocks() throws InterruptedException {
		waitAndFind(By.linkText(contentBlocksBtn)).click();
		Thread.sleep(5000);
  }
  public void searchBar(String query) {
		waitAndFind(By.id(search)).click();
		waitAndFind(By.id(search)).sendKeys(query);
		waitAndFind(By.id(search)).sendKeys(Keys.ENTER);
  }
	
  public void searchReset() throws InterruptedException {
	    Thread.sleep(2000);
		waitAndFind(By.cssSelector(searchReset)).click();
  }
  
  public void filtering(String from, String to) {
	  	waitAndFind(By.className(filtersBar)).click();
	  	waitAndFind(By.name(filtersFromIDFilter)).click();
	  	waitAndFind(By.name(filtersFromIDFilter)).sendKeys(from);
	  	waitAndFind(By.name(filtersToIDFilter)).click();
	  	waitAndFind(By.name(filtersToIDFilter)).sendKeys(to);
  }
  
  public void filteringAccept() {
  		waitAndFind(By.xpath(filterAccept)).click();
  }
  
  public void sortByID() throws InterruptedException {
	  waitAndFind(By.xpath(sortID)).click();
	  Thread.sleep(1000);
  }
  
  public void sortByLayout() throws InterruptedException {
	  waitAndFind(By.xpath(sortLayout)).click();
	  Thread.sleep(1000);
  }
  
  public void sortByTitle() throws InterruptedException {
	  waitAndFind(By.xpath(sortTitle)).click();
	  Thread.sleep(1000);
  }
  
  public void sortByURLKey() throws InterruptedException {
	  waitAndFind(By.xpath(sortURLKey)).click();
	  Thread.sleep(1000);
  }
  
  public void columnStart() {
	  waitAndFind(By.xpath(columnsSubmit)).click();
	  waitAndFind(By.xpath(columnsTitle)).click();
	  waitAndFind(By.xpath(columnsColumns)).click();
  }
  
  public void columnChoose() throws InterruptedException {
	  List<WebElement> elements = driver.findElements(By.tagName("th"));
	    System.out.println("liczba kolumn: " +elements.size());
	    
	    for (int i = 1, j = elements.size()-1; i <= 9; i++, j--) {
	    	driver.findElement(By.id(Integer.toString(i))).click();
	    	elements.remove(0);
	    	System.out.println("liczba kolumn: " +elements.size());
	    	assertEquals(j, elements.size());
	    	
	    	Thread.sleep(500);
	    }
  }
  
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void allStepsTest() throws Exception {
    driver.get("http://localhost/magento/admin/");
    loginAs("kony", "qwerty12345");
    
    goToContent();
    goToPages();
    searchBar("Home");
    searchReset();
    
    goToContent();
    goToBlocks();
    searchBar("Men");
    searchReset();
    
    goToContent();
    goToPages();
    filtering("1", "3");
    filteringAccept();
    searchReset();
    
    sortByID();
    sortByLayout();
    sortByTitle();
    sortByURLKey();
    
    columnStart();
    columnChoose();
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
