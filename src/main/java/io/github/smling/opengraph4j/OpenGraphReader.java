package io.github.smling.opengraph4j;

import io.github.smling.opengraph4j.exceptions.OpenGraphReadException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class OpenGraphReader {
    private final Map<String, String> openGraphTags;
    private final OpenGraphReaderOption openGraphReaderOption;

    public OpenGraphReader(URI uri) {
        this.openGraphReaderOption = new OpenGraphReaderOption();
        openGraphTags = readOpenGraphTags(uri);
    }

    public String getContent(String key) {
        return openGraphTags.get(key);
    }

    private Map<String, String> readOpenGraphTags(URI uri) {
        Map<String, String> result = new HashMap<>();
        HttpRequest httpRequest = HttpRequest.newBuilder(uri)
                .header("User-Agent", openGraphReaderOption.getUserAgent())
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(openGraphReaderOption.getHttpRequestTimeoutyDuration())
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString(Charset.forName(openGraphReaderOption.getHttpResponseDefaultCharsetName())));
            Document htmlResponse = Jsoup.parse(response.body());
            Element head = htmlResponse.head();
            Elements headClildElements = head.children();
            headClildElements.stream().filter(o -> "meta".equals(o.nodeName()) && o.hasAttr("property") && o.attr("property").startsWith("og:")).forEach(o -> {
                String key = o.attr("property");
                String value = o.attr("content");
                result.put(key, value);
            });
        } catch (IOException | InterruptedException e) {
            throw new OpenGraphReadException("Error occurred when reading OG tags.", e);
        }
        return result;
    }
}
