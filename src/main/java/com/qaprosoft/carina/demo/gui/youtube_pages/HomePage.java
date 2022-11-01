package com.qaprosoft.carina.demo.gui.youtube_pages;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.qaprosoft.carina.demo.gui.pages.BrandModelsPage;
import com.qaprosoft.carina.demo.gui.youtube_components.BannerAdvert;
import com.qaprosoft.carina.demo.gui.youtube_components.HomepageVideoThumbnail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class HomePage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private BannerAdvert bannerAdvert;

    @FindBy(xpath = "//div[contains(@class, 'style-scope ytd-feed-filter-chip-bar-renderer')]//a")
    private List<ExtendedWebElement> feedFilterLinks;

    @FindBy(xpath = "//div[contains(@class, 'style-scope ytd-rich-grid-row')]//a")
    private List<HomepageVideoThumbnail> videoRow;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public void selectFeedFilter(String filter) {
        LOGGER.info("selecting '" + filter + "' filter...");
        for (ExtendedWebElement filterLink : feedFilterLinks) {
            String currentBrand = filterLink.getText();
            LOGGER.info("currentBrand: " + currentBrand);
            if (filter.equalsIgnoreCase(currentBrand)) {
                filterLink.click();

            }
        }
        throw new RuntimeException("Unable to select filter: " + filter);
    }

    public List<HomepageVideoThumbnail> getVideoRow() {
        return videoRow;
    }

    public BannerAdvert getBannerAd() {
        return new BannerAdvert(driver);
    }
}
