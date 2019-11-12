package hackathon.testing.test;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;

import hackathon.testing.config.Utils;
import hackathon.testing.config.Utils.Browsers;
import hackathon.testing.pages.DashBoardPage;
import hackathon.testing.pages.LoginPage;

public class TestBase {
	protected WebDriver driver;
	protected Eyes eyes;
	protected BatchInfo batch = new BatchInfo("Hackathon");
	protected Utils util = new Utils();
	private Properties prop = util.readPropertiesFile("config.properties");
	private final String URL = prop.getProperty("URL_V2");
	protected final String URL_DC = prop.getProperty("URL_DYNAMIC_CONTENT_V2");
	protected final String EYES_API_KEY = prop.getProperty("EYES_API_KEY");
	protected LoginPage login;
	protected DashBoardPage dashboard;

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		driver = util.setupBrowser(Browsers.CHROME);
		login = new LoginPage(driver);
		dashboard = new DashBoardPage(driver);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUpTest() {
		driver.get(URL);
		initEyes();
		eyes.setBatch(batch);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		eyes.abortIfNotClosed();
		driver.quit();
	}

	public void initEyes() {
		eyes = new Eyes();
		eyes.setApiKey(EYES_API_KEY);
	}

	public void checkScreen(String screenName) {
		eyes.open(driver, "Hackathon", Thread.currentThread().getStackTrace()[2].getMethodName(),
				new RectangleSize(1920, 884));
		eyes.checkWindow(screenName);
		eyes.close();
	}

	public void checkRegion(By selector, String tag) {
		eyes.open(driver, "Hackathon", Thread.currentThread().getStackTrace()[2].getMethodName(),
				new RectangleSize(1920, 884));
		eyes.checkRegion(selector, tag);
		eyes.close();
	}

	public void checkRegion(By selector, String tag, String variable) {
		eyes.open(driver, "Hackathon", Thread.currentThread().getStackTrace()[2].getMethodName() + variable,
				new RectangleSize(1920, 884));
		eyes.checkRegion(selector, tag);
		eyes.close();
	}
}
