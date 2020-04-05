package com.orangehrmlive.web.fluentlenium.test.pim;

import com.orangehrmlive.web.fluentlenium.page.pim.AddEmployeePage;
import com.orangehrmlive.web.fluentlenium.page.pim.EmployeeListPage;
import com.orangehrmlive.web.fluentlenium.test.BaseAuthenticatedTest;
import com.orangehrmlive.web.fluentlenium.util.Credential;
import com.thedeanda.lorem.LoremIpsum;

import static org.assertj.core.api.Assertions.assertThat;

public class AddEmployeeTest extends BaseAuthenticatedTest {
    @Override
    protected Credential credential() {
        return defaultCredential();
    }


    public void addEmployeeShouldSucceed() {
        String firstName = LoremIpsum.getInstance().getTitle(1);
        String lastName = LoremIpsum.getInstance().getTitle(1);

        EmployeeListPage userListPage = goTo(AddEmployeePage.class)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .clickSaveBtn();
        assertThat(userListPage.hasMessageSuccess()).isTrue();
    }
}
