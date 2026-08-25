package ge.tbc.testautomation.data;

import org.testng.annotations.DataProvider;

public class BrowserProvider {
    @DataProvider(name = "browsers")
    public static Object[][] browsers() {
        return new Object[][]{
                {"chromium"},
                {"webkit"}
        };
    }
}
