package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginSteps {
    Page page;
    LoginPage loginPage;

    public LoginSteps(Page page) {
        this.page = page;
        loginPage = new LoginPage(page);
    }

    public LoginSteps openLoginPage() {
        loginPage.signInLink.click();
        loginPage.emailInput.waitFor();
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
        loginPage.userMenu.or(loginError).first().waitFor();
        if (loginError.count() > 0 && loginError.first().isVisible()) {
            throw new IllegalStateException("Login failed: " + loginError.innerText());
        }
        assertThat(loginPage.userMenu).isVisible();
        return this;
    }

    public LoginSteps logOut() {
        loginPage.userMenu.click();
        loginPage.signOutLink.click();
        assertThat(loginPage.signInLink).isVisible();
        return this;
    }
}
