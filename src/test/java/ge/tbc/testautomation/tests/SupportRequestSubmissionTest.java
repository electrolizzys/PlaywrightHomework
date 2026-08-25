package ge.tbc.testautomation.tests;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.UserData;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

@Feature("Submission of a support request through the contact form")
public class SupportRequestSubmissionTest extends ScenarioBaseTest {
    private UserData sender;
    private final Path invalidAttachment = Paths.get("src/test/resources/attachments/invalid.jpg");
    private final Path validAttachment = Paths.get("src/test/resources/attachments/valid.txt");

    @Test(priority = 1, description = "Access of the contact form")
    public void accessOfContactForm() {
        navigationSteps.openContact();
        contactSteps.validateFormIsDisplayed();
    }

    @Test(priority = 2, dependsOnMethods = "accessOfContactForm", description = "Submission of the empty contact form")
    public void submissionOfEmptyContactForm() {
        contactSteps.submitEmptyForm().validateRequiredFieldErrors();
    }

    @Test(priority = 3, dependsOnMethods = "submissionOfEmptyContactForm", description = "Provision of sender identity")
    public void provisionOfSenderIdentity() {
        sender = UserData.randomUser();
        contactSteps.fillIdentity(sender);
    }

    @Test(priority = 4, dependsOnMethods = "provisionOfSenderIdentity", description = "Selection of the support subject")
    public void selectionOfSupportSubject() {
        contactSteps.selectSubject(Constants.CONTACT_SUBJECT);
    }

    @Test(priority = 5, dependsOnMethods = "selectionOfSupportSubject", description = "Provision of the support message")
    public void provisionOfSupportMessage() {
        contactSteps.fillMessage(Constants.CONTACT_MESSAGE);
    }

    @Test(priority = 6, dependsOnMethods = "provisionOfSupportMessage", description = "Attachment of an unsupported file")
    public void attachmentOfUnsupportedFile() {
        contactSteps.attachFile(invalidAttachment).submit().validateInvalidAttachmentError();
    }

    @Test(priority = 7, dependsOnMethods = "attachmentOfUnsupportedFile", description = "Attachment of a valid empty text file")
    public void attachmentOfValidEmptyTextFile() {
        contactSteps.attachFile(validAttachment);
    }

    @Test(priority = 8, dependsOnMethods = "attachmentOfValidEmptyTextFile", description = "Submission of a valid contact request")
    public void submissionOfValidContactRequest() {
        contactSteps.submit().validateSuccessfulSubmission();
    }

    @Test(priority = 9, dependsOnMethods = "submissionOfValidContactRequest", description = "Availability of the form for a new submission")
    public void availabilityOfFormForNewSubmission() {
        contactSteps.validateFormReadyForNewSubmission();
    }
}
