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
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;

public class ColumnChoose {
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
	driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testColumnChoose() throws Exception {
    driver.get("http://localhost/magento/admin/");
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("kony");
    driver.findElement(By.id("login")).clear();
    driver.findElement(By.id("login")).sendKeys("qwerty12345");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("//*[@id=\"menu-magento-backend-content\"]")).click();
    driver.findElement(By.linkText("Pages")).click();
    Thread.sleep(7000);
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Submit'])[1]/following::button[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Title'])[1]/following::label[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Columns'])[1]/following::div[4]")).click();
    
    
    List<WebElement> elements = driver.findElements(By.tagName("th"));
    System.out.println("liczba kolumn: " +elements.size());
    
    for (int i = 1, j = elements.size()-1; i <= 9; i++, j--) {
    	driver.findElement(By.id(Integer.toString(i))).click();
    	elements.remove(0);
    	System.out.println("liczba kolumn: " +elements.size());
    	assertEquals(j, elements.size());
    	
    	Thread.sleep(500);
    	
    }
    
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Action'])[1]/following::button[1]")).click();
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
