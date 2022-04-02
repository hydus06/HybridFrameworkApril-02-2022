package com.qa.linkedin.testcases;
import org.testng.annotations.Test;
import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase {
	
private Logger log = Logger.getLogger(SearchResultsPage.class);
private String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchData.xlsx" ;

LinkedinHomePage lhmPg;
LinkedinLoggedinPage llPg;
SearchResultsPage srPg;

	@ Test(priority=1)
	public void doLoginTest() throws Exception {
		log.debug("Executing doLoginTest()");
		log.debug("Verify Linkedin Home Page Element And Title");
		lhmPg.verifyLinkedinHomePageTitle();
		lhmPg.verifyLinkedinLogo();
		
		log.debug("Click On Signin Link");
		lhmPg.clickSigninLink();
		
		log.debug("Verify Linkedin Login Page Element And Title");
		lhmPg.verifyLinkedinSigninPageHeaderText();
		lhmPg.verifyLinkedinLoginPageTitle();
		
		log.debug("Perform Login Test");
		llPg = lhmPg.doLogin(readPropertiesValue("username"), readPropertiesValue("pwd"));
		}
			
 
	@Test(dataProvider = "dp", priority=2)
  public void doSearchPeopleTest(String s) throws Exception {
		log.debug("Start doSearchPeopleTest for  :" + s);
		llPg.verifyProfileRailCard();
		llPg.verifyLinkedinLoggedInPageTitle();
		
		log.debug("Type The Value Of " +s+ " In Search Editbox");
		srPg=llPg.doPeopleSearch(s);
		
		log.debug("Fetch The Search Results Count For : "+s);
		long count = srPg.getResultsCount();
		
		log.debug("Search Results Count For " + s+ " is:" + count);
		log.debug("Click On Home Tab");
		srPg.clickOnHomeTab();
  }

  @DataProvider
  public Object[][] dp() throws InvalidFormatException, IOException {
  log.debug("Reading The Excel Sheet Data To @Dataprovider Annotation");
  Object[][] data=new ExcelUtils().getTestData(path,"Sheet1");
      
  return data;
    };
  
  @BeforeClass
  public void initializedObjects() {
	  log.debug("Initilize The Page Objects");
	  lhmPg = new LinkedinHomePage();
	  llPg = new LinkedinLoggedinPage();
	  srPg = new SearchResultsPage();
  }

 
  @AfterClass
  public void logoutTest() throws Exception {
	  log.debug("Logout From Application");
	  llPg.doLogout();
  }

}
