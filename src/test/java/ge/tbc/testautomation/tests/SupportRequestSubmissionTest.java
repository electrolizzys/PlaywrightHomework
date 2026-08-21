package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.UserData;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SupportRequestSubmissionTest extends ScenarioBaseTest {
    private UserData sender;
    private final Path invalidAttachment = Paths.get("src/test/resources/attachments/invalid.jpg");
    private final Path validAttachment = Paths.get("src/test/resources/attachments/valid.txt");

    @Test(priority = 1)
    public void accessOfContactForm() {
        navigationSteps.openContact();
        contactSteps.validateFormIsDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "accessOfContactForm")
    public void submissionOfEmptyContactForm() {
        contactSteps.submitEmptyForm().validateRequiredFieldErrors();
    }

    @Test(priority = 3, dependsOnMethods = "submissionOfEmptyContactForm")
    public void provisionOfSenderIdentity() {
        sender = UserData.randomUser();
        contactSteps.fillIdentity(sender);
    }

    @Test(priority = 4, dependsOnMethods = "provisionOfSenderIdentity")
    public void selectionOfSupportSubject() {
        contactSteps.selectSubject(Constants.CONTACT_SUBJECT);
    }

    @Test(priority = 5, dependsOnMethods = "selectionOfSupportSubject")
    public void provisionOfSupportMessage() {
        contactSteps.fillMessage(Constants.CONTACT_MESSAGE);
    }

    @Test(priority = 6, dependsOnMethods = "provisionOfSupportMessage")
    public void attachmentOfUnsupportedFile() {
        contactSteps.attachFile(invalidAttachment).submit().validateInvalidAttachmentError();
    }

    @Test(priority = 7, dependsOnMethods = "attachmentOfUnsupportedFile")
    public void attachmentOfValidEmptyTextFile() {
        contactSteps.attachFile(validAttachment);
    }

    @Test(priority = 8, dependsOnMethods = "attachmentOfValidEmptyTextFile")
    public void submissionOfValidContactRequest() {
        contactSteps.submit().validateSuccessfulSubmission();
    }

    @Test(priority = 9, dependsOnMethods = "submissionOfValidContactRequest")
    public void availabilityOfFormForNewSubmission() {
        contactSteps.validateFormReadyForNewSubmission();
    }
}
