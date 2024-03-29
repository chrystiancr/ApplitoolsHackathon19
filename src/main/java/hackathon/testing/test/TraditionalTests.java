package hackathon.testing.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TraditionalTests extends TestBase {

	@Test(priority = 1)
	public void loginPageUiElementsTest() {
		assertTrue(login.checkPageElements());
	}

	@Test(priority = 2)
	public void loginWithoutData() {
		login.doLogin("", "");
		assertEquals(login.getAlertMessage(), "Both Username and Password must be present");
	}

	@Parameters("username")
	@Test(priority = 3)
	public void loginWithoutPassoword(String username) {
		login.doLogin(username, "");
		assertEquals(login.getAlertMessage(), "Password must be present");
	}

	@Parameters("password")
	@Test(priority = 4)
	public void loginWithoutUser(String password) {
		login.doLogin("", password);
		assertEquals(login.getAlertMessage(), "Username must be present");
	}

	@Parameters({ "username", "password" })
	@Test(priority = 5)
	public void doLoginTest(String username, String password) {
		login.doLogin(username, password);
		assertEquals(driver.getCurrentUrl(), "https://demo.applitools.com/hackathonApp.html");
	}

	@Ignore
	@Test(priority = 6, description = "out of scope")
	public void loginWithWhiteSpace() {
		login.doLogin("   ", "   ");
		assertEquals(login.getAlertMessage(), "Both Username and Password must be present");
	}

	@Parameters({ "username", "password" })
	@Test(priority = 7)
	public void tableSortTest(String username, String password) {
		login.doLogin(username, password);
		dashboard.amountButtonClick();

		List<String> rows = new ArrayList<String>();
		rows.add(dashboard.getStarbucksTransaction());
		rows.add(dashboard.getStripeTransaction());
		rows.add(dashboard.getMailChinpTransaction());
		rows.add(dashboard.getShopifyTransaction());
		rows.add(dashboard.getEbayTransaction());
		rows.add(dashboard.getTemplatesTransaction());

		assertEquals(dashboard.getAmounts(), dashboard.getAmountsOrderAsc());
		assertEquals(rows, dashboard.getTransactionsContent());

	}

	@Parameters({ "username", "password" })
	@Test(priority = 8)
	public void canvasChartTest(String username, String password) throws Exception {
		login.doLogin(username, password);
		dashboard.showExpensesChartCLick();
		dashboard.checkCanvasPresence();

		File baseImagePath = new File("images/base_canvas_page.jpg");
		File currentImagePath = util.takeScreenShot("images/current_canvas_page.jpg");

		assertTrue(util.imageComparison(baseImagePath, currentImagePath));

		dashboard.addDatasetButtonCLick();

		File baseImagePath2 = new File("images/base_canvas_page2.jpg");
		File currentImagePath2 = util.takeScreenShot("images/current_canvas_page2.jpg");

		assertTrue(util.imageComparison(baseImagePath2, currentImagePath2));

	}

	@Parameters({ "username", "password" })
	@Test(priority = 9)
	public void dynamicContentTest(String username, String password) {
		driver.get(URL_DC);
		login.doLogin(username, password);
		assertTrue(dashboard.checkGifsPresence());
	}

}