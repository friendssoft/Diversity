package com.wf.hackathon.diversity.controller;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.bonigarcia.wdm.WebDriverManager;

@RestController
@RequestMapping("/selenium")
public class SeleniumController {

	@GetMapping
	public void getLinkedinDetails(@RequestParam("name")String name) throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	
	WebDriver driver = new ChromeDriver();
	driver.get("https://www.linkedin.com/");
	
	driver.manage().window().maximize();
	
	driver.findElement(By.xpath("//html/body/nav/div/a[2]")).click();
	
	WebElement email = driver.findElement(By.id("username"));

	//Get the Web Element corresponding to the Password Field 
	WebElement password = driver.findElement(By.id("password"));

	//Find the Sign me in button
	//WebElement login = driver.findElement(By.id("user_submit"));
	
	WebElement submit = driver.findElement(By.xpath("//*[@id=\"organic-div\"]/form/div[3]/button"));

	email.sendKeys("anwar4frnd@gmail.com");
	password.sendKeys("Khayan@484");

	submit.click();
	WebElement search = driver.findElement(By.xpath("//input"));
	search.sendKeys(name);
	search.sendKeys(Keys.ENTER);
	
	WebElement seeAllResults = (new WebDriverWait(driver, 5))
		       .until(ExpectedConditions.elementToBeClickable(By.linkText("See all people results")));
	
	seeAllResults.click();
	
	WebElement userProfile = (new WebDriverWait(driver, 5))
		       .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"main\"]//ul/li[1]/div/div/div[2]/div[1]/div[1]/div/span[1]/span/a")));
	
	userProfile.click();
	
	boolean exists = driver.findElements(By.xpath("//*[@id='main']/div/div/div[1]/div/a/div/div[2]/div[2]/a")).size()!= 0;
	if(exists) {
		WebElement viewFullProfile = (new WebDriverWait(driver, 5))
			       .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main']/div/div/div[1]/div/a/div/div[2]/div[2]/a")));
		viewFullProfile.click();
	}
	

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,250)", "");
	
	String path = "/html/body/div[6]/div[3]/div/div/div[2]/div/div/main/section";
	Thread.sleep(10000);
	List<WebElement> userExperience = driver.findElements(By.xpath(path));
	String experience = "";
	int counter = 1;
	for (WebElement webElement : userExperience) {
		boolean isExists = driver.findElements(By.xpath(path+"["+counter+"]"+"/div[@id='experience']")).size()!= 0;
		if(isExists) {
			experience = driver.findElement(By.xpath(path+"["+counter+"]")).getText();
			break;
		}
		counter++;
	}
	counter = 1;
	String about = "";
	for (WebElement webElement : userExperience) {
		boolean isExists = driver.findElements(By.xpath(path+"["+counter+"]"+"/div[@id='about']")).size()!= 0;
		if(isExists) {
			about = driver.findElement(By.xpath(path+"["+counter+"]")).getText();
			break;
		}
		counter++;
	}
	
	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	System.out.println(experience);
	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
	
	
	System.out.println("*******************************************************************");
	System.out.println(about);
	System.out.println("*******************************************************************");
	
	driver.close();
	
	}
}
