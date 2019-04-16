package com.example.tests;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.junit.After;
import org.junit.Assert;
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


public class SearchCMS {
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
 
  
  
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSearchCMS() throws Exception {
    driver.get("http://localhost/magento/admin/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("kony");
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("qwerty12345");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("//*[@id=\"menu-magento-backend-content\"]")).click();
    driver.findElement(By.linkText("Pages")).click();
    driver.findElement(By.id("fulltext")).clear();
    Thread.sleep(4000);
    driver.findElement(By.id("fulltext")).sendKeys("Home");
    driver.findElement(By.id("fulltext")).sendKeys(Keys.ENTER);
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("#container > div > div.admin__data-grid-header > div:nth-child(1) > div.admin__data-grid-filters-current._show > div.admin__current-filters-list-wrap > ul > li > button")).click();
    Thread.sleep(2000);
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
