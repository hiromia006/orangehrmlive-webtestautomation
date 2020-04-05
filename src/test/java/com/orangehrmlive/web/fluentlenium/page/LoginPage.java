package com.orangehrmlive.web.fluentlenium.page;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePage {

    public static final String PAGE_ID = "frmLogin";

    @Override
    public String getPageId() {
        return PAGE_ID;
    }

    @Override
    public String getUrl() {
        return "/index.php/auth/login";
    }

    public LoginPage fillAndSubmitForm(String username, String password) {
        el("input#txtUsername").fill().with(username);
        el("input#txtPassword").fill().with(password);
        el("input#btnLogin")
                .scrollToCenter()
                .click();
        await().until(this::isAjaxComplete);
        await().atMost(10, TimeUnit.SECONDS).until(this::isNotAtPage);
        await().atMost(10, TimeUnit.SECONDS).untilPage().isLoaded();
        return this;
    }

}

