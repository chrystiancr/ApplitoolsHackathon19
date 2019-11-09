package hackathon.testing.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

	protected static WebDriver driver;
	protected WebDriverWait waiter;

	public PageObject(WebDriver pageDriver) {
		driver = pageDriver;
		waiter = new WebDriverWait(driver, 15);
		waiter.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofSeconds(1));
		PageFactory.initElements(driver, this);
	}
}