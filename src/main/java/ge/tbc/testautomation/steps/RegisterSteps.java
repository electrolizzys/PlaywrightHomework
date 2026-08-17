package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.data.UserData;
import ge.tbc.testautomation.pages.RegisterPage;

import static ge.tbc.testautomation.data.Constants.REGISTER_URL;

public class RegisterSteps {
    Page page;
    RegisterPage registerPage;

    public RegisterSteps(Page page) {
        this.page = page;
        registerPage = new RegisterPage(page);
    }

    public RegisterSteps openRegisterPage() {
        page.navigate(REGISTER_URL);
        registerPage.firstNameInput.waitFor();
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
        Locator registerError = page.locator("[data-test='register-error'], [data-test='password-error']");
        Locator loginSubmit = page.locator("[data-test='login-submit']");
        loginSubmit.or(registerError).first().waitFor();
        if (registerError.count() > 0 && registerError.first().isVisible()) {
            throw new IllegalStateException("Registration failed: " + registerError.first().innerText());
        }
        loginSubmit.waitFor();
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
