package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.UserData;
import ge.tbc.testautomation.pages.CheckoutPage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutSteps {
    Page page;
    CheckoutPage checkoutPage;

    public CheckoutSteps(Page page) {
        this.page = page;
        checkoutPage = new CheckoutPage(page);
    }

    @Step("Validateing the checkout sign-in step")
    public CheckoutSteps validateSignInStep() {
        checkoutPage.guestTab.waitFor();
        assertThat(checkoutPage.guestTab).isVisible();
        return this;
    }

    @Step("Contineing checkout as a guest")
    public CheckoutSteps continueAsGuest(UserData user) {
        checkoutPage.guestTab.click();
        checkoutPage.guestEmail.waitFor();
        checkoutPage.guestEmail.fill(user.email);
        checkoutPage.guestFirstName.fill(user.firstName);
        checkoutPage.guestLastName.fill(user.lastName);
        checkoutPage.guestSubmit.click();
        checkoutPage.proceedAsGuest.waitFor();
        checkoutPage.proceedAsGuest.click();
        return this;
    }

    @Step("Filling the billing address")
    public CheckoutSteps fillBillingAddress(UserData user) {
        checkoutPage.streetInput.waitFor();
        checkoutPage.countrySelect.selectOption(new SelectOption().setLabel(user.country));
        checkoutPage.postalCodeInput.fill(user.postalCode);
        checkoutPage.houseNumberInput.fill(user.houseNumber);
        waitForPostcodeLookupToFinish();
        fillIfEmpty(checkoutPage.streetInput, user.street);
        fillIfEmpty(checkoutPage.cityInput, user.city);
        fillIfEmpty(checkoutPage.stateInput, user.state);
        checkoutPage.proceedToPayment.click();
        return this;
    }

    @Step("Completing payment and confirm the order")
    public CheckoutSteps completePayment() {
        checkoutPage.paymentMethod.waitFor();
        checkoutPage.paymentMethod.selectOption(Constants.CASH_ON_DELIVERY);
        assertThat(checkoutPage.paymentMethod).hasValue(Constants.CASH_ON_DELIVERY);
        assertThat(checkoutPage.finishOrder).isEnabled();
        checkoutPage.finishOrder.click();
        checkoutPage.paymentSuccess.waitFor();
        checkoutPage.finishOrder.hover();
        checkoutPage.finishOrder.click();
        assertThat(checkoutPage.orderConfirmation).containsText(Constants.ORDER_CONFIRMATION);
        return this;
    }

    private void fillIfEmpty(Locator field, String value) {
        if (field.inputValue().isBlank()) {
            field.fill(value);
        }
    }

    private void waitForPostcodeLookupToFinish() {
        try {
            checkoutPage.postcodeLookupLoading.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(2000));
            checkoutPage.postcodeLookupLoading.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.HIDDEN));
        } catch (RuntimeException ignored) {
        }
    }
}
