package com.orangehrmlive.web.fluentlenium.test.recruitment;

import com.orangehrmlive.web.fluentlenium.test.BaseAuthenticatedTest;
import com.orangehrmlive.web.fluentlenium.util.Credential;

public class AddCandidateTest extends BaseAuthenticatedTest {
    @Override
    protected Credential credential() {
        return defaultCredential();
    }
}
