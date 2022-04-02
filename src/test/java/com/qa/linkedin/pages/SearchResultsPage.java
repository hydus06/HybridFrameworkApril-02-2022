package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResultsPage extends BasePageWeb {

	public SearchResultsPage() {
		PageFactory.initElements(driver, this);
	}

	private Logger log = Logger.getLogger(SearchResultsPage.class);

	@FindBy(xpath = "//a[@class='app-aware-link'][contains(.,'See all people results')]")
	private WebElement seeAllPeopleResults;

	@FindBy(xpath="//div[@class='search-results-container']/h2")
	private WebElement SearchResultsText;

	@FindBy(xpath = "//svg[contains(@class,'global-nav__icon ')]")
	private WebElement homeTab;

	String SearchResultsPageTitle = "Feed | LinkedIn";  //(14) "jaya" | Search | LinkedIn   (14) "jaya" | Search | LinkedIn

	public void validateSearchResultsPageTitle() {
		log.debug("Wait for search results page title");
		wait.until(ExpectedConditions.titleContains(SearchResultsPageTitle));
		Assert.assertTrue(driver.getPageSource().contains(SearchResultsPageTitle));
	}

	public void clickOnSeeAllResultsLink() throws InterruptedException {
		log.debug("Click on See All People Results Link");
		//click(seeAllPeopleResults);
		clickUsingJsExecutor(seeAllPeopleResults);
	}

	public void clickOnHomeTab() throws InterruptedException {
		log.debug("Click On Home Tab");
		click(homeTab);
	}
	
	public long fetchResultsCount() {
		log.debug("Performing fetch results count for the given people");
		String txt = SearchResultsText.getText();
		
		log.debug("Search results text is: " + txt);
		// txt="About 640,000 results"
		String[] str = txt.split(" ");
		// str[]=["About","640,000","results"]
		
		log.debug("Results count in string format= " + str[1]);
		String finalStringCount = str[1].replace(",", "");
		long count = Long.parseLong(finalStringCount);
		return count;
	}

	public long getResultsCount() throws InterruptedException {
		validateSearchResultsPageTitle();
		log.debug("Performing Get Results count for the given people");
			if (isPresentElement(seeAllPeopleResults)) {
				clickOnSeeAllResultsLink();
				return fetchResultsCount();
			}else {
				return fetchResultsCount();
			}
		}

	public void clickHomeTab() throws Exception {
		log.debug("click on home tab");
		clickUsingJsExecutor(homeTab);
	}

}
