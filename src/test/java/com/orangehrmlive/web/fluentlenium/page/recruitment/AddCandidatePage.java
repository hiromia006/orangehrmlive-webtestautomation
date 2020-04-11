package com.orangehrmlive.web.fluentlenium.page.recruitment;

import com.orangehrmlive.web.fluentlenium.test.BaseAuthenticatedTest;
import com.orangehrmlive.web.fluentlenium.util.Credential;

public class AddCandidatePage extends BaseAuthenticatedTest {

    @Override
    protected Credential credential() {
        return defaultCredential();
    }
}
