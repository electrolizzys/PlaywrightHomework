package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CommonPage {
    public Locator navToggler;
    public Locator signInLink;
    public Locator userMenu;
    public Locator myFavouritesLink;
    public Locator signOutLink;
    public Locator categoriesNav;
    public Locator handToolsLink;
    public Locator contactLink;
    public Locator cartLink;
    public Locator cartQuantity;
    public Locator homeLink;
    public Locator expandedNav;

    public CommonPage(Page page) {
        navToggler = page.locator("button.navbar-toggler");
        signInLink = page.getByTestId("nav-sign-in");
        userMenu = page.getByTestId("nav-menu");
        myFavouritesLink = page.getByTestId("nav-my-favorites");
        signOutLink = page.getByTestId("nav-sign-out");
        categoriesNav = page.getByTestId("nav-categories");
        handToolsLink = page.getByTestId("nav-hand-tools");
        contactLink = page.getByTestId("nav-contact");
        cartLink = page.getByTestId("nav-cart");
        cartQuantity = page.getByTestId("cart-quantity");
        homeLink = page.getByTestId("nav-home");
        expandedNav = page.locator("#navbarSupportedContent.show");
    }
}
