package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.BrowserProvider;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.UserData;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CustomerSupportInquirySubmissionTest extends PrestaShopBaseTest {
    private UserData customer;
    private final Path attachment = Paths.get("src/test/resources/attachments/support-attachment.png");

    @Factory(dataProvider = "browsers", dataProviderClass = BrowserProvider.class)
    public CustomerSupportInquirySubmissionTest(String browserName) {
        super(browserName);
    }

    @Test(priority = 1)
    public void accessOfShopStorefrontInsideLiveDemoFrame() {
        homeSteps.openShop().validateStorefrontDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "accessOfShopStorefrontInsideLiveDemoFrame")
    public void accessOfContactUsPage() {
        homeSteps.extractStoreEmail();
        homeSteps.openContactUs();
        contactSteps.validateFormIsDisplayed();
    }

    @Test(priority = 3, dependsOnMethods = "accessOfContactUsPage")
    public void provisionOfInquiryDetails() {
        customer = UserData.randomUser();
        contactSteps.fillInquiry(customer.email, Constants.PRESTASHOP_CONTACT_SUBJECT, Constants.PRESTASHOP_CONTACT_MESSAGE);
    }

    @Test(priority = 4, dependsOnMethods = "provisionOfInquiryDetails")
    public void attachmentOfSupportingFile() {
        contactSteps.attachFile(attachment);
    }

    @Test(priority = 5, dependsOnMethods = "attachmentOfSupportingFile")
    public void submissionOfSupportInquiry() {
        contactSteps.submit().validateSuccessfulSubmission();
    }
}
