package ge.tbc.testautomation.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import ge.tbc.testautomation.data.UserData;
import ge.tbc.testautomation.steps.FavoritesSteps;
import ge.tbc.testautomation.steps.HomeSteps;
import ge.tbc.testautomation.steps.LoginSteps;
import ge.tbc.testautomation.steps.ProductSteps;
import ge.tbc.testautomation.steps.RegisterSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static ge.tbc.testautomation.data.Constants.BASE_URL;

public abstract class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    protected String email;
    protected String password;

    protected RegisterSteps registerSteps;
    protected LoginSteps loginSteps;
    protected HomeSteps homeSteps;
    protected ProductSteps productSteps;
    protected FavoritesSteps favoritesSteps;

    protected abstract boolean isHeadless();

    protected abstract boolean isolateTests();

    @BeforeClass
    public void setUp() {
        PlaywrightAssertions.setDefaultAssertionTimeout(30_000);
        playwright = Playwright.create();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(isHeadless());
        browser = playwright.chromium().launch(launchOptions);

        if (!isolateTests()) {
            createContextAndPage();
            initSteps();
            registerAndLogin();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        if (isolateTests()) {
            createContextAndPage();
            initSteps();
            registerAndLogin();
        }
    }

    @AfterMethod
    public void afterMethod() {
        if (isolateTests() && browserContext != null) browserContext.close();
    }

    @AfterClass
    public void tearDown() {
        if (!isolateTests() && page != null) {
            page.close();
        }
        if (!isolateTests() && browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    private void createContextAndPage() {
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        page = browserContext.newPage();
        page.navigate(BASE_URL);
    }

    private void initSteps() {
        registerSteps = new RegisterSteps(page);
        loginSteps = new LoginSteps(page);
        homeSteps = new HomeSteps(page);
        productSteps = new ProductSteps(page);
        favoritesSteps = new FavoritesSteps(page);
    }

    private void registerAndLogin() {
        UserData user = UserData.randomUser();
        email = user.email;
        password = user.password;
        registerSteps.openRegisterPage().fillRegistrationForm(user).submit();
        loginSteps.fillLoginCredentials(email, password).logIn();
    }
}
