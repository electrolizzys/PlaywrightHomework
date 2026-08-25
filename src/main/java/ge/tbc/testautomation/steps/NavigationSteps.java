package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.pages.CommonPage;
import io.qameta.allure.Step;

public class NavigationSteps {
    Page page;
    CommonPage commonPage;

    public NavigationSteps(Page page) {
        this.page = page;
        commonPage = new CommonPage(page);
    }

    @Step("Opening the navigation menu if it is collapsed")
    public NavigationSteps openMenuIfCollapsed() {
        commonPage.homeLink.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED));
        if (commonPage.navToggler.isVisible() && !commonPage.expandedNav.isVisible()) {
            commonPage.navToggler.click();
            commonPage.expandedNav.waitFor();
        }
        return this;
    }

    @Step("Opening Sign in")
    public NavigationSteps openSignIn() {
        openMenuIfCollapsed();
        commonPage.signInLink.click();
        return this;
    }

    @Step("Opening Contact")
    public NavigationSteps openContact() {
        openMenuIfCollapsed();
        commonPage.contactLink.click();
        return this;
    }

    @Step("Opening the cart")
    public NavigationSteps openCart() {
        openMenuIfCollapsed();
        commonPage.cartLink.click();
        return this;
    }

    @Step("Opening favourites from the user menu")
    public NavigationSteps openFavourites() {
        openMenuIfCollapsed();
        commonPage.userMenu.click();
        commonPage.myFavouritesLink.click();
        return this;
    }

    @Step("Opening Home")
    public NavigationSteps openHome() {
        openMenuIfCollapsed();
        commonPage.homeLink.click();
        return this;
    }
}
