package hackathon.testing.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.Eyes;

import hackathon.testing.config.Utils;
import hackathon.testing.config.Utils.Browsers;
import hackathon.testing.pages.DashBoardPage;
import hackathon.testing.pages.LoginPage;

public class TestBase {
	protected WebDriver driver;
	protected Eyes eyes;
	protected Utils util = new Utils();
	private Properties prop = util.readPropertiesFile("config.properties");
	private final String URL = prop.getProperty("URL_V1");
	protected final String URL_DC = prop.getProperty("URL_DYNAMIC_CONTENT_V1");
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
			eyes.open(driver, "Hackathon", Thread.currentThread().getStackTrace()[2].getMethodName());
			eyes.setForceFullPageScreenshot(true);
			eyes.setMatchLevel(MatchLevel.EXACT);
			eyes.checkWindow(screenName);
			eyes.close();

	}
}
