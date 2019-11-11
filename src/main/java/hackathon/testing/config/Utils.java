package hackathon.testing.config;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utils {
	public WebDriver driver;

	public Properties readPropertiesFile(String filePath) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(filePath);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	public WebDriver setupBrowser(Browsers browser) {
		try {
			switch (browser) {
			case CHROME:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case SAFARI:
				System.setProperty("webdriver.safari.noinstall", "true");
				driver = new SafariDriver();
				break;
			case OPERA:
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
				break;
			case EDGE:
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			case IE:
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			default:
				System.err.println("Invalid browser parameter! Try Chrome, FireFox, Safari, Opera, Edge ou IE'.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}

	public enum Browsers {
		CHROME, FIREFOX, SAFARI, EDGE, OPERA, IE;
	}

	public File takeScreenShot(String filePath) throws InterruptedException, IOException {
		Thread.sleep(1000);
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);
		FileUtils.copyFile(srcFile, destFile);
		return destFile;
	}

	public boolean imageComparison(File file1, File file2) throws IOException {

		BufferedImage bufferfileInput = ImageIO.read(file1);
		DataBuffer datafileInput = bufferfileInput.getData().getDataBuffer();
		int sizefileInput = datafileInput.getSize();

		BufferedImage bufferfileOutPut = ImageIO.read(file2);
		DataBuffer datafileOutPut = bufferfileOutPut.getData().getDataBuffer();
		int sizefileOutPut = datafileOutPut.getSize();

		Boolean matchFlag = true;

		if (sizefileInput == sizefileOutPut) {
			for (int i = 0; i < sizefileInput; i++) {
				if (datafileInput.getElem(i) != datafileOutPut.getElem(i)) {
					matchFlag = false;
					break;
				}
			}
		} else {
			matchFlag = false;
		}
		return matchFlag;
	}
}