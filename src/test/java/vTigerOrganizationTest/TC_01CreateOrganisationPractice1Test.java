package vTigerOrganizationTest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PomPages.ContactInformationPage;
import PomPages.ContactsPage;
import PomPages.CreateNewOrganizationPage;
import PomPages.CreatingNewContactPage;
import PomPages.HomePage;
import PomPages.OrganizationInfoPage;
import PomPages.OrganizationsPage;
import genericUtilities.BaseClass;

public class TC_01CreateOrganisationPractice1Test extends BaseClass {
	@Test
	public void createOrganisationPractice1Test() throws IOException, Throwable {

//		step 1: read the test data
		String ORGNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 3) + jUtil.getRandomNumber();
		String LASTNAME = eUtil.readDataFromExcelSheet("Contacts", 4, 2);

//		Click on Organizations Link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrgationLink();

//		Click on Organization LookUp Icon
		OrganizationsPage op = new OrganizationsPage(driver);
		op.clickOnCreateOrgLookUpImg();

//		Create Organization with OrgName
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME);

//		Verify the Organization is created or not
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getOrganizationHeaderText();
//		if (OrgHeader.contains(ORGNAME)) {
//			System.out.println(OrgHeader);
//			System.out.println("Organization is created");
//		} else {
//			System.out.println("Organization is not created");
//		}
		Assert.assertTrue(OrgHeader.contains(ORGNAME));

//		In HomePage Click On Contacts link 
		hp.clickOnContectsLink();

//		Click on Contacts LookUp Icon
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();

//		Create new Contact with lastName
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createNewContact(LASTNAME);

//		Verify the Contacts is Created or not
		ContactInformationPage cip = new ContactInformationPage(driver);
		String ContactHeader = cip.getContactHeaderText();
//		if (ContactHeader.contains(LASTNAME)) {
//			System.out.println(ContactHeader);
//			System.out.println("pass");
//		} else {
//			System.out.println("fail");
//		}
		Assert.assertEquals(ContactHeader.contains(LASTNAME), true);
	}
}
