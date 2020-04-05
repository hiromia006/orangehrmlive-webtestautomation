package com.orangehrmlive.web.fluentlenium.component;


import com.orangehrmlive.web.fluentlenium.util.JavaScriptUtils;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.script.JavascriptControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class BaseComponent extends FluentWebElement {

    public BaseComponent(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    public boolean isAjaxComplete(JavascriptControl scriptControl) {
        return JavaScriptUtils.isAjaxComplete(scriptControl);
    }

    public boolean isScrollComplete(JavascriptControl scriptControl) {
        return JavaScriptUtils.isScrollComplete(scriptControl);
    }

    public boolean hasText(String text) {
        return find(By.xpath("//div[contains(text(),'" + text + "')]")).count() > 0;
    }

    public void isDisplayed(FluentWebElement element) {
        await().atMost(60, TimeUnit.SECONDS).until(() -> element.displayed());
    }

    public void isClickable(FluentWebElement element) {
        await().atMost(60, TimeUnit.SECONDS).until(() -> element.clickable());
    }

    public void isPageLoaded() {
        await().atMost(60, TimeUnit.SECONDS).untilPage().isLoaded();
    }

}
