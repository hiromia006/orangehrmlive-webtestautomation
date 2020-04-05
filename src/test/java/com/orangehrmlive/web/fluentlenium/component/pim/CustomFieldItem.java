package com.orangehrmlive.web.fluentlenium.component.pim;

import com.orangehrmlive.web.fluentlenium.component.BaseComponent;
import com.orangehrmlive.web.fluentlenium.page.pim.AddCustomFieldPage;
import com.orangehrmlive.web.fluentlenium.page.pim.CustomFieldListPage;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.openqa.selenium.WebElement;

public class CustomFieldItem extends BaseComponent {
    public CustomFieldItem(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    public String getName() {
        return el("td.fieldName a").text().trim();
    }

    public AddCustomFieldPage clickName() {
        el("td.fieldName a").scrollToCenter().waitAndClick();
        return newInstance(AddCustomFieldPage.class);
    }

    public CustomFieldListPage selectCustomField() {
        el("td input.checkbox").scrollToCenter().waitAndClick();
        return newInstance(CustomFieldListPage.class);
    }
}
