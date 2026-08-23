package ge.tbc.testautomation.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.PrestaShopContactSteps;
import ge.tbc.testautomation.steps.PrestaShopHomeSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class PrestaShopBaseTest {
    private final String browserName;

    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    protected PrestaShopHomeSteps homeSteps;
    protected PrestaShopContactSteps contactSteps;

    protected PrestaShopBaseTest(String browserName) {
        this.browserName = browserName;
    }

    @BeforeClass
    public void setUp() {
        PlaywrightAssertions.setDefaultAssertionTimeout(60_000);
        playwright = Playwright.create();
        browser = launchBrowser();
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        page = browserContext.newPage();
        page.setDefaultTimeout(60_000);
        FrameLocator shop = page.frameLocator("iframe[name='" + Constants.PRESTASHOP_FRAME + "']");
        homeSteps = new PrestaShopHomeSteps(page, shop);
        contactSteps = new PrestaShopContactSteps(shop);
    }

    @AfterClass
    public void tearDown() {
        if (page != null) page.close();
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    private Browser launchBrowser() {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(true);
        if ("webkit".equalsIgnoreCase(browserName)) {
            return playwright.webkit().launch(launchOptions);
        }
        return playwright.chromium().launch(launchOptions);
    }

    @Override
    public String toString() {
        return browserName;
    }
}
