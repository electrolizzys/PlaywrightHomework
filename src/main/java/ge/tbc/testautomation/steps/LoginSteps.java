package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginSteps {
    Page page;
    LoginPage loginPage;
    NavigationSteps navigationSteps;

    public LoginSteps(Page page) {
        this.page = page;
        loginPage = new LoginPage(page);
        navigationSteps = new NavigationSteps(page);
    }

    public LoginSteps openLoginPage() {
        navigationSteps.openSignIn();
        loginPage.emailInput.waitFor();
        return this;
    }

    public LoginSteps validateLoginPageIsDisplayed() {
        loginPage.emailInput.waitFor();
        assertThat(loginPage.emailInput).isVisible();
        assertThat(loginPage.passwordInput).isVisible();
        assertThat(loginPage.registerLink).isVisible();
        return this;
    }

    public LoginSteps openRegistrationForm() {
        loginPage.registerLink.click();
        return this;
    }

    public LoginSteps fillLoginCredentials(String email, String password) {
        loginPage.emailInput.waitFor();
        loginPage.emailInput.fill(email);
        loginPage.passwordInput.fill(password);
        return this;
    }

    public LoginSteps logIn() {
        loginPage.loginBtn.click();
        Locator loginError = loginPage.loginError;
        loginPage.userMenu.or(loginError).first().waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED));
        if (loginError.count() > 0 && loginError.first().isVisible()) {
            throw new IllegalStateException("Login failed: " + loginError.innerText());
        }
        assertThat(loginPage.userMenu).isAttached();
        assertThat(loginPage.signInLink).not().isAttached();
        return this;
    }

    public LoginSteps logOut() {
        navigationSteps.openMenuIfCollapsed();
        loginPage.userMenu.click();
        loginPage.signOutLink.click();
        loginPage.signInLink.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED));
        return this;
    }

    public LoginSteps validateSignedOut() {
        assertThat(loginPage.signInLink).isAttached();
        assertThat(loginPage.userMenu).not().isAttached();
        return this;
    }

    public LoginSteps openForgotPassword() {
        loginPage.forgotPasswordLink.click();
        return this;
    }
}
