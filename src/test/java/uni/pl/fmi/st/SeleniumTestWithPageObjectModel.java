package uni.pl.fmi.st;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import uni.pl.fmi.st.selenium.models.WikipediaPageModel;

public class SeleniumTestWithPageObjectModel {
	WebDriver driver;
	WikipediaPageModel wikipediaPageModel;
	@Rule
	public ErrorCollector collector = new ErrorCollector();

	@BeforeClass
	public static void setupClass() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
	}

	@Before
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		wikipediaPageModel = new WikipediaPageModel(driver);
	}

	@Test
	public void checkTabOverviewLabel() {
		wikipediaPageModel.navigateToMain();
		final String result = wikipediaPageModel.getOverviewTabText();
		assertEquals("Преглед", result);
	}

	@Test
	public void checkSearchResult() {
		final String expectedURL = "https://bg.wikipedia.org/wiki/%D0%AF%D0%B1%D1%8A%D0%BB%D0%BA%D0%B0";

		wikipediaPageModel.navigateToMain();
		wikipediaPageModel.setSearchValue("ябълка");
		wikipediaPageModel.clickSearchButton();

		final String result = driver.getCurrentUrl();
		collector.checkThat(result,IsEqual.equalTo(expectedURL));
		//assertEquals(expectedURL, result);

		WebElement header = wikipediaPageModel.getHeaderElement();

		//assertEquals("h1", header.getTagName());
		collector.checkThat(header.getTagName(),IsEqual.equalTo("h2"));
		//assertEquals("Ябълка", header.getText());
		collector.checkThat(header.getText(),IsEqual.equalTo("Ябълка1"));
	}

	@After
	public void after() {
		driver.close();
	}

}
