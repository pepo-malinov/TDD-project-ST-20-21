package uni.pl.fmi.st;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSelenuimTest {
	WebDriver driver;

	@BeforeClass
	public static void setupClass() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	}

	@Before
	public void setup() {
		driver = new ChromeDriver();
	}

	@Test
	public void checkTabOverviewLabel() {
		driver.get(
				"https://bg.wikipedia.org/wiki/%D0%9D%D0%B0%D1%87%D0%B0%D0%BB%D0%BD%D0%B0_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");

		WebElement element = driver.findElement(By.id("ca-view"));
		final String result = element.getText();
		assertEquals("Преглед", result);
	}
	
	
	@Test
	public void checkSearchResult() {
		driver.get(
				"https://bg.wikipedia.org/wiki/%D0%9D%D0%B0%D1%87%D0%B0%D0%BB%D0%BD%D0%B0_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
		
		WebElement searchInput = driver.findElement(By.name("search"));
		searchInput.sendKeys("ябълка");
		WebElement searchButton = driver.findElement(By.name("go"));
		searchButton.click();
		
		final String expectedURL = "https://bg.wikipedia.org/wiki/%D0%AF%D0%B1%D1%8A%D0%BB%D0%BA%D0%B0";
		final String result = driver.getCurrentUrl();
		
		assertEquals(expectedURL, result);
		
		WebElement header = driver.findElement(By.id("firstHeading"));
		
		assertEquals("h1", header.getTagName());
		assertEquals("Ябълка", header.getText());
	}

	@After
	public void after() {
		driver.close();
	}

}
