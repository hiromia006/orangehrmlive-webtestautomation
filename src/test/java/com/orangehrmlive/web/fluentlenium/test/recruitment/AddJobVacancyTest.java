package com.orangehrmlive.web.fluentlenium.test.recruitment;

import com.orangehrmlive.web.fluentlenium.page.recruitment.AddJobVacancyPage;
import com.orangehrmlive.web.fluentlenium.page.recruitment.JobVacancyListPage;
import com.orangehrmlive.web.fluentlenium.test.BaseAuthenticatedTest;
import com.orangehrmlive.web.fluentlenium.util.Credential;
import com.thedeanda.lorem.LoremIpsum;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddJobVacancyTest extends BaseAuthenticatedTest {
    @Override
    protected Credential credential() {
        return defaultCredential();
    }

    @Test
    public void addJobVacancyShouldSucceed() {
        JobVacancyListPage jobVacancyListPage = goTo(AddJobVacancyPage.class)
                .selectJobTitle("IT Manager")
                .fillVacancyName(LoremIpsum.getInstance().getTitle(5))
                .fillHiringManager("a")
                .fillNumberPositions(2)
                .fillDescription(LoremIpsum.getInstance().getParagraphs(1, 2))
                .clickSaveBtn();
        assertThat(jobVacancyListPage.hasMessageSuccess()).isTrue();
    }
}
