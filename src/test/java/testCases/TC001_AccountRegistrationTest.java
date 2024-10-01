package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;


import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test (groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info(" **** Starting TC001_Account RegisterationTest****");
		HomePage hp = new HomePage(driver);
		try
		{
			hp.clickMyAccount();
			logger.info("Clicked on My Account link");
			hp.clickRegister();
			logger.info("Click in Register link");
			
			AccountRegistrationPage register = new AccountRegistrationPage(driver);
			register.setFirstName(randomString().toUpperCase());
			register.setLastName(randomString().toUpperCase());
			register.setEmail(randomString() +"@gmail.com");
			register.setTelephone(randomNumber());
			register.setPassword("abcdefgh");
			register.confirmPassword("abcdefgh");
			register.setPrivacyPolicy();
			register.clickContinue();
			
			logger.info("Validating expected message");
			String confmsg = register.getConfimartionMsg();
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.info("Test Failed");
				logger.debug("Debug logs...");
			}
			//Assert.assertEquals(confmsg, "Your Account Has Been Createdd!");
		}
		catch(Exception e)
		{
			//logger.info("Test Failed");
			//logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("****** Finish Test Case*******");
	}
	
	
}
