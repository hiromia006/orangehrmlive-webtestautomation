package com.orangehrmlive.web.fluentlenium.test.pim;

import com.orangehrmlive.web.fluentlenium.page.pim.AddCustomFieldPage;
import com.orangehrmlive.web.fluentlenium.page.pim.CustomFieldListPage;
import com.orangehrmlive.web.fluentlenium.test.BaseAuthenticatedTest;
import com.orangehrmlive.web.fluentlenium.util.Credential;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomFieldTest extends BaseAuthenticatedTest {
    @Override
    protected Credential credential() {
        return defaultCredential();
    }

    @Test
    public void addUserShouldSucceed() {
        String fieldName = LoremIpsum.getInstance().getTitle(2);
        CustomFieldListPage customFieldListPage = goTo(AddCustomFieldPage.class)
                .fillFieldName(fieldName)
                .selectScreen(1)
                .selectType(1)
                .clickSaveBtn();
        assertThat(customFieldListPage.hasMessageSuccess()).isTrue();
    }

    @Test
    public void updateUserShouldSucceed() {
        String fieldName = LoremIpsum.getInstance().getTitle(2);
        CustomFieldListPage customFieldListPage = goTo(AddCustomFieldPage.class)
                .fillFieldName(fieldName)
                .selectScreen(1)
                .selectType(1)
                .clickSaveBtn();
        assertThat(customFieldListPage.hasMessageSuccess()).isTrue();

        String fieldNameUpdate = LoremIpsum.getInstance().getTitle(2);
        CustomFieldListPage customFieldListPageSecond = goTo(CustomFieldListPage.class)
                .getFirstCustomField()
                .clickName()
                .fillFieldName(fieldNameUpdate)
                .clickSaveBtn();
        assertThat(customFieldListPageSecond.hasMessageSuccess()).isTrue();
    }

    @Test
    public void deleteUserShouldSucceed() {
        CustomFieldListPage customFieldListPageSecond = goTo(CustomFieldListPage.class)
                .getFirstCustomField()
                .selectCustomField()
                .clickDeleteBtn();
        assertThat(customFieldListPageSecond.hasMessageSuccess()).isTrue();
    }
}
