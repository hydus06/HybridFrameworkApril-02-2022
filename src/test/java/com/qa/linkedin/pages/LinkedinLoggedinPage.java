package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{
	
	
	//create a constructor
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}
	private  Logger log = Logger.getLogger(LinkedinHomePage.class);
			
	@FindBy(xpath = "//div[contains(@class,'actor-meta break-words')]")
	private WebElement profileRailCard;
	
	@FindBy(xpath = "//img[contains(@id,'ember26')]")
	private WebElement profileImageIcon;
	
	@FindBy(xpath = "//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;
	
	@FindBy(xpath = "//input[contains(@class,'search-global-typeahead__input always-show-placeholder')]")
	private WebElement searchEditbox;
	
	String loggedInPageTitle ="Feed | LinkedIn";
	
	
	public void verifyProfileRailCard() {
		log.debug("wait for the ProfileRailCard");
		wait.until(ExpectedConditions.visibilityOf(profileRailCard));
		Assert.assertTrue(profileRailCard.isDisplayed(), "profileRailCard is not avilable");
	}
	
	public void verifyLinkedinLoggedInPageTitle() {
		log.debug("Verify The Linkedin Loggedin Page Title: "+ loggedInPageTitle );
		wait.until(ExpectedConditions.titleContains(loggedInPageTitle));
		Assert.assertTrue(driver.getTitle().contains(loggedInPageTitle));
	}
	
	public void doLogout() throws Exception {
		log.debug("Perform the logout operarion from the application");
		wait.until(ExpectedConditions.elementToBeClickable(profileImageIcon));
		wait.until(ExpectedConditions.visibilityOf(profileImageIcon));
		
		log.debug("Click On Profile Image Icon");
		click(profileImageIcon);
		
		log.debug("Wait For The Signout Link");
		wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
		wait.until(ExpectedConditions.visibilityOf(signOutLink));
		
		log.debug("Click On Signout Link");
		click(signOutLink);
		}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws Exception {
		log.debug("Perform the doPeopleSearch search: "+ keyword);
		log.debug("Clear The Content In The Search Editbox");
		clearText(searchEditbox);
		
		log.debug("Type The Search Keyword: "+ keyword + " in search Editbox");
		sendKey(searchEditbox, keyword);
		
		log.debug("Press The ENTER Key On searchEditbox");
		searchEditbox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();
	}
	
	
}
