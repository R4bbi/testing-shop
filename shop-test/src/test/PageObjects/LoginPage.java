
public class LoginPage extends BasePage {
	
	WebDriver driver;
	
	String loginInputId = "username";
	String passwdInputId = "login";
	String signInBtn = "//*[@id=\"login-form\"]/fieldset/div[3]/div[1]/button";
	
	public LoginPage() {
		driver = Initalization.getDriver();
		
	}
	
	public void loginAs(String login, String pass) {
		
		waitAndFind(By.id(loginInputId)).sendKeys(login);
		waitAndFind(By.id(passwdInputId)).sendKeys(pass);
	}
}
