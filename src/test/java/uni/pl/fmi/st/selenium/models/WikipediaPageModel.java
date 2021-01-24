package uni.pl.fmi.st.selenium.models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Wikipedia Page object Model
 * 
 * @author pepo
 *
 */
public class WikipediaPageModel {

	private WebDriver driver;

	@FindBy(name = "search")
	WebElement searchInput;

	@FindBy(name = "go")
	WebElement searchButton;

	@FindBy(id = "ca-view")
	WebElement overviewTabHeader;

	/**
	 * Constructor Initialize instance of Wikipedia Page object Model
	 * 
	 * @param driver
	 */
	public WikipediaPageModel(final WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToMain() {
		driver.get(
				"https://bg.wikipedia.org/wiki/%D0%9D%D0%B0%D1%87%D0%B0%D0%BB%D0%BD%D0%B0_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0");
	}

	public void setSearchValue(String searchValue) {
		searchInput.clear();
		searchInput.sendKeys(searchValue);
	}

	public void clickSearchButton() {
		searchButton.click();
	}

	public WebElement getHeaderElement() {
		return driver.findElement(By.id("firstHeading"));
	}

	public String getOverviewTabText() {
		return overviewTabHeader.getText();
	}

}
