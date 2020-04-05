package com.orangehrmlive.web.fluentlenium.test.admin;

import com.orangehrmlive.web.fluentlenium.page.admin.AddUserPage;
import com.orangehrmlive.web.fluentlenium.page.admin.UserListPage;
import com.orangehrmlive.web.fluentlenium.test.BaseAuthenticatedTest;
import com.orangehrmlive.web.fluentlenium.util.Credential;
import com.thedeanda.lorem.LoremIpsum;
import org.fluentlenium.core.hook.wait.Wait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Wait
public class UserListTest extends BaseAuthenticatedTest {
    @Override
    protected Credential credential() {
        return defaultCredential();
    }

    @Test
    public void searchUserShouldSucceed() {
        String password = "qaqaQA123456";
        String username = "qaqa" + LoremIpsum.getInstance().getTitle(1);
        UserListPage userListPage = goTo(AddUserPage.class)
                .selectUserRoleByIndex(1)
                .fillEmployeeName("a")
                .fillUsername(username)
                .selectStatus(1)
                .fillPassword(password)
                .fillConfirmPassword(password)
                .clickSaveBtn();
        assertThat(userListPage.hasMessageSuccess()).isTrue();

        UserListPage userListPage1 = goTo(UserListPage.class)
                .fillUsername(username)
                .clickSearchBtn();

        assertThat(userListPage1.getUsers().count() > 0).isTrue();
        assertThat(userListPage1.getFirstUser().getUserName()).isEqualToIgnoringCase(username);

        UserListPage userListPageSecond = goTo(UserListPage.class)
                .fillUsername(username)
                .clickSearchBtn()
                .getFirstUser()
                .selectUser()
                .clickDeleteBtn();
        assertThat(userListPageSecond.hasMessageSuccess()).isTrue();
    }
}
