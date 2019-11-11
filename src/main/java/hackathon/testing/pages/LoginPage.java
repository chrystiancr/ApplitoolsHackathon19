package hackathon.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageObject {

	@FindBy(css = "body > div > div > div.logo-w > a > img")
	WebElement logo;

	@FindBy(css = "body > div > div > h4")
	WebElement formTitle;

	@FindBy(css = "body > div > div > form > div:nth-child(1) > div")
	WebElement userIcon;

	@FindBy(css = "body > div > div > form > div:nth-child(1) > label")
	WebElement userNameLabel;

	@FindBy(id = "username")
	WebElement userName;

	@FindBy(css = "body > div > div > form > div:nth-child(2) > div")
	WebElement authenticationIcon;

	@FindBy(css = "body > div > div > form > div:nth-child(2) > label")
	WebElement passwordLabel;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "log-in")
	WebElement loginButton;

	@FindBy(css = "body > div > div > form > div.buttons-w > div.form-check-inline > label")
	WebElement remeberLabel;

	@FindBy(css = "body > div > div > form > div.buttons-w > div.form-check-inline > label > input")
	WebElement remeberButton;

	@FindBy(css = "body > div > div > form > div.buttons-w > div:nth-child(3) > a:nth-child(1) > img")
	WebElement twitterIcon;

	@FindBy(css = "body > div > div > form > div.buttons-w > div:nth-child(3) > a:nth-child(2) > img")
	WebElement facebookIcon;

	@FindBy(css = "body > div > div > form > div.buttons-w > div:nth-child(3) > a:nth-child(3) > img")
	WebElement linkedinIcon;

	@FindBy(xpath = "//*[@class=\"alert alert-warning\"]")
	WebElement alertBox;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public LoginPage userNameSenKeys(String keysToSend) {
		waiter.until(ExpectedConditions.visibilityOf(userName)).sendKeys(keysToSend);
		return this;
	}

	public LoginPage passwordSenKeys(String keysToSend) {
		waiter.until(ExpectedConditions.visibilityOf(password)).sendKeys(keysToSend);
		return this;
	}

	public LoginPage loginButtonClick() {
		waiter.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
		return this;
	}

	public LoginPage rememberMeButtonClick() {
		waiter.until(ExpectedConditions.elementToBeClickable(remeberButton)).click();
		return this;
	}

	public LoginPage doLogin(String username, String password) {
		userNameSenKeys(username).passwordSenKeys(password).rememberMeButtonClick().loginButtonClick();
		return new LoginPage(driver);
	}

	public String getAlertMessage() {
		return waiter.until(ExpectedConditions.visibilityOf(alertBox)).getText();
	}

	public boolean checkPageElements() {
		waiter.until(ExpectedConditions.visibilityOf(logo));
		waiter.until(ExpectedConditions.textToBePresentInElement(formTitle, "Login Form"));
		waiter.until(ExpectedConditions.visibilityOf(userIcon));
		waiter.until(ExpectedConditions.textToBePresentInElement(userNameLabel, "Username"));
		waiter.until(ExpectedConditions.textToBePresentInElement(userName, "Enter your username"));
		waiter.until(ExpectedConditions.visibilityOf(authenticationIcon));
		waiter.until(ExpectedConditions.textToBePresentInElement(passwordLabel, "Password"));
		waiter.until(ExpectedConditions.textToBePresentInElement(password, "Enter your password"));
		waiter.until(ExpectedConditions.elementToBeClickable(loginButton));
		waiter.until(ExpectedConditions.elementToBeClickable(remeberButton));
		waiter.until(ExpectedConditions.textToBePresentInElement(remeberLabel, "Remember Me"));
		waiter.until(ExpectedConditions.elementToBeClickable(twitterIcon));
		waiter.until(ExpectedConditions.elementToBeClickable(facebookIcon));
		waiter.until(ExpectedConditions.elementToBeClickable(linkedinIcon));
		return true;
	}

}
