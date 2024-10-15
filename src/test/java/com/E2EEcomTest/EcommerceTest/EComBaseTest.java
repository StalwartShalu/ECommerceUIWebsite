package com.E2EEcomTest.EcommerceTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EComBaseTest {

	public static void main(String[] args) throws InterruptedException {

		

// Start Edge Session
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("shalini098@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shalini1526$");
		driver.findElement(By.id("login")).click();
		List<WebElement> collectedList = driver.findElements(By.xpath("//h5"));
		System.out.println("Available Items are: ");
		System.out.println( " ");
		for (int i = 0; i < collectedList.size(); i++) {
			System.out.println(collectedList.get(i).getText());
		}
		System.out.println(" ");
		System.out.println("Items Collected");

		WebElement itemName = collectedList.stream()
				.filter(prod -> prod.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findAny()
				.orElseThrow(null);
		//System.out.println(itemName.getText());

		itemName.findElement(By.xpath("//button[2]")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//app-sidebar/nav/ul/li[4]/button")).click();

		List<WebElement> cartProduct = driver.findElements(By.cssSelector("div.cartSection h3"));
		
		System.out.println(" ");
		System.out.println("Product in Cart is:  ");
		for (int i = 0; i < cartProduct.size(); i++) {
			System.out.println(cartProduct.get(i).getText());
		}

		driver.findElement(By.xpath("//li[@class=\"totalRow\"]/button")).click();
		driver.findElement(By.xpath("//div[@class=\"form-group\"]/input")).sendKeys("ind");

		List<WebElement> Country = driver
				.findElements(By.cssSelector("section.ta-results.list-group.ng-star-inserted"));
		
		WebElement selectedCountry = Country.stream()
				.filter(match -> match.findElement(By.cssSelector("button.list-group-item")).getText()
						.equals("British Indian Ocean Territory"))
				.findAny().orElseThrow(null);

		selectedCountry.findElement(By.cssSelector("button.list-group-item")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String successMsg = driver.findElement(By.tagName("h1")).getText();
		System.out.println(" ");
		System.out.println(successMsg);
		

	}

}