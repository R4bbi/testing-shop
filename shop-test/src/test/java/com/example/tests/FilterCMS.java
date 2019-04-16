package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FilterCMS {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  
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
  
  String filterBar = "//*[@id=\"container\"]/div/div[2]/div[1]/div[3]/div";
  String fromIDFilter = "page_id[from]";
  String toIDFilter = "page_id[to]";
  
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testFilterCMS() throws Exception {
	driver.get("http://localhost/magento/admin/");
	driver.findElement(By.id("username")).clear();
	driver.findElement(By.id("username")).sendKeys("kony");
	driver.findElement(By.id("login")).clear();
	driver.findElement(By.id("login")).sendKeys("qwerty12345");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("//*[@id=\"menu-magento-backend-content\"]")).click();
    driver.findElement(By.linkText("Pages")).click();
    Thread.sleep(8000);
    waitAndFind(By.className("action-default")).click();
    waitAndFind(By.name(fromIDFilter)).click();
    waitAndFind(By.name(fromIDFilter)).sendKeys("1");
    Thread.sleep(1000);
    waitAndFind(By.name(toIDFilter)).click();
    waitAndFind(By.name(toIDFilter)).sendKeys("3");
    Thread.sleep(1000);
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Cancel'])[2]/following::button[1]")).click();
    Thread.sleep(4000);
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
