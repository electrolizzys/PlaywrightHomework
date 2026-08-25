package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class TechcanvassRegistrationPage {
    public Locator firstNameInput;
    public Locator lastNameInput;
    public Locator modelSelect;
    public Locator address1Input;
    public Locator address2Input;
    public Locator cityInput;
    public Locator contact1Input;
    public Locator contact2Input;
    public Locator registerButton;
    private final Page page;

    public TechcanvassRegistrationPage(Page page) {
        this.page = page;
        Locator textInputs = page.locator("form fieldset input[type='text']");
        firstNameInput = textInputs.nth(0);
        lastNameInput = textInputs.nth(1);
        address1Input = textInputs.nth(2);
        address2Input = textInputs.nth(3);
        cityInput = textInputs.nth(4);
        contact1Input = textInputs.nth(5);
        contact2Input = textInputs.nth(6);
        modelSelect = page.locator("select[name='model']");
        registerButton = page.locator("input[type='submit']");
    }
    public Locator genderRadio(String gender) {
        return page.locator("input[name='gender'][value='" + gender.toLowerCase() + "']");
    }
}
