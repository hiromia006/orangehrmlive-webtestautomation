package com.orangehrmlive.web.fluentlenium.page.pim;

import com.orangehrmlive.web.fluentlenium.component.pim.CustomFieldItem;
import com.orangehrmlive.web.fluentlenium.page.BasePage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;

import java.util.concurrent.TimeUnit;

public class CustomFieldListPage extends BasePage {
    @Override
    public String getPageId() {
        return "frmList_ohrmListComponent";
    }

    @Override
    public String getUrl() {
        return "/index.php/pim/listCustomFields";
    }

    FluentList<FluentWebElement> getCustomFields() {
        return find("table#customFieldList tbody tr");
    }

    public CustomFieldItem getFirstCustomField() {
        return getCustomFields().first().as(CustomFieldItem.class);
    }


    public CustomFieldListPage clickDeleteBtn() {
        FluentWebElement btnSave = el("input#buttonRemove");
        await().until(() -> btnSave.clickable());
        btnSave.scrollToCenter().waitAndClick();
        FluentWebElement dialogDeleteBtn = el("input#dialogDeleteBtn");
        await().until(dialogDeleteBtn).clickable();
        dialogDeleteBtn.waitAndClick();
        await().atMost(30, TimeUnit.SECONDS).until(this::hasMessageSuccess);
        await().atMost(10, TimeUnit.SECONDS).until(this::isNotAtPage);
        return this;
    }
}
