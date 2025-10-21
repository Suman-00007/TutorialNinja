package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utilities.DataProviders;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC003_LogInDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	
	public void VerifyLoginDDT(String mail, String pwd, String exp) {
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogIn();
		
		LogInPage lp = new LogInPage(driver);
		lp.setEmail(mail);
		lp.setPass(pwd);
		lp.clickLogin();
		
		MyAccountPage mp = new MyAccountPage(driver);
		boolean bool = mp.verifyMyAccount();
		
		if(bool==true) {
			mp.clickLogOutBtn();
			if(exp.toLowerCase().equals("valid")) Assert.assertTrue(true);
			else Assert.fail();
		}
		if(bool==false) {
			if(exp.toLowerCase().equals("invalid")) Assert.assertTrue(true);
			else Assert.fail();
		}
		
		
	}


}
