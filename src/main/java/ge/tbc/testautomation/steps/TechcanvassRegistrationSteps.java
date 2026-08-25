package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitUntilState;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.TechcanvassRegistrationPage;
import io.qameta.allure.Step;

import static ge.tbc.testautomation.data.Constants.TECHCANVASS_REGISTER_URL;

public class TechcanvassRegistrationSteps {
    Page page;
    TechcanvassRegistrationPage registrationPage;

    public TechcanvassRegistrationSteps(Page page) {
        this.page = page;
        registrationPage = new TechcanvassRegistrationPage(page);
    }
    @Step("Open the Techcanvass registration form")
    public TechcanvassRegistrationSteps openForm() {
        if (registrationPage.firstNameInput.count() > 0) return this;

        Page.NavigateOptions options = new Page.NavigateOptions()
                .setWaitUntil(WaitUntilState.DOMCONTENTLOADED).setTimeout(60_000);
        try {
            page.navigate(TECHCANVASS_REGISTER_URL, options);
        } catch (TimeoutError e) {
            page.navigate(TECHCANVASS_REGISTER_URL, options);
        }
        registrationPage.firstNameInput.waitFor();
        return this;
    }

    @Step("Fill the registration form from a database row")
    public TechcanvassRegistrationSteps fillForm(String firstName, String lastName, String gender, String model,
                                                 String address1, String address2, String city,
                                                 String contact1, String contact2) {
        registrationPage.firstNameInput.fill(firstName);
        registrationPage.lastNameInput.fill(lastName);
        registrationPage.genderRadio(gender).check();
        registrationPage.modelSelect.selectOption(new SelectOption().setLabel(model));
        registrationPage.address1Input.fill(address1);
        registrationPage.address2Input.fill(address2);
        registrationPage.cityInput.fill(city);
        registrationPage.contact1Input.fill(contact1);
        registrationPage.contact2Input.fill(contact2);
        return this;
    }

    @Step("Submit the registration form")
    public TechcanvassRegistrationSteps submit() {
        page.onceDialog(dialog -> {
            if (!dialog.message().contains(Constants.TECHCANVASS_SUCCESS_ALERT)) {
                throw new IllegalStateException("Unexpected alert: " + dialog.message());
            }
            dialog.accept();
        });
        registrationPage.registerButton.click();
        return this;
    }
}
