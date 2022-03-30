package com.facebook;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		driver.findElement(By.id("email")).sendKeys("tier5qa@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("B@7'tew7:av\"Q89H");
		driver.findElement(By.name("login")).click();
		Thread.sleep(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			long lastHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);
				List<WebElement> allLikes = driver.findElements(By.xpath("//span[text() = 'Like']"));
				for (WebElement all : allLikes) {
					all.click();
					System.out.println("Printing" + allLikes.size());
				}
				long newHeight = ((Number) js.executeScript("return document.body.scrollHeight")).longValue();
				if (newHeight == lastHeight) {
					System.out.println("before break");
					break;
				}
				lastHeight = newHeight;

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
