package ge.tbc.testautomation.steps;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.options.SelectOption;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.PrestaShopContactPage;
import io.qameta.allure.Step;

import java.nio.file.Path;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PrestaShopContactSteps {
    PrestaShopContactPage contactPage;

    public PrestaShopContactSteps(FrameLocator shop) {
        contactPage = new PrestaShopContactPage(shop);
    }
    @Step("Validating that the Contact us form is displayed")
    public PrestaShopContactSteps validateFormIsDisplayed() {
        contactPage.pageTitle.waitFor();
        assertThat(contactPage.subjectSelect).isVisible();
        assertThat(contactPage.emailInput).isVisible();
        assertThat(contactPage.messageInput).isVisible();
        assertThat(contactPage.attachmentInput).isVisible();
        assertThat(contactPage.sendButton).isVisible();
        return this;
    }

    @Step("Filling the support inquiry details")
    public PrestaShopContactSteps fillInquiry(String email, String subject, String message) {
        contactPage.subjectSelect.selectOption(new SelectOption().setLabel(subject));
        contactPage.emailInput.fill(email);
        contactPage.messageInput.fill(message);
        return this;
    }

    @Step("Attaching a file to the inquiry")
    public PrestaShopContactSteps attachFile(Path file) {
        contactPage.attachmentInput.setInputFiles(file);
        assertThat(contactPage.attachmentInput).hasValue(Pattern.compile(file.getFileName().toString()));
        return this;
    }
    @Step("Submitting the Contact us form")
    public PrestaShopContactSteps submit() {
        contactPage.sendButton.click();
        return this;
    }

    @Step("Validating successful inquiry submission")
    public PrestaShopContactSteps validateSuccessfulSubmission() {
        assertThat(contactPage.successMessage).containsText(Constants.PRESTASHOP_CONTACT_SUCCESS);
        return this;
    }
}
