package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_LoginTest extends BaseClass {

	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("**** Login Test Case Started*****");
		try
		{
			 HomePage home = new HomePage(driver);
			 home.clickMyAccount();
			 home.clickLogin();
			 
			 LoginPage login = new LoginPage(driver);
			 login.setEmail(properties.getProperty("email"));
			 login.setPassword(properties.getProperty("password"));
			 login.clickLogin();
			 
			 MyAccountPage macc = new MyAccountPage(driver);
			 boolean verifyHeading = macc.isMyAccountPageExist();
			 
			 Assert.assertTrue(verifyHeading, "Heading does not match");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***Login Test Case Ended***");
		 
		 
	}
}
