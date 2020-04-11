package com.orangehrmlive.web.fluentlenium.page.recruitment;

import com.orangehrmlive.web.fluentlenium.page.BasePage;

public class JobVacancyListPage extends BasePage {
    @Override
    public String getPageId() {
        return null;
    }

    @Override
    public String getUrl() {
        return "/index.php/recruitment/viewJobVacancy";
    }
}
