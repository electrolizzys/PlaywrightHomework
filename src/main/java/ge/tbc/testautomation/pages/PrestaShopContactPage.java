package ge.tbc.testautomation.pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import ge.tbc.testautomation.data.Constants;

public class PrestaShopContactPage {
    public Locator pageTitle;
    public Locator subjectSelect;
    public Locator emailInput;
    public Locator messageInput;
    public Locator attachmentInput;
    public Locator sendButton;
    public Locator successMessage;

    public PrestaShopContactPage(FrameLocator shop) {
        pageTitle = shop.getByRole(AriaRole.HEADING, new FrameLocator.GetByRoleOptions().setName("Contact us"));
        subjectSelect = shop.locator("#contact-us-subject-select");
        emailInput = shop.locator("#contact-us-email-input");
        messageInput = shop.locator("#contact-us-message-textarea");
        attachmentInput = shop.locator("#contact-us-attachment-input");
        sendButton = shop.locator("[name='submitMessage']");
        successMessage = shop.locator(".alert-success");
    }
}
