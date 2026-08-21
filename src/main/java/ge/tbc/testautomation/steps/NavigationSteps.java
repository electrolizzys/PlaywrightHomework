package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.pages.CommonPage;

public class NavigationSteps {
    Page page;
    CommonPage commonPage;

    public NavigationSteps(Page page) {
        this.page = page;
        commonPage = new CommonPage(page);
    }

    public NavigationSteps openMenuIfCollapsed() {
        commonPage.homeLink.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.ATTACHED));
        if (commonPage.navToggler.isVisible() && !commonPage.expandedNav.isVisible()) {
            commonPage.navToggler.click();
            commonPage.expandedNav.waitFor();
        }
        return this;
    }

    public NavigationSteps openSignIn() {
        openMenuIfCollapsed();
        commonPage.signInLink.click();
        return this;
    }

    public NavigationSteps openContact() {
        openMenuIfCollapsed();
        commonPage.contactLink.click();
        return this;
    }

    public NavigationSteps openCart() {
        openMenuIfCollapsed();
        commonPage.cartLink.click();
        return this;
    }

    public NavigationSteps openFavourites() {
        openMenuIfCollapsed();
        commonPage.userMenu.click();
        commonPage.myFavouritesLink.click();
        return this;
    }

    public NavigationSteps openHome() {
        openMenuIfCollapsed();
        commonPage.homeLink.click();
        return this;
    }
}
