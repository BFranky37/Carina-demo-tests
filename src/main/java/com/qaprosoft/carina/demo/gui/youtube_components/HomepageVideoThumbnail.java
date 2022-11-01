package com.qaprosoft.carina.demo.gui.youtube_components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.qaprosoft.carina.demo.gui.youtube_pages.WatchVideoPage;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomepageVideoThumbnail extends AbstractUIObject {
    @FindBy(xpath = "//yt-formatted-string[contains(@id, 'video-title')]//a")
    private ExtendedWebElement videoTitle;

    @FindBy(xpath = "//div[contains(@class, 'style-scope ytd-rich-grid-media')]//a")
    private ExtendedWebElement videoLink;

    public HomepageVideoThumbnail(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public HomepageVideoThumbnail(WebDriver driver) {
        super(driver);
    }

    public void readTitle() {
        videoTitle.getText();
    }

    public WatchVideoPage clickVideoLink() {
        videoLink.click();
        return new WatchVideoPage(driver);
    }
}
