package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
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

	// Tránh lặp lại code nhiều lần
	public void selectItemInDropdown(String parentCSS, String allItemCss, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentCSS)).click();

		// Chờ cho tất cả các element được load thành công
		// Chờ linh động
		// Phải lấy locator đại diện cho tất cả các item
		// Và phải lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

		// Đưa hết tất cả các item trong dropdown vào 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));

		// Tìm iteam xem đúng cái đang cần ko
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();

			// Kiểm tra text của từng item, iteam nào đúng với expect thì click vào item đó
			if (itemText.equals(expectedTextItem)) {
				tempItem.click();
				// Thoát khỏi vòng lặp nếu tìm thấy item và ko xét các item tiếp theo nữa
				break;
			}
		}

	}

	public void enterAndSelectItemInDropdown(String texBoxCss, String allItemCss, String expectedTextItem) {
		// 1. Nhập expected text item vào -> xổ ra tất cả các item của dropdown
		driver.findElement(By.cssSelector(texBoxCss)).clear();
		driver.findElement(By.cssSelector(texBoxCss)).sendKeys(expectedTextItem);
		sleepInSecond(1);

		// Và phải lấy đến thẻ chứa text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));

		// Đưa hết tất cả các item trong dropdown vào 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));

		// Tìm iteam xem đúng cái đang cần ko
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();

			// Kiểm tra text của từng item, iteam nào đúng với expect thì click vào item đó
			if (itemText.equals(expectedTextItem)) {
				tempItem.click();
				// Thoát khỏi vòng lặp nếu tìm thấy item và ko xét các item tiếp theo nữa
				break;
			}
		}

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.findElement(By.id("speed-button")).click();

		// Chờ cho tất cả các element được load thành công
		// Chờ linh động
		// Phải lấy locator đại diện cho tất cả các item
		// Và phải lấy đến thẻ chứa text
		explicitWait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));

		// Đưa hết tất cả các item trong dropdown vào 1 list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));

		// Tìm iteam xem đúng cái đang cần ko
		for (WebElement tempItem : speedDropdownItems) {
			// Kiểm tra text của từng item, iteam nào đúng với expect thì click vào item đó
			if (tempItem.getText().trim().equals("Fast")) {
				tempItem.click();
				// Thoát khỏi vòng lặp nếu tìm thấy item và ko xét các item tiếp theo nữa
				break;
			}
		}

	}

	@Test
	public void TC_02_Custom_Function() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// Muốn chọn item cho speed dropdown
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slower");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Slower");
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Faster");
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Medium");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Medium");
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Slow");
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),
				"Fast");

	}

	@Test
	public void TC_03_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInDropdown("i.dropdown.icon", "span.text", "Matt");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");

		selectItemInDropdown("i.dropdown.icon", "span.text", "Elliot Fu");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
	}

	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

	}

	@Test
	public void TC_05_VueJS_EnterandSelect() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		enterAndSelectItemInDropdown("input.search", "span.text", "Angola");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}