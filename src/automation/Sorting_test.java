package automation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class Sorting_test {
	public WebDriver driver;

	@BeforeTest()
	public void login() {

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.get("https://www.saucedemo.com/inventory.html");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
	}

	public void sort_items_low_to_high() {
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();
		List<WebElement> thePricesList = driver.findElements(By.className("inventory_item_price"));
		List<Double> newList = new ArrayList<>();

		for (int i = 0; i < thePricesList.size() ; i++) {
			String price = thePricesList.get(i).getText();
			String newprice = price.replace("$", " ");
			double val = Double.parseDouble(newprice.trim());
			newList.add(val);
		}
		for (int k = 0; k < newList.size(); k++) {
			boolean checProcess = newList.get(0) < newList.get(newList.size() - 1);
			Assert.assertEquals(checProcess, true);
		}
	}
}