package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.ForgotPasswordPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ForgotPasswordSteps {
    Page page;
    ForgotPasswordPage forgotPasswordPage;

    public ForgotPasswordSteps(Page page) {
        this.page = page;
        forgotPasswordPage = new ForgotPasswordPage(page);
    }

    public ForgotPasswordSteps validatePageIsDisplayed() {
        forgotPasswordPage.pageTitle.waitFor();
        assertThat(forgotPasswordPage.emailInput).isVisible();
        assertThat(forgotPasswordPage.submitBtn).isVisible();
        return this;
    }

    public ForgotPasswordSteps requestReset(String email) {
        forgotPasswordPage.emailInput.fill(email);
        forgotPasswordPage.submitBtn.click();
        return this;
    }

    public ForgotPasswordSteps validateConfirmation() {
        assertThat(forgotPasswordPage.confirmation).isVisible();
        return this;
    }
}
