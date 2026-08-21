package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.UserData;
import ge.tbc.testautomation.pages.LoginPage;
import ge.tbc.testautomation.pages.RegisterPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.REGISTER_URL;

public class RegisterSteps {
    Page page;
    RegisterPage registerPage;
    LoginPage loginPage;

    public RegisterSteps(Page page) {
        this.page = page;
        registerPage = new RegisterPage(page);
        loginPage = new LoginPage(page);
    }

    public RegisterSteps openRegisterPage() {
        page.navigate(REGISTER_URL);
        registerPage.firstNameInput.waitFor();
        return this;
    }

    public RegisterSteps validateRegistrationFormIsDisplayed() {
        registerPage.pageTitle.waitFor();
        assertThat(registerPage.firstNameInput).isVisible();
        assertThat(registerPage.emailInput).isVisible();
        assertThat(registerPage.passwordInput).isVisible();
        return this;
    }

    public RegisterSteps submitInvalidData() {
        registerPage.emailInput.fill(Constants.INVALID_EMAIL);
        registerPage.passwordInput.fill(Constants.WEAK_PASSWORD);
        registerPage.registerBtn.click();
        return this;
    }

    public RegisterSteps validateInvalidRegistrationErrors() {
        assertThat(registerPage.emailError).isVisible();
        assertThat(registerPage.emailError).containsText(Constants.EMAIL_FORMAT_INVALID);
        assertThat(registerPage.passwordError).isVisible();
        return this;
    }

    public RegisterSteps fillRegistrationForm(UserData user) {
        registerPage.firstNameInput.fill(user.firstName);
        registerPage.lastNameInput.fill(user.lastName);
        registerPage.dateOfBirthInput.fill(user.dateOfBirth);
        registerPage.countrySelect.selectOption(new SelectOption().setLabel(user.country));
        registerPage.postalCodeInput.fill(user.postalCode);
        registerPage.houseNumberInput.fill(user.houseNumber);
        waitForPostcodeLookupToFinish();
        registerPage.streetInput.fill(user.street);
        registerPage.cityInput.fill(user.city);
        registerPage.stateInput.fill(user.state);
        registerPage.phoneInput.fill(user.phone);
        registerPage.emailInput.fill(user.email);
        registerPage.passwordInput.fill(user.password);
        return this;
    }

    public RegisterSteps submit() {
        registerPage.registerBtn.click();
        Locator registerError = registerPage.registerError.or(registerPage.passwordError);
        loginPage.loginBtn.or(registerError).first().waitFor();
        if (registerPage.registerError.count() > 0 && registerPage.registerError.first().isVisible()) {
            throw new IllegalStateException("Registration failed: " + registerPage.registerError.innerText());
        }
        loginPage.loginBtn.waitFor();
        return this;
    }

    public RegisterSteps validateRedirectToLogin() {
        assertThat(loginPage.loginBtn).isVisible();
        return this;
    }

    private void waitForPostcodeLookupToFinish() {
        try {
            registerPage.postcodeLookupLoading.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(2000));
            registerPage.postcodeLookupLoading.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.HIDDEN));
        } catch (RuntimeException ignored) {
        }
    }
}
