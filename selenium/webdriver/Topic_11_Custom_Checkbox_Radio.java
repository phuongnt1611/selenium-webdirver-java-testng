package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	public void sleepInSecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void TC_01_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		// Case 1: thẻ input bị che nên không  thao tác được, nhưng thẻ input lại dùng để verify được vì hàm isSelected() chỉ verify cho thẻ input
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}

	//@Test
	public void TC_02_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		// Case 2: Dùng thẻ khác với input để click (span/ div/ label/ ,,,) -> đang hiển thị là được
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).isSelected());
	}

	//@Test
	public void TC_03_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		// Case 3: Dùng label để click -và input để verify 
		// dùng demo thì được còn dùng trong framework thì ko, vì 1 element phải define tới nhiều locator (dễ hiểu nhầm và mất time để bảo trì)
		
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/parent::label")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input")).isSelected());
	}

	@Test
	public void TC_04_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(3);
		// Case 4: 
		// Thẻ input bị ẩn nhưng vẫn dùng để click
		// Hàm click() của Webelement nó sẽ ko thao tác vào các element bị aanrh được
		// Nên sẽ dùng 1 hàm click của javascript để click(click vào element bị ẩn được)
		// thẻ input lại dùng để verify được -> vì hàm isSelected() nó chỉ work với thẻ input
		
		// selenium có cung cấp 1 thư viện để có thể nhúng các đoạn code JS vào kịch bản test - JavaExecutor
		
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']/preceding-sibling::div/input");
		
		sleepInSecond(3);
		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(radioButton));
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
	}
	
	@Test
	public void TC_05_Custome_Checkbox_Radio() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(3);
		// Kiểm tra xem element 
		By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		By checkBox = By.cssSelector("div[aria-label='Quảng Ngãi']");
		
		sleepInSecond(3);
		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(radioButton));
		sleepInSecond(3);
		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkBox));
		sleepInSecond(3);
		
		//verify được chọn thành công
		// Cách 1 sử dụng thuộc tính kết hợp vào cssselector và sử dụng hàm isDisplayed -> ko nên sử dụng vì có thể element đó ko hiển thị lên 
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed());
		
		// Cách 2: dùng hàm getAttribute và assertequals => nên dùng cách 2
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkBox).getAttribute("aria-checked"), "true");
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}