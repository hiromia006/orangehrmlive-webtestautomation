package com.orangehrmlive.web.fluentlenium.component;

import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class SelectComponent extends BaseComponent {
    public SelectComponent(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    public void doSelectByValue(String value) {
        if (isOptionsLoaded()) {
            this.scrollToCenter().fillSelect().withValue(value);
        }
    }

    public void doSelectByIndex(int index) {
        if (isOptionsLoaded()) {
            this.scrollToCenter().fillSelect().withIndex(index);
        }
    }

    public void doSelectByText(String text) {
        if (isOptionsLoaded()) {
            this.scrollToCenter().fillSelect().withText(text);
        }
    }

    public boolean isOptionsLoaded() {
        return await().atMost(60, TimeUnit.SECONDS).until(this.find("option")).size().greaterThan(1);
    }
}
