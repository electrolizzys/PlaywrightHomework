package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.BrowserProvider;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.UserData;
import io.qameta.allure.Feature;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

@Feature("Submission of a customer support inquiry with an attached file")
public class CustomerSupportInquirySubmissionTest extends PrestaShopBaseTest {
    private UserData customer;
    private final Path attachment = Paths.get("src/test/resources/attachments/support-attachment.png");

    @Factory(dataProvider = "browsers", dataProviderClass = BrowserProvider.class)
    public CustomerSupportInquirySubmissionTest(String browserName) {
        super(browserName);
    }

    @Test(priority = 1, description = "Access of the shop storefront inside the live demo frame")
    public void accessOfShopStorefrontInsideLiveDemoFrame() {
        homeSteps.openShop().validateStorefrontDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "accessOfShopStorefrontInsideLiveDemoFrame",
            description = "Access of the Contact us page")
    public void accessOfContactUsPage() {
        homeSteps.extractStoreEmail();
        homeSteps.openContactUs();
        contactSteps.validateFormIsDisplayed();
    }

    @Test(priority = 3, dependsOnMethods = "accessOfContactUsPage", description = "Provision of inquiry details")
    public void provisionOfInquiryDetails() {
        customer = UserData.randomUser();
        contactSteps.fillInquiry(customer.email, Constants.PRESTASHOP_CONTACT_SUBJECT, Constants.PRESTASHOP_CONTACT_MESSAGE);
    }

    @Test(priority = 4, dependsOnMethods = "provisionOfInquiryDetails", description = "Attachment of a supporting file")
    public void attachmentOfSupportingFile() {
        contactSteps.attachFile(attachment);
    }

    @Test(priority = 5, dependsOnMethods = "attachmentOfSupportingFile", description = "Submission of the support inquiry")
    public void submissionOfSupportInquiry() {
        contactSteps.submit().validateSuccessfulSubmission();
    }
}
