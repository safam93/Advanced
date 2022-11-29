package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Call_Test_Cases {

	public WebDriver driver1;

	@BeforeTest(groups="login logout group")

	public void login() {

		WebDriverManager.chromedriver().setup();

		driver1 = new ChromeDriver();

		driver1.get("https://github.com/login");
		driver1.findElement(By.id("login_field")).sendKeys("safa.m.93@hotmail.com");
		driver1.findElement(By.id("password")).sendKeys("");
		driver1.findElement(By.name("commit")).click();
		

	}

	@Test
	public void Test_The_Title() {

		String ActualTitle = driver1.getTitle();
		String ExpectedTitle = "GitHub";
		Assert.assertEquals(ActualTitle, ExpectedTitle);
	}
	
	
	@Test (groups="login logout group")
	public void Test_The_log_out() throws InterruptedException {

		
		driver1.findElement(By.xpath("/html/body/div[1]/header/div[7]/details/summary/img")).click();
		Thread.sleep(1000);

		driver1.findElement(By.xpath("/html/body/div[1]/header/div[7]/details/details-menu/form/button")).click();
		Thread.sleep(2000);
		String ActualTitle = driver1.getTitle();
		String ExpectedTitle = "GitHub: Let’s build from here · GitHub";
		Assert.assertEquals(ActualTitle, ExpectedTitle);
	}
	
	@Test (groups="login logout group")
	public void Test_The_existance_of_the_user_name() {

	System.out.println("Logged out");
	}
	
	
}
