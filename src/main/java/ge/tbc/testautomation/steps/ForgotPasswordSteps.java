package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ForgotPasswordPage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ForgotPasswordSteps {
    Page page;
    ForgotPasswordPage forgotPasswordPage;

    public ForgotPasswordSteps(Page page) {
        this.page = page;
        forgotPasswordPage = new ForgotPasswordPage(page);
    }

    @Step("Validating that the forgot password page is displayed")
    public ForgotPasswordSteps validatePageIsDisplayed() {
        forgotPasswordPage.pageTitle.waitFor();
        assertThat(forgotPasswordPage.emailInput).isVisible();
        assertThat(forgotPasswordPage.submitBtn).isVisible();
        return this;
    }

    @Step("Requesting a password reset for {email}")
    public ForgotPasswordSteps requestReset(String email) {
        forgotPasswordPage.emailInput.fill(email);
        forgotPasswordPage.submitBtn.click();
        return this;
    }

    @Step("Validating password recovery confirmation")
    public ForgotPasswordSteps validateConfirmation() {
        assertThat(forgotPasswordPage.confirmation).isVisible();
        return this;
    }
}
