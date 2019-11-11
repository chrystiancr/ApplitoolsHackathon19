package hackathon.testing.test;

import org.testng.annotations.Test;

public class VisualTests extends TestBase {

	@Test(priority = 1)
	public void loginPageUiElementsTest() {
		checkScreen("login");
	}

	@Test(priority = 2)
	public void loginWithoutData() {
		login.doLogin("", "");
		checkScreen("login2");
	}

	@Test(priority = 3, parameters = "username")
	public void loginWithoutPassoword(String username) {
		login.doLogin(username, "");
		checkScreen("login3");
	}

	@Test(priority = 4, parameters = "password")
	public void loginWithourUser(String password) {
		login.doLogin("", password);
		checkScreen("login4");
	}

	@Test(priority = 5, parameters = { "username", "password" })
	public void doLoginTest(String username, String password) {
		login.doLogin(username, password);
		checkScreen("login5");
	}

	@Test(priority = 6, description = "out of scope")
	public void loginWithWhiteSpace() {
		login.doLogin("   ", "   ");
		checkScreen("login6");
	}

	@Test(priority = 7, parameters = { "username", "password" })
	public void tableSortTest(String username, String password) {
		login.doLogin(username, password);
		checkScreen("dashboard");

		dashboard.amountButtonClick();
		checkScreen("dashboard2");
	}

	@Test(priority = 8, parameters = { "username", "password" })
	public void canvasChartTest(String username, String password) throws Exception {
		login.doLogin(username, password);
		checkScreen("dashboard3");

		dashboard.showExpensesChartCLick();
		checkScreen("canvas");

		dashboard.addDatasetButtonCLick();
		checkScreen("canvas2");
	}

	@Test(priority = 10, parameters = { "username", "password" })
	public void dynamicContentTest(String username, String password) {
		driver.get(URL_DC);
		login.doLogin(username, password);
		checkScreen("gifs");
	}

}