package com.orangehrmlive.web.fluentlenium.component.admin;

import com.orangehrmlive.web.fluentlenium.component.BaseComponent;
import com.orangehrmlive.web.fluentlenium.page.admin.AddUserPage;
import com.orangehrmlive.web.fluentlenium.page.admin.UserListPage;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class UserItem extends BaseComponent {
    public UserItem(WebElement element, FluentControl control, ComponentInstantiator instantiator) {
        super(element, control, instantiator);
    }

    public String getUserName() {
        return el("td a").text().trim();
    }

    public AddUserPage updateUser() {
        el("td a").scrollToCenter().waitAndClick();
        FluentWebElement btnSave = el("input#btnSave");
        await().atMost(10, TimeUnit.SECONDS).until(() -> btnSave.clickable());
        btnSave.scrollToCenter().waitAndClick();
        return newInstance(AddUserPage.class);
    }

    public UserListPage selectUser() {
        el("td input[id^='ohrmList_chkSelectRecord_']").scrollToCenter().waitAndClick();
        return newInstance(UserListPage.class);
    }
}
