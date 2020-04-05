package com.orangehrmlive.web.fluentlenium.test.admin;

import com.orangehrmlive.web.fluentlenium.page.UserRole;
import com.orangehrmlive.web.fluentlenium.page.admin.AddUserPage;
import com.orangehrmlive.web.fluentlenium.page.admin.UserListPage;
import com.orangehrmlive.web.fluentlenium.test.BaseAuthenticatedTest;
import com.orangehrmlive.web.fluentlenium.util.Credential;
import com.thedeanda.lorem.LoremIpsum;
import org.fluentlenium.core.hook.wait.Wait;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Wait
public class UserTest extends BaseAuthenticatedTest {
    @Override
    protected Credential credential() {
        return defaultCredential();
    }

    @Test
    public void addUserShouldSucceed() {
        String password = "qaqaQA123456";
        String username = "user" + LoremIpsum.getInstance().getTitle(1);

        UserListPage userListPage = goTo(AddUserPage.class)
                .selectUserRoleByIndex(1)
                .fillEmployeeName("a")
                .fillUsername(username)
                .selectStatus(1)
                .fillPassword(password)
                .fillConfirmPassword(password)
                .clickSaveBtn();
        assertThat(userListPage.hasMessageSuccess()).isTrue();

        UserListPage userListPageSecond = goTo(UserListPage.class)
                .fillUsername(username)
                .clickSearchBtn()
                .getFirstUser()
                .selectUser()
                .clickDeleteBtn();
        assertThat(userListPageSecond.hasMessageSuccess()).isTrue();
    }


    public void updateUserShouldSucceed() {
        String password = "qaqaQA123456";
        String username = "user" + LoremIpsum.getInstance().getTitle(1);

        UserListPage userListPage = goTo(AddUserPage.class)
                .selectUserRoleByText(UserRole.ESS.getKey())
                .fillEmployeeName("a")
                .fillUsername(username)
                .selectStatus(1)
                .fillPassword(password)
                .fillConfirmPassword(password)
                .clickSaveBtn();
        assertThat(userListPage.hasMessageSuccess()).isTrue();

        String usernameUpdate = "useru" + LoremIpsum.getInstance().getTitle(1);
        UserListPage userListPageSecond = goTo(UserListPage.class)
                .fillUsername(username)
                .clickSearchBtn()
                .getFirstUser()
                .updateUser()
                .fillUsername(usernameUpdate)
                .clickSaveBtn();

        assertThat(userListPageSecond.hasMessageSuccess()).isTrue();

        UserListPage userListPageThird = goTo(UserListPage.class)
                .fillUsername(username)
                .clickSearchBtn()
                .getFirstUser()
                .selectUser()
                .clickDeleteBtn();
        assertThat(userListPageThird.hasMessageSuccess()).isTrue();
    }


    @Test
    public void deleteUserShouldSucceed() {
        String password = "qaqaQA123456";
        String username = "qaqa" + LoremIpsum.getInstance().getTitle(1);
        UserListPage userListPage = goTo(AddUserPage.class)
                .selectUserRoleByValue("2")
                .fillEmployeeName("a")
                .fillUsername(username)
                .selectStatus(1)
                .fillPassword(password)
                .fillConfirmPassword(password)
                .clickSaveBtn();
        assertThat(userListPage.hasMessageSuccess()).isTrue();

        UserListPage userListPageSecond = goTo(UserListPage.class);

        int usersBefore = userListPageSecond.getUsers().count();

        userListPageSecond = userListPageSecond
                .fillUsername(username)
                .clickSearchBtn();
        assertThat(userListPageSecond.getUsers().count() > 0).isTrue();
        assertThat(userListPageSecond.getFirstUser().getUserName()).isEqualToIgnoringCase(username);

        userListPageSecond = userListPageSecond
                .getFirstUser()
                .selectUser()
                .clickDeleteBtn();
        assertThat(userListPageSecond.hasMessageSuccess()).isTrue();

        int usersAfter = goTo(UserListPage.class).getUsers().count();
        assertThat(usersAfter).isLessThan(usersBefore);
    }
}
