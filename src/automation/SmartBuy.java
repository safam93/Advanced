package automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SmartBuy {

	public WebDriver driver;
	public int numberOfTry = 100;
	public int items_in_the_stock = 4;
	SoftAssert softassertProcess = new SoftAssert();
	

	@BeforeTest()

	public void this_is_before_test() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://smartbuy-me.com/smartbuystore/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();

	}

	@Test()
	public void Test_Adding_item_SAMSUNG_50_inch() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		for (int i = 0; i < numberOfTry; i++) {

			driver.findElement(By.xpath(
					"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[3]/div[1]/div/div/form[1]/div[1]/button"))
					.click();

			driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();
		}

	}

	@Test()
	public void we_need_to_check_the_correct_price() {

		String theSingleItemPrice = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]"))
				.getText();

		String[] theUpdatedSingleItemPrice = theSingleItemPrice.split("JOD");

		String TheFinalSingleItemPrice = theUpdatedSingleItemPrice[0].trim();
		String Updated =TheFinalSingleItemPrice.replace(",", ".");

		Double FinalPrice = Double.parseDouble(Updated);
		System.out.println(FinalPrice);
		System.out.println(FinalPrice*numberOfTry);
		
		String Actualresult = driver.getTitle();
		softassertProcess.assertEquals(Actualresult, "safa");
		softassertProcess.assertAll();
	}

}
