package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_ID() {
		// Open Register page 
		driver.get("https://demo.nopcommerce.com/register");
		// Thao tác lên element thì đầu tiên phải tìm được element đó
		// Find theo cái gì: id/class/name/css/ xpath....
		driver.findElement(By.id("FirstName")).sendKeys("Phuong");
		driver.findElement(By.id("LastName")).sendKeys("Nguyen Thi");
		driver.findElement(By.id("Email")).sendKeys("phuongnt161@gmail.com");
		driver.findElement(By.id("Company")).sendKeys("Boost commerce");
		driver.findElement(By.id("Password")).sendKeys("thothoifamily");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("thothoifamily");
		driver.findElement(By.id("register-button")).click();
		
	}

	@Test
	public void TC_02_Class() {
		// Mở màn hình search 
		driver.get("https://demo.nopcommerce.com/search");
		driver.findElement(By.className("search-text")).sendKeys("Macbook");
		
	}
	
	@Test
	public void TC_03_Name() {
		// Tìm ra bao nhiêu thẻ input trên màn hình hiện tại
	    driver.findElement(By.name("advs")).click();
	}

	@Test
	public void TC_04_TagName() {
		// Tìm ra bao nhiêu thẻ input trên màn hình hiện tại
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	
	@Test
	public void TC_05_LinkText() {
		// Click vào đường link address
		
		driver.findElement(By.linkText("Addresses")).click();
		
		
	}
	
	@Test
	public void TC_06_PartiaLinktext() {
		// Sự khác biệt dữa link text và partialinktext là Linktext phải truyền vào string tuyệt đối còn partia chỉ cần có string con là đc
		driver.findElement(By.partialLinkText("vendor account")).click();
		
	}
	
	@Test
	public void TC_07_CSS() {
		driver.get("https://demo.nopcommerce.com/register");
		// Sự khác biệt dữa link text và partialinktext là Linktext phải truyền vào string tuyệt đối còn partia chỉ cần có string con là đc
		// Cách 1 
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Selenium");
		// Cách 2
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Locator");
		// Cách 3
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("phuongnt161@gmail.com");
		
		
	}

	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register");
		// Sự khác biệt dữa link text và partialinktext là Linktext phải truyền vào string tuyệt đối còn partia chỉ cần có string con là đc
		// Cách 1 
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//input[@id='FirstName']")).clear();
		// hoặc
		driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("cách 2");
		// Cách 2
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Locator");
		// Cách 3
		driver.findElement(By.xpath("//label[text()='Last name:']/following-sibling::input")).sendKeys("phuongnt161@gmail.com");
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}