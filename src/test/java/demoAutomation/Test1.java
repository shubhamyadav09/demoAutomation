package demoAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("Test class epic")
public class Test1 {
	WebDriver driver;
	String searchKey;
	@BeforeSuite
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		searchKey  = System.getProperty("SEARCH_KEY");
		System.out.println(searchKey);
	}
	
	@Test
	@Story("Story 1")
	public void myTest1() {
		System.out.println("Starting test 1");
		Allure.description("Positive test");
		driver.get("https://www.wikipedia.com");
		System.out.println(driver.getTitle());
		driver.findElement(By.id("searchInput")).sendKeys(searchKey);
		driver.findElement(By.xpath("//button[contains(@class,'pure-button')]")).click();
	}
	
	@Test
	@Story("Story 2")
	public void myTest2() {	
		System.out.println("Starting test 2");
		Allure.description("Negative test");
		driver.get("https://www.wikipedia.com");
		assertEquals(driver.getTitle(), "WRONG");
		System.out.println(driver.getTitle());
		driver.findElement(By.id("searchInput")).sendKeys("Sachin Tendulkar");
		driver.findElement(By.xpath("//button[contains(@class,'pure-button')]")).click();
	}
	
	@AfterSuite
	public void closure() {
		driver.quit();
	}
}
