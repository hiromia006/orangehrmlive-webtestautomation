package com.orangehrmlive.web.fluentlenium.test;

import com.orangehrmlive.web.fluentlenium.page.LoginPage;
import com.orangehrmlive.web.fluentlenium.util.Credential;
import org.fluentlenium.core.hook.wait.Wait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Wait
public class LoginTest extends BaseTest {

    @Test
    public void LoginShouldSucceed() {
        Credential credential = defaultCredential();
        LoginPage loginPage = goTo(LoginPage.class, credential.getBaseUrl());
        loginPage.fillAndSubmitForm(credential.getUsername(), credential.getPassword());
        assertThat(loginPage.isNotAtPage()).isTrue();
    }
}
