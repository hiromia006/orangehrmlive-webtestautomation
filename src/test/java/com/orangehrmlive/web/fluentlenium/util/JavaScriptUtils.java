package com.orangehrmlive.web.fluentlenium.util;

import org.fluentlenium.core.script.JavascriptControl;

public class JavaScriptUtils {
    public static boolean isAjaxComplete(JavascriptControl scriptControl) {
        return scriptControl.executeScript("return (window.jQuery != null) && (jQuery.active === 0);").getBooleanResult();
    }

    public static boolean isScrollComplete(JavascriptControl scriptControl) {
        scriptControl.executeScript("var isScrolling; " +
                "window.addEventListener('scroll', function () { " +
                "document.getElementsByTagName('body')[0].setAttribute('scroll', 0); " +
                "window.clearTimeout( isScrolling ); " +
                "isScrolling = setTimeout(function() { " +
                "document.getElementsByTagName('body')[0].setAttribute('scroll', 1); }, 66);" +
                "}, false);");
        return scriptControl.executeScript("return document.getElementsByTagName('body')[0].getAttribute('scroll') == 1").getBooleanResult();
    }
}

