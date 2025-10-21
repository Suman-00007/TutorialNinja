package testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_UserRegistration extends BaseClass {
	
	String mail, pass;
	@Test(priority =1, groups="Regression")
	
	public void verifyRegistration() throws Exception {
		
		logger.info("***** Test verifyRegistration Starts*****");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("***** Clicked on MyAccount*****");
		hp.clickRegister();
		logger.info("***** Clicked on Register*****");
		
		
		RegistrationPage rp = new RegistrationPage(driver);
		logger.info("***** User REgistration going on *****");
		rp.setFirstName(randomeString());
		rp.setLastName(randomeString());
		mail = (randomeString()+"@gmail.com") ;
		rp.setEmail(mail);
		rp.setTelephone(randomeNumber());
		pass = randomAlphaNumeric();
		rp.setPassword(pass);
		rp.setConfirmPassword(pass);
		rp.setPrivacyPolicy();
		rp.clickContinue();
		TakesScreenshot ts =  (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\2303544\\newEclipseworkspace\\TutorialNunja\\screenshots\\ConfMsgpng");
		FileUtils.copyFile(src, trg);
		
		System.out.println("mail- "+mail + " password- "+pass);
		if(rp.getConfirmationMsg().equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
			logger.info("***** Test verifyRegistration Passed *****");
			
		}
		else {
			Assert.assertFalse(false);
			logger.debug("Debugging..........");
			logger.error("Error..........");
		}
		
		
		
	}
	

}
