package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTestDDT extends BaseClass {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="Datadrivern")
	public void verfyLoginDDT(String email, String password, String exp_result)
	{
		logger.info("***Test case started ****");
		try {
			//HomePage
			HomePage home = new HomePage(driver);
			home.clickMyAccount();
			home.clickLogin();
			
			//Login
			LoginPage login = new LoginPage(driver);
			login.setEmail(email);
			login.setPassword(password);
			login.clickLogin();
			
			//MyAccount
			MyAccountPage myacc = new MyAccountPage(driver);
			myacc.isMyAccountPageExist();
			
			boolean targetPage = myacc.isMyAccountPageExist();
			
			if(exp_result.equalsIgnoreCase("Valid"))
			{
				if(targetPage == true)
				{
					myacc.clickLogout();
					Assert.assertTrue(true);
					
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			if(exp_result.equalsIgnoreCase("Invalid"))
			{
				if(targetPage == true)
				{
					myacc.clickLogout();
					Assert.assertTrue(false);
					
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***Test case failed ****");
	}
}
