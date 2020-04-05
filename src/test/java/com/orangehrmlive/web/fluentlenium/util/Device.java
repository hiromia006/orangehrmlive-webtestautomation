package com.orangehrmlive.web.fluentlenium.util;

import org.openqa.selenium.Dimension;

public enum Device {
    DESKTOP(1920, 1080);
    private int width;
    private int height;
    private boolean mobile;
    private double pixelRatio;
    private String userAgent;

    Device(int width, int height) {
        this(width, height, false, 1.0, null);
    }

    Device(int width, int height, double pixelRatio, String userAgent) {
        this(width, height, true, pixelRatio, userAgent);
    }

    Device(int width, int height, boolean mobile, double pixelRatio, String userAgent) {
        this.width = width;
        this.height = height;
        this.mobile = mobile;
        this.pixelRatio = pixelRatio;
        this.userAgent = userAgent;
    }

    public Dimension getScreenSize() {
        return new Dimension(width, height);
    }

    public boolean isMobile() {
        return mobile;
    }

    public double getPixelRatio() {
        return pixelRatio;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
