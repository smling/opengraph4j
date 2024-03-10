package io.github.smling.opengraph4j;

import java.time.Duration;

public class OpenGraphReaderOption {
    private final String userAgent;
    private final Duration httpRequestTimeoutyDuration;
    private final String httpResponseDefaultCharsetName;

    public String getUserAgent() {
        return userAgent;
    }

    public Duration getHttpRequestTimeoutyDuration() {
        return httpRequestTimeoutyDuration;
    }

    public String getHttpResponseDefaultCharsetName() {
        return httpResponseDefaultCharsetName;
    }

    public OpenGraphReaderOption() {
        userAgent = "facebookexternalhit/1.1";
        httpRequestTimeoutyDuration = Duration.ofSeconds(3);
        httpResponseDefaultCharsetName = "UTF-8";
    }
}
