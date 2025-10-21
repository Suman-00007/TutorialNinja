package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage {

	public LogInPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[@id='input-email']")
	WebElement email ;
	
	@FindBy(xpath= "//*[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//*[@id='content']/div/div[2]/div/form/input")
	WebElement loginBtn ;
	
	public void setEmail(String mail) {
		email.sendKeys(mail);
	}
	public void setPass(String p) {
		password.sendKeys(p);
	}
	public void clickLogin() {
		loginBtn.click();
	}
	

}
