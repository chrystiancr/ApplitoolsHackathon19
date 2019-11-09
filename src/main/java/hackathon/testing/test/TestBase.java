package hackathon.testing.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import hackathon.testing.config.Utils;
import hackathon.testing.config.Utils.Browsers;
import hackathon.testing.pages.DashBoardPage;
import hackathon.testing.pages.LoginPage;

public class TestBase {
	protected WebDriver driver;
	protected Utils util = new Utils();
	private Properties prop = util.readPropertiesFile("config.properties");
	private final String URL_V1 = prop.getProperty("URL_V1");
	protected final String URL_DC = prop.getProperty("URL_DYNAMIC_CONTENT_V1");
	protected LoginPage login;
	protected DashBoardPage dashboard;

	@BeforeClass
	public void setUp() {
		driver = util.setupBrowser(Browsers.CHROME);
		login = new LoginPage(driver);
		dashboard = new DashBoardPage(driver);
	}

	@BeforeMethod
	public void tearTestDown() {
		driver.get(URL_V1);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
