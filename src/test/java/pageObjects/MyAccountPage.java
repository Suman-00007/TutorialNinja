package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[@id='content']/h2[1]")
	WebElement MyAccount;
	
	@FindBy(xpath="//*[@id='column-right']/div/a[13]")
	WebElement LogOutBtn;
	
	public boolean verifyMyAccount() {
		try{
			String m = MyAccount.getText();
			if(m.equals("My Account")) return true;
		}
		catch(Exception e) {
			return false;
		 }
		return false;
	}
	public void clickLogOutBtn() {
		LogOutBtn.click();
	}

}
