package concepts.page;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class FrameByUrlTest {

    @Test
    public void testPageFrameByUrl() {

        // Initialize playwright variable to null
        Playwright playwright = null;

        // Declaring variable for a browser type
        BrowserType browserType;

        // Initialize browser variable to null
        Browser browser = null;

        try {
            // Create Playwright instance
            playwright = Playwright.create();

            // Use Chromium as the browser type
            browserType = playwright.chromium();

            // Launch the browser
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));

            // Create a browser context with touch support
            BrowserContext browserContext = browser.newContext();

            // Create a new page within the context
            Page page = browserContext.newPage();

            // Navigate to a local HTML file using the file:// protocol
            page.navigate("file:///D:/Environment_Collection/Intellij_Env/Playwright_Concepts/support/iframe.html");

            // Get a reference to a specific frame with the name "MyFrame"
            Frame frame = page.frameByUrl(Pattern.compile("content.html"));

            // Locate an image within the frame using its alt attribute value "TeddyBears"
            Locator teddyImage = frame.getByAltText("TeddyBears");

            // Check if the Teddy image is visible on the page
            System.out.println("Teddy Image Displayed? : " + teddyImage.isVisible());
        } catch (Exception ex) {
            // Print the exception stack trace for debugging
            ex.printStackTrace();
        } finally {
            // Close the browser if it's running
            if (browser != null) {
                browser.close();
            }

            // Close the Playwright object to release resources
            if (playwright != null) {
                playwright.close();
            }
        }
    }

}