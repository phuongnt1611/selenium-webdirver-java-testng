package webdriver;

import org.openqa.selenium.support.Color;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Button_DefaultCheckbox_RadioButton {
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
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		// Verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		String loginButtonBackground = driver.findElement(loginButton).getCssValue("background-image");
		Assert.assertTrue(loginButtonBackground.contains("rgb(224, 224, 224)"));
		
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0987654321");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
		
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		loginButtonBackground = driver.findElement(loginButton).getCssValue("background-color");
		
	    Color loginButtonBackgroundColor = Color.fromString(loginButtonBackground);
	    Assert.assertEquals(loginButtonBackgroundColor.asHex().toUpperCase(), "#C92127");
	    
		
		//Assert.assertEquals(loginButtonBackground, "");
		
	}

	@Test
	public void TC_02_Default_Checkbox_Radio() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Click chọn 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),' Anemia ')]/preceding-sibling::input")).click();
		
		// click chọn vào 1 radio 
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink \")]/preceding-sibling::input")).click();
		
		//verify cac checkbox va radio button đã được chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),' Anemia ')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\\\"I don't drink \\\")]/preceding-sibling::input")).isSelected());
		
		// Checkbox có thể bỏ chọn được nhưng radio button thì ko
	}

	//@Test
	public void TC_03_Checkbox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> allCheckbox= driver.findElements(By.cssSelector("input.form-checkbox"));
		
		for (WebElement checkBox : allCheckbox) {
			checkBox.click();
		}
		

		for (WebElement checkBox : allCheckbox) {
			Assert.assertTrue(checkBox.isSelected());
		}
		
		// Nếu gặp 1 checkbox có tên là X thì mới click
		for (WebElement checkBox : allCheckbox) {
			if(checkBox.getAttribute("value").equals("Arthritis")) {
			checkBox.click();
		}
		}
	}
	
	@Test
	public void TC_04_Default_Checkbox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		// Click chọn 1 checkbox
		if (!driver.findElement(By.xpath("////label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		//verify checkbox đã được chọn rồi
		Assert.assertTrue(driver.findElement(By.xpath("////label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		// bỏ chọn 
		if(driver.findElement(By.xpath("////label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		// Verify checkbox đã được bỏ chọn
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),\\\"I don't drink \\\")]/preceding-sibling::input")).isSelected());
		
		// Checkbox có thể bỏ chọn được nhưng radio button thì ko
	}

	public void checkToCheckbox(By by) {
		if(!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void unCheckToCheckbox(By by) {
		if(driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}