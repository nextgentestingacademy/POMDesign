package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest{
	private LoginPage login;
	
	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() {
		login = new LoginPage(driver);
	}
	
	
	@Parameters({"username","password"})
	@Test(priority=1,enabled=true,groups={"smoke","regression"})
	public void loginValidCredentials(String username, String password) throws InterruptedException {
		login.enterUsername(username);
		login.enterPassword(password);
		login.clickLogin();
		
		String dashboardUrl = driver.getCurrentUrl();
		Assert.assertTrue(dashboardUrl.contains("dashboard"));
	}
	
	@Test(groups="regression",priority=0)
	public void loginInvalidCredentials() throws InterruptedException {
		
		SoftAssert soft = new SoftAssert();
		login.enterUsername("Admin");
		login.enterPassword("admin1234");
		login.clickLogin();
		
		String dashboardUrl = driver.getCurrentUrl();
		Assert.assertFalse(dashboardUrl.contains("dashboard"));
		
		soft.assertAll();
	}

}
