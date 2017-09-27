package autotests.test.pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
	public static WebDriver driver;
	public static boolean bResult;

	public BasePage(WebDriver driver){
		BasePage.driver = driver;
		BasePage.bResult = true;
	}
	public static void goToUrl(String url)
	{
		driver.get(url);
	}

}
