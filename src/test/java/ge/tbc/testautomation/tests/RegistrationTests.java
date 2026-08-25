package ge.tbc.testautomation.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import ge.tbc.testautomation.data.RegistrationDataProvider;
import ge.tbc.testautomation.steps.TechcanvassRegistrationSteps;
import io.qameta.allure.Feature;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Feature("Registration form filled from SQL RegistrationData")
public class RegistrationTests {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    TechcanvassRegistrationSteps registrationSteps;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36"));
        page = browserContext.newPage();
        registrationSteps = new TechcanvassRegistrationSteps(page);
        registrationSteps.openForm();
    }

    @AfterClass
    public void tearDown() {
        if (page != null) page.close();
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
    @Test(dataProvider = "registrationData", dataProviderClass = RegistrationDataProvider.class,
            description = "Fill the Techcanvass registration form for each RegistrationData row")
    public void fillRegistrationFormFromDatabase(String firstName, String lastName, String gender, String model,
                                                 String address1, String address2, String city,
                                                 String contact1, String contact2) {
        registrationSteps.openForm()
                .fillForm(firstName, lastName, gender, model, address1, address2, city, contact1, contact2).submit();
    }
}
