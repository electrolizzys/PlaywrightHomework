package ge.tbc.testautomation.steps;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.PrestaShopHomePage;
import io.qameta.allure.Step;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.PRESTASHOP_URL;

public class PrestaShopHomeSteps {
    Page page;
    PrestaShopHomePage homePage;

    public PrestaShopHomeSteps(Page page, FrameLocator shop) {
        this.page = page;
        homePage = new PrestaShopHomePage(page, shop);
    }

    @Step("Opening the PrestaShop demo storefront inside framelive")
    public PrestaShopHomeSteps openShop() {
        page.navigate(PRESTASHOP_URL);
        homePage.liveFrame.waitFor();
        homePage.header.waitFor(new Locator.WaitForOptions().setTimeout(90_000));
        return this;
    }

    @Step("Validating that the storefront is displayed")
    public PrestaShopHomeSteps validateStorefrontDisplayed() {
        assertThat(homePage.header).isVisible();
        assertThat(homePage.contactUsLink.first()).isAttached();
        return this;
    }
    @Step("Extracting the store email from the footer")
    public String extractStoreEmail() {
        homePage.footerEmail.waitFor();
        Object email = homePage.footerEmail.evaluate(
                "link => (link.getAttribute('href') || '').replace(/^mailto:/i, '').split('?')[0]");
        String storeEmail = String.valueOf(email).trim();
        if (!storeEmail.contains("@")) {
            throw new IllegalStateException("Store email was not extracted from the footer: " + storeEmail);
        }
        return storeEmail;
    }
    @Step("Opening Contact us")
    public PrestaShopHomeSteps openContactUs() {
        homePage.contactUsLink.first().click();
        return this;
    }
}
