package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.UserData;
import ge.tbc.testautomation.pages.ContactPage;
import io.qameta.allure.Step;

import java.nio.file.Path;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ContactSteps {
    Page page;
    ContactPage contactPage;

    public ContactSteps(Page page) {
        this.page = page;
        contactPage = new ContactPage(page);
    }

    @Step("Validating that the contact form is displayed")
    public ContactSteps validateFormIsDisplayed() {
        contactPage.pageTitle.waitFor();
        assertThat(contactPage.firstNameInput).isVisible();
        assertThat(contactPage.submitBtn).isVisible();
        return this;
    }

    @Step("Submitting the empty contact form")
    public ContactSteps submitEmptyForm() {
        contactPage.submitBtn.click();
        return this;
    }

    @Step("Validating required field errors on the contact form")
    public ContactSteps validateRequiredFieldErrors() {
        assertThat(contactPage.firstNameError).containsText(Constants.FIRST_NAME_REQUIRED);
        assertThat(contactPage.lastNameError).containsText(Constants.LAST_NAME_REQUIRED);
        assertThat(contactPage.emailError).containsText(Constants.EMAIL_REQUIRED);
        assertThat(contactPage.messageError).containsText(Constants.MESSAGE_REQUIRED);
        return this;
    }

    @Step("Filling sender identity")
    public ContactSteps fillIdentity(UserData user) {
        contactPage.firstNameInput.fill(user.firstName);
        contactPage.lastNameInput.fill(user.lastName);
        contactPage.emailInput.fill(user.email);
        return this;
    }

    @Step("Selecting support subject {subject}")
    public ContactSteps selectSubject(String subject) {
        contactPage.subjectSelect.selectOption(new SelectOption().setLabel(subject));
        return this;
    }

    @Step("Filling the support message")
    public ContactSteps fillMessage(String message) {
        contactPage.messageInput.fill(message);
        return this;
    }

    @Step("Attaching a file to the contact form")
    public ContactSteps attachFile(Path file) {
        contactPage.attachmentInput.setInputFiles(file);
        return this;
    }

    @Step("Submitin the contact form")
    public ContactSteps submit() {
        contactPage.submitBtn.click();
        return this;
    }

    @Step("Validating the invalid attachment error")
    public ContactSteps validateInvalidAttachmentError() {
        assertThat(contactPage.attachmentError).containsText(Constants.INCORRECT_ATTACHMENT_TYPE);
        return this;
    }

    @Step("Validating successful contact submission")
    public ContactSteps validateSuccessfulSubmission() {
        assertThat(contactPage.successMessage).isVisible();
        return this;
    }

    @Step("Validating the form is ready for a new submission")
    public ContactSteps validateFormReadyForNewSubmission() {
        assertThat(contactPage.successMessage).isVisible();
        assertThat(contactPage.firstNameInput).not().isVisible();
        return this;
    }
}
