package ge.tbc.testautomation.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.Constants;

public class PrestaShopHomePage {
    public Locator liveFrame;
    public Locator header;
    public Locator footerEmail;
    public Locator contactUsLink;

    public PrestaShopHomePage(Page page, FrameLocator shop) {
        liveFrame = page.locator("iframe[name='" + Constants.PRESTASHOP_FRAME + "']");
        header = shop.locator("#header");
        footerEmail = shop.locator("footer a[href^='mailto:']");
        contactUsLink = shop.locator("a[href$='/contact-us']");
    }
}
