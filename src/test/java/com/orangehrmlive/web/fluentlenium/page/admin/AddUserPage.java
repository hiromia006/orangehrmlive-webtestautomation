package com.orangehrmlive.web.fluentlenium.page.admin;

import com.orangehrmlive.web.fluentlenium.component.SelectComponent;
import com.orangehrmlive.web.fluentlenium.page.BasePage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;

import java.util.concurrent.TimeUnit;

public class AddUserPage extends BasePage {
    @Override
    public String getPageId() {
        return "frmSystemUser";
    }

    @Override
    public String getUrl() {
        return "/index.php/admin/saveSystemUser";
    }

    public AddUserPage selectUserRoleByIndex(int index) {
        el("select#systemUser_userType")
                .as(SelectComponent.class)
                .doSelectByIndex(index);
        return this;
    }

    public AddUserPage selectUserRoleByText(String text) {
        el("select#systemUser_userType")
                .as(SelectComponent.class)
                .doSelectByText(text);
        return this;
    }


    public AddUserPage selectUserRoleByValue(String value) {
        el("select#systemUser_userType")
                .as(SelectComponent.class)
                .doSelectByValue(value);
        return this;
    }

    public AddUserPage fillEmployeeName(String employeeName) {
        el("input#systemUser_employeeName_empName").scrollToCenter().fill().with(employeeName);
        FluentList<FluentWebElement> searchResults = find("div.ac_results ul > li");
        await().until(() -> searchResults.first().clickable());
        searchResults.get(0).scrollToCenter().waitAndClick();
        return this;
    }

    public AddUserPage fillUsername(String username) {
        FluentWebElement fluentWebElement = el("input#systemUser_userName");
        fluentWebElement.scrollToCenter().fill().with(username.toLowerCase());
        return this;
    }

    public AddUserPage selectStatus(int index) {
        el("select#systemUser_userType")
                .as(SelectComponent.class)
                .doSelectByIndex(index);
        return this;
    }

    public AddUserPage fillPassword(String password) {
        el("input#systemUser_password").scrollToCenter().fill().with(password);
        await().until(el("span#systemUser_password_strength_meter")).displayed();
        return this;
    }

    public AddUserPage fillConfirmPassword(String confirmPassword) {
        el("input#systemUser_confirmPassword").scrollToCenter().fill().with(confirmPassword);
        return this;
    }

    public UserListPage clickSaveBtn() {
        FluentWebElement btnSave = el("input#btnSave");
        await().until(btnSave).clickable();
        btnSave.scrollToCenter().waitAndClick();
        await().atMost(30, TimeUnit.SECONDS).until(this::hasMessageSuccess);
        await().atMost(10, TimeUnit.SECONDS).until(this::isNotAtPage);
        return newInstance(UserListPage.class);
    }
}
