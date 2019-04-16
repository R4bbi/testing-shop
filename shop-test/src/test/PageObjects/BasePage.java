

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	 public WebDriver driver;

	   	public BasePage() {
	   		driver = Initalization.getDriver();
	   	}
	 
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
}
