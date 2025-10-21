package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_UserLogin extends BaseClass {
	
	@Test( groups="Sanity")
	public void VerifyLogin() {
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogIn();
		
		LogInPage lp = new LogInPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPass(p.getProperty("pass"));
		lp.clickLogin();
		
		MyAccountPage mp = new MyAccountPage(driver);
		
		Assert.assertEquals(true, mp.verifyMyAccount(), "Login Failed!!");  
		
	}

}
