package com.orangehrmlive.web.fluentlenium.page;

import com.orangehrmlive.web.fluentlenium.util.JavaScriptUtils;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.script.JavascriptControl;

import java.util.Objects;

public abstract class BasePage extends FluentPage {

    private static final String ELEMENT_ID = "id";

    private static final String TAG_FORM = "form";
    private static final String CLASS_MESSAGE_SUCCESS = ".success";
    private static final String CLASS_ERROR = ".error";
    private static final String CLASS_VALIDATION_ERROR = ".validation-error";


    public abstract String getPageId();

    public String currentPageId() {
        return el(TAG_FORM).attribute(ELEMENT_ID);
    }

    public boolean isPage(String pageId) {
        return Objects.equals(pageId, currentPageId());
    }

    public boolean isAtPage() {
        return isPage(getPageId());
    }

    public boolean isNotAtPage() {
        return !isAtPage();
    }


    public boolean isAjaxComplete(JavascriptControl scriptControl) {
        return JavaScriptUtils.isAjaxComplete(scriptControl);
    }

    public boolean hasMessageSuccess() {
        return find(CLASS_MESSAGE_SUCCESS).count() > 0;
    }

    public boolean hasError() {
        return find(CLASS_ERROR).count() > 0;
    }

    public boolean hasValidationError() {
        return find(CLASS_VALIDATION_ERROR).count() > 0;
    }
}

