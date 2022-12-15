package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_Exercise_II {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress, firstname, lastName, password, fullName;
	
	By myAccountLink = By.xpath("//div[@class='footer']//a[@Title='My Account']");
	By loginButton = By.cssSelector("#send2");
	By emailTextbox = By.id("email");
	By passwordTextbox = By.id("pass");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		
		rand = new Random();
		driver = new FirefoxDriver(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		emailAddress = "votrongthanh" +  rand.nextInt(9999) + "@gmail.com";
		firstname = "Thanh";
		lastName = "Vo Trong";
		password = "12345678";
		fullName= firstname +" " + lastName;
	}

	@Test
	public void TC_01_Empty_Enail_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(2);
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(2);
		driver.findElement(emailTextbox).sendKeys("123456@12345.123455");
		driver.findElement(passwordTextbox).sendKeys("123456");
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("#advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Password_5characters() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(2);
		driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("12345");
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_Incorrect_Email_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(2);
		driver.findElement(emailTextbox).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123123123");
		driver.findElement(loginButton).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
	}
	
	@Test
	public void TC_05_Create_New_Account() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@Title='Create an Account']")).click();
		sleepInSecond(2);
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		sleepInSecond(10);
		
		Assert.assertEquals(driver.getTitle(),"Home page");
				
	}
	
	@Test
	public void TC_06_Login_Success() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccountLink).click();
		sleepInSecond(2);
		
		driver.findElement(emailTextbox).sendKeys("votronganh12345678@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("12345678");
		
		driver.findElement(loginButton).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(),"My Account");
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains("votronganh12345678@gmail.com"));
		
				
	}
	public void sleepInSecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}