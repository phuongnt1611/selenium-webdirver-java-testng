package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.seleniumhq.jetty9.io.ByteBufferPool.Bucket;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	// Khai bao thu vien select
	Select select;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password;
	String day, month, year;
	String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;

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

		firstName = "Elon";
		lastName = "Musk";
		emailAddress = "ElonMusk" + getRandomNumber() + "@gmail.com";
		companyName = "SpaceX";
		password = "123456";
		day = "18";
		month = "September";
		year = "1997";

		countryName = "United States";
		provinceName = "Alabama";
		cityName = "Miami";
		addressName = "123 Po Box";
		postalCode = "33111";
		phoneNumber = "+13055555584";

	}

	public void sleepInSecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@Test
	public void TC_01_Register_New_Account() {
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		// Cách 1: Khai báo biến
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		// Chon ngay 7
		// select.selectByIndex(7);
		// chon ngay 14
		// select.selectByValue("14");
		// Chon ngay 18
		// select.deselectByVisibleText("18");

		// Cach 2: Khởi tạo và sử dụng luôn
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.xpath("//a[text()='My account']")).click();
		sleepInSecond(3);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
		sleepInSecond(3);

		// verify information
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

	}

	@Test
	public void TC_02_Add_Address() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("button.add-address-button")).click();
		// Input infs
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Address_Company")).sendKeys(companyName);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector("button.save-address-button")).click();
		// so sanh tuyet doi
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName + " " + lastName);
		// so sanh tuong doi
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), companyName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), addressName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(cityName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(provinceName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalCode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(), countryName);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}