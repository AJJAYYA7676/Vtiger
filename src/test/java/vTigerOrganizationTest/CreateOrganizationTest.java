package vTigerOrganizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PomPages.CreateNewOrganizationPage;
import PomPages.HomePage;
import PomPages.OrganizationInfoPage;
import PomPages.OrganizationsPage;
import genericUtilities.BaseClass;

@Listeners(genericUtilities.ListenerImplementationClass.class)
public class CreateOrganizationTest extends BaseClass {
  	@Test(groups = "SmokeTesting",retryAnalyzer = genericUtilities.RetryAnalyserImplementation.class)
	public void createOrgTest() throws EncryptedDocumentException, IOException {
//	Excel File - TestData
		String ORGNAME = eUtil.readDataFromExcelSheet("Organization", 1, 2) + jUtil.getRandomNumber();

//	Step 4: Click on create ORganizationLink
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgationLink();
		Reporter.log("Organisation is clicked", true);
		

//	Step 5: Click on create Oragaization lookup image
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgLookUpImg();
		Reporter.log("Create Organisation look up image is clicked", true);

//	Step 6: Create new Orgaization with mandatory information an save
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME);
		Reporter.log("Orgnasation is created with "+ORGNAME, true);

//	Step 7: Validate
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getOrganizationHeaderText();
//		if (OrgHeader.contains(ORGNAME)) {
//			System.out.println(OrgHeader);
//			System.out.println("pass");
//		} else {
//			System.out.println("fail");
//		}
		Assert.fail();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
	}
}
