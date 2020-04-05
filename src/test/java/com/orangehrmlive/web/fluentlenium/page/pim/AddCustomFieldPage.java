package com.orangehrmlive.web.fluentlenium.page.pim;

import com.orangehrmlive.web.fluentlenium.component.SelectComponent;
import com.orangehrmlive.web.fluentlenium.page.BasePage;
import org.fluentlenium.core.domain.FluentWebElement;

import java.util.concurrent.TimeUnit;

public class AddCustomFieldPage extends BasePage {
    @Override
    public String getPageId() {
        return "frmCustomField";
    }

    @Override
    public String getUrl() {
        return "/index.php/pim/listCustomFields";
    }

    public AddCustomFieldPage fillFieldName(String fieldName) {
        el("input#customField_name").scrollToCenter().fill().with(fieldName);
        return this;
    }

    public AddCustomFieldPage selectScreen(int index) {
        el("select#customField_screen")
                .as(SelectComponent.class)
                .doSelectByIndex(index);
        return this;
    }

    public AddCustomFieldPage selectType(int index) {
        el("select#customField_type")
                .as(SelectComponent.class)
                .doSelectByIndex(index);
        return this;
    }

    public CustomFieldListPage clickSaveBtn() {
        FluentWebElement btnSave = el("input#btnSave");
        await().until(() -> btnSave.clickable());
        btnSave.scrollToCenter().waitAndClick();
        await().atMost(30, TimeUnit.SECONDS).until(this::hasMessageSuccess);
        await().atMost(10, TimeUnit.SECONDS).until(this::isNotAtPage);
        return newInstance(CustomFieldListPage.class);
    }
}
