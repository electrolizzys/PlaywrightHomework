package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegisterPage extends CommonPage {
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

    public RegisterPage(Page page) {
        super(page);
        firstNameInput = page.locator("[data-test='first-name']");
        lastNameInput = page.locator("[data-test='last-name']");
        dateOfBirthInput = page.locator("[data-test='dob']");
        countrySelect = page.locator("[data-test='country']");
        postalCodeInput = page.locator("[data-test='postal_code']");
        houseNumberInput = page.locator("[data-test='house_number']");
        streetInput = page.locator("[data-test='street']");
        cityInput = page.locator("[data-test='city']");
        stateInput = page.locator("[data-test='state']");
        phoneInput = page.locator("[data-test='phone']");
        emailInput = page.locator("[data-test='email']");
        passwordInput = page.locator("[data-test='password']");
        registerBtn = page.locator("[data-test='register-submit']");
        postcodeLookupLoading = page.locator("[data-test='postcode-lookup-loading']");
    }
}
