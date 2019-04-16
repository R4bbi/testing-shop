package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ListBlocks {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testListBlocks() throws Exception {
    driver.get("http://localhost/magento/admin/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("kony");
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("qwerty12345");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("//*[@id=\"menu-magento-backend-content\"]")).click();
    driver.findElement(By.linkText("Blocks")).click();
    Thread.sleep(8000);
    driver.findElement(By.id("fulltext")).click();
    driver.findElement(By.id("fulltext")).clear();
    driver.findElement(By.id("fulltext")).sendKeys("Men");
    driver.findElement(By.id("fulltext")).sendKeys(Keys.ENTER);
    Thread.sleep(4000);
    driver.findElement(By.cssSelector("#container > div > div.admin__data-grid-header > div:nth-child(1) > div.admin__data-grid-filters-current._show > div.admin__current-filters-list-wrap > ul > li > button")).click();
    Thread.sleep(1000);
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
