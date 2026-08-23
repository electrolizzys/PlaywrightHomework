package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegisterPage extends CommonPage {
    public Locator pageTitle;
    public Locator firstNameInput;
    public Locator lastNameInput;
    public Locator dateOfBirthInput;
    public Locator countrySelect;
    public Locator postalCodeInput;
    public Locator houseNumberInput;
    public Locator streetInput;
    public Locator cityInput;
    public Locator stateInput;
    public Locator phoneInput;
    public Locator emailInput;
    public Locator passwordInput;
    public Locator registerBtn;
    public Locator postcodeLookupLoading;
    public Locator emailError;
    public Locator passwordError;
    public Locator registerError;

    public RegisterPage(Page page) {
        super(page);
        pageTitle = page.getByRole(com.microsoft.playwright.options.AriaRole.HEADING, new Page.GetByRoleOptions().setName("Customer registration"));
        firstNameInput = page.getByTestId("first-name");
        lastNameInput = page.getByTestId("last-name");
        dateOfBirthInput = page.getByTestId("dob");
        countrySelect = page.getByTestId("country");
        postalCodeInput = page.getByTestId("postal_code");
        houseNumberInput = page.getByTestId("house_number");
        streetInput = page.getByTestId("street");
        cityInput = page.getByTestId("city");
        stateInput = page.getByTestId("state");
        phoneInput = page.getByTestId("phone");
        emailInput = page.getByTestId("email");
        passwordInput = page.getByTestId("password");
        registerBtn = page.getByTestId("register-submit");
        postcodeLookupLoading = page.getByTestId("postcode-lookup-loading");
        emailError = page.getByTestId("email-error");
        passwordError = page.getByTestId("password-error");
        registerError = page.getByTestId("register-error");
    }
}
