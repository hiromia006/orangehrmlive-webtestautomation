package com.orangehrmlive.web.fluentlenium.page.admin;

import com.orangehrmlive.web.fluentlenium.component.admin.UserItem;
import com.orangehrmlive.web.fluentlenium.page.BasePage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;

public class UserListPage extends BasePage {
    @Override
    public String getPageId() {
        return "search_form";
    }

    @Override
    public String getUrl() {
        return "/index.php/admin/viewSystemUsers";
    }

    public UserListPage fillUsername(String username) {
        el("input#searchSystemUser_userName").scrollToCenter().fill().with(username);
        return this;

    }

    public UserListPage clickSearchBtn() {
        el("input#searchBtn").scrollToCenter().waitAndClick();
        return this;
    }

    public FluentList<FluentWebElement> getUsers() {
        return find("table#resultTable tbody tr");
    }

    public UserItem getFirstUser() {
        return getUsers().first().as(UserItem.class);
    }

    public UserListPage clickDeleteBtn() {
        FluentWebElement btnDelete = el("input#btnDelete");
        await().until(btnDelete).clickable();
        btnDelete.scrollToCenter().waitAndClick();
        FluentWebElement dialogDeleteBtn = el("input#dialogDeleteBtn");
        await().until(dialogDeleteBtn).clickable();
        dialogDeleteBtn.waitAndClick();
        return this;
    }
}
