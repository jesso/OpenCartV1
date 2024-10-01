package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[text()='My Account']") WebElement msgHeading;
	@FindBy(xpath = "//div[@class='list-group']//a[contains(text(),'Logout')]") WebElement btnLogout;
	
	//Action methods
	
	public boolean isMyAccountPageExist()
	{
		try
		{
			return(msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		btnLogout.click();
	}
}
