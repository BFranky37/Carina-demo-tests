package my_tests;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.gui.pages.CompareModelsPage;
import com.qaprosoft.carina.demo.gui.youtube_pages.HomePage;
import com.qaprosoft.carina.demo.gui.youtube_pages.WatchVideoPage;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class WebTestYoutube implements IAbstractTest {


    @Test
    @MethodOwner(owner = "Blake")
    public void testOpenHomePage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
    }

    @Test
    @MethodOwner(owner = "Blake")
    public void testCloseBannerAd() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.getBannerAd().closeAdIfPresent();
        Assert.assertFalse(homePage.getBannerAd().isUIObjectPresent(2), "Banner advert should be closed.");
    }

    @Test
    @MethodOwner(owner = "Blake")
    public void testOpenWatchVideoPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        WatchVideoPage watchVideoPage = homePage.getVideoRow().get(0).clickVideoLink();
        Assert.assertTrue(watchVideoPage.isPageOpened(), "Home page is not opened");
    }
}
