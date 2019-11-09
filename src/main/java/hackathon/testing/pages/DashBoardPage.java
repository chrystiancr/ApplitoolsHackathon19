package hackathon.testing.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashBoardPage extends PageObject {

	@FindBy(id = "amount")
	WebElement amountButton;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[1]")
	WebElement starbucks;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[2]")
	WebElement stripe;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[3]")
	WebElement mailchimp;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[4]")
	WebElement shopity;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[5]")
	WebElement ebay;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[6]")
	WebElement templates;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[1]/td[5]")
	WebElement amount1;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[2]/td[5]")
	WebElement amount2;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[3]/td[5]")
	WebElement amount3;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[4]/td[5]")
	WebElement amount4;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[5]/td[5]")
	WebElement amount5;

	@FindBy(xpath = "//*[@id=\"transactionsTable\"]/tbody/tr[6]/td[5]")
	WebElement amount6;

	@FindBy(id = "showExpensesChart")
	WebElement showExpensesChart;

	@FindBy(id = "addDataset")
	WebElement addDatasetButton;

	@FindBy(id = "flashSale")
	WebElement gif1;

	@FindBy(id = "flashSale2")
	WebElement gif2;

	public DashBoardPage(WebDriver pageDriver) {
		super(pageDriver);
	}

	public void amountButtonClick() {
		waiter.until(ExpectedConditions.visibilityOf(amountButton)).click();
	}

	public Integer getRowAmount(WebElement tr) {
		return Integer.parseInt(
				tr.getText().replace("USD", "").replace(".", "").replace(",", "").replace("+", "").replaceAll(" ", ""));
	}

	public String getRowContent(WebElement tr) {
		return tr.getText();
	}

	public List<Integer> getAmounts() {
		List<Integer> array = new ArrayList<Integer>();

		array.add(getRowAmount(amount1));
		array.add(getRowAmount(amount2));
		array.add(getRowAmount(amount3));
		array.add(getRowAmount(amount4));
		array.add(getRowAmount(amount5));
		array.add(getRowAmount(amount6));

		return array;
	}

	public List<Integer> getAmountsOrderAsc() {
		List<Integer> array = getAmounts();

		Collections.sort(array);

		return array;
	}

	public List<String> getTransactionsContent() {
		List<String> rows = new ArrayList<String>();
		rows.add(getStarbucksTransaction());
		rows.add(getStripeTransaction());
		rows.add(getMailChinpTransaction());
		rows.add(getShopifyTransaction());
		rows.add(getEbayTransaction());
		rows.add(getTemplatesTransaction());

		return rows;
	}

	public String getStarbucksTransaction() {
		return getRowContent(starbucks);
	}

	public String getStripeTransaction() {
		return getRowContent(stripe);
	}

	public String getMailChinpTransaction() {
		return getRowContent(mailchimp);
	}

	public String getShopifyTransaction() {
		return getRowContent(shopity);
	}

	public String getEbayTransaction() {
		return getRowContent(ebay);
	}

	public String getTemplatesTransaction() {
		return getRowContent(templates);
	}

	public void addDatasetButtonCLick() {
		waiter.until(ExpectedConditions.visibilityOf(addDatasetButton)).click();
	}
	
	public void showExpensesChartCLick() {
		waiter.until(ExpectedConditions.visibilityOf(showExpensesChart)).click();
	}
	
	public boolean checkGifsPresence() {

		if (gif1.isDisplayed() && gif1.getAttribute("style").equals("display: block;")) {
			if (gif2.isDisplayed() && gif2.getAttribute("style").equals("display: block;")) {
				return true;
			}
		}
		return false;
	}

}