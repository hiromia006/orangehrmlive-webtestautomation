package com.orangehrmlive.web.fluentlenium.page.recruitment;

import com.orangehrmlive.web.fluentlenium.component.SelectComponent;
import com.orangehrmlive.web.fluentlenium.page.BasePage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;

public class AddJobVacancyPage extends BasePage {
    @Override
    public String getPageId() {
        return null;
    }

    @Override
    public String getUrl() {
        return "/index.php/recruitment/addJobVacancy";
    }

    public AddJobVacancyPage selectJobTitle(String text) {
        el("select#addJobVacancy_jobTitle")
                .as(SelectComponent.class)
                .doSelectByText(text);
        return this;
    }

    public AddJobVacancyPage fillVacancyName(String vacancyName) {
        el("input#addJobVacancy_name").scrollToCenter().fill().withText(vacancyName);
        return this;
    }

    public AddJobVacancyPage fillHiringManager(String managerName) {
        el("input#addJobVacancy_hiringManager").scrollToCenter().fill().with(managerName);
        FluentList<FluentWebElement> searchResults = find("div.ac_results ul > li");
        await().until(() -> searchResults.first().clickable());
        searchResults.get(0).scrollToCenter().waitAndClick();
        return this;
    }


    public AddJobVacancyPage fillNumberPositions(int no) {
        el("input#addJobVacancy_noOfPositions").scrollToCenter().fill().withText(String.valueOf(no));
        return this;
    }

    public AddJobVacancyPage fillDescription(String description) {
        el("textarea#addJobVacancy_description").scrollToCenter().fill().withText(description);
        return this;
    }

    public JobVacancyListPage clickSaveBtn() {
        el("input#btnSave").scrollToCenter().waitAndClick();
        return newInstance(JobVacancyListPage.class);
    }
}
