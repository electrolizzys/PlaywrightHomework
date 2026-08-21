package ge.tbc.testautomation.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.steps.CartSteps;
import ge.tbc.testautomation.steps.CheckoutSteps;
import ge.tbc.testautomation.steps.ContactSteps;
import ge.tbc.testautomation.steps.FavoritesSteps;
import ge.tbc.testautomation.steps.ForgotPasswordSteps;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.LoginSteps;
import ge.tbc.testautomation.steps.NavigationSteps;
import ge.tbc.testautomation.steps.ProductSteps;
import ge.tbc.testautomation.steps.RegisterSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

public abstract class ScenarioBaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    protected NavigationSteps navigationSteps;
    protected HomeSteps homeSteps;
    protected ProductSteps productSteps;
    protected CartSteps cartSteps;
    protected CheckoutSteps checkoutSteps;
    protected LoginSteps loginSteps;
    protected RegisterSteps registerSteps;
    protected FavoritesSteps favoritesSteps;
    protected ForgotPasswordSteps forgotPasswordSteps;
    protected ContactSteps contactSteps;

    @BeforeClass
    public void setUp() {
        PlaywrightAssertions.setDefaultAssertionTimeout(30_000);
        playwright = Playwright.create();
        playwright.selectors().setTestIdAttribute("data-test");
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        page = browserContext.newPage();
        initSteps();
        page.navigate(BASE_URL);
    }

    @AfterClass
    public void tearDown() {
        if (page != null) page.close();

        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();

        if (playwright != null) playwright.close();
    }

    private void initSteps() {
        navigationSteps = new NavigationSteps(page);
        homeSteps = new HomeSteps(page);
        productSteps = new ProductSteps(page);
        cartSteps = new CartSteps(page);
        checkoutSteps = new CheckoutSteps(page);
        loginSteps = new LoginSteps(page);
        registerSteps = new RegisterSteps(page);
        favoritesSteps = new FavoritesSteps(page);
        forgotPasswordSteps = new ForgotPasswordSteps(page);
        contactSteps = new ContactSteps(page);
    }
}
