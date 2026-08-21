package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import ge.tbc.testautomation.data.Constants;

public class CheckoutPage extends CommonPage {
    public Locator signInPrompt;
    public Locator guestTab;
    public Locator guestEmail;
    public Locator guestFirstName;
    public Locator guestLastName;
    public Locator guestSubmit;
    public Locator proceedAsGuest;
    public Locator streetInput;
    public Locator houseNumberInput;
    public Locator postalCodeInput;
    public Locator cityInput;
    public Locator stateInput;
    public Locator countrySelect;
    public Locator proceedToPayment;
    public Locator paymentMethod;
    public Locator finishOrder;
    public Locator orderConfirmation;
    public Locator paymentSuccess;
    public Locator postcodeLookupLoading;

    public CheckoutPage(Page page) {
        super(page);
        signInPrompt = page.getByText(Constants.CHECKOUT_SIGN_IN_PROMPT);
        guestTab = page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName(Constants.GUEST_TAB));
        guestEmail = page.getByTestId("guest-email");
        guestFirstName = page.getByTestId("guest-first-name");
        guestLastName = page.getByTestId("guest-last-name");
        guestSubmit = page.getByTestId("guest-submit");
        proceedAsGuest = page.getByTestId("proceed-2-guest");
        streetInput = page.getByTestId("street");
        houseNumberInput = page.getByTestId("house_number");
        postalCodeInput = page.getByTestId("postal_code");
        cityInput = page.getByTestId("city");
        stateInput = page.getByTestId("state");
        countrySelect = page.getByTestId("country");
        proceedToPayment = page.getByTestId("proceed-3");
        paymentMethod = page.getByTestId("payment-method");
        finishOrder = page.getByTestId("finish");
        orderConfirmation = page.locator("#order-confirmation");
        paymentSuccess = page.getByTestId("payment-success-message");
        postcodeLookupLoading = page.getByTestId("postcode-lookup-loading");
    }
}
