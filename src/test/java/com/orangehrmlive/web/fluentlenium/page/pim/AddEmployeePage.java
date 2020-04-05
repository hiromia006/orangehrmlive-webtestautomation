package com.orangehrmlive.web.fluentlenium.page.pim;

import com.orangehrmlive.web.fluentlenium.page.BasePage;
import org.fluentlenium.core.domain.FluentWebElement;

import java.util.concurrent.TimeUnit;

public class AddEmployeePage extends BasePage {
    @Override
    public String getPageId() {
        return "frmAddEmp";
    }

    @Override
    public String getUrl() {
        return "/index.php/pim/addEmployee";
    }

    public AddEmployeePage fillFirstName(String firstName) {
        el("input#firstName").scrollToCenter().fill().with(firstName);
        return this;
    }

    public AddEmployeePage fillLastName(String lastName) {
        el("input#lastName").scrollToCenter().fill().with(lastName);
        return this;
    }

    public EmployeeListPage clickSaveBtn() {
        FluentWebElement btnSave = el("input#btnSave");
        await().until(() -> btnSave.clickable());
        btnSave.scrollToCenter().waitAndClick();
        await().atMost(30, TimeUnit.SECONDS).until(this::hasMessageSuccess);
        await().atMost(10, TimeUnit.SECONDS).until(this::isNotAtPage);
        return newInstance(EmployeeListPage.class);
    }
}
