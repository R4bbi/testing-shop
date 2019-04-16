package com.example.tests;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortCMS {
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
  
  String sortID = "//*[@id=\"container\"]/div/div[4]/table/thead/tr/th[2]";
  String sortTitle = "//*[@id=\"container\"]/div/div[4]/table/thead/tr/th[3]";
  String sortURLKey = "//*[@id=\"container\"]/div/div[4]/table/thead/tr/th[4]";
  String sortLayout = "//*[@id=\"container\"]/div/div[4]/table/thead/tr/th[5]";
  
  @Before
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSortCMS() throws Exception {
    driver.get("http://localhost/magento/admin/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("kony");
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("qwerty12345");
    driver.findElement(By.id("login-form")).submit();
    driver.findElement(By.xpath("//*[@id=\"menu-magento-backend-content\"]")).click();
    driver.findElement(By.linkText("Pages")).click();
    Thread.sleep(5000);
    
    waitAndFind(By.xpath(sortID)).click();
    Thread.sleep(6000);
    
    waitAndFind(By.xpath(sortLayout)).click();
    Thread.sleep(6000);
    
    waitAndFind(By.xpath(sortTitle)).click();
    Thread.sleep(6000);
    
    waitAndFind(By.xpath(sortURLKey)).click();
    Thread.sleep(6000);
    
    
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
