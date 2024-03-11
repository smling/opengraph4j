package io.github.smling.opengraph4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenGraphReaderTest {
    static Stream<Arguments> mockGetContentHappyCasesTestData() throws URISyntaxException {
        return Stream.of(
                Arguments.of(
                        new URI("https://www.bbc.com/future/story/20140428-the-myth-of-tech-revolutions"),
                        "og:description",
                        new String("If there’s an idea associated with breakthroughs and innovations that needs to be dropped, it's that we are witnessing a ‘revolution’, says Alice Bell.".getBytes(), StandardCharsets.UTF_8)
                ),
                Arguments.of(
                        new URI("https://i.imgur.com/dfBP5K6.gifv"),
                        "og:title",
                        "Car parks on pedestrian crossing. Pedestrian gets revenge."
                )
        );
    }

    @ParameterizedTest
    @MethodSource("mockGetContentHappyCasesTestData")
    void getContent(URI url, String tagName, Object expectedValue) {
        Assertions.assertDoesNotThrow(() -> {
            OpenGraphReader openGraphReader = new OpenGraphReader(url);
            assertNotNull(openGraphReader);
            String actual = openGraphReader.getContent(tagName);
            assertNotNull(actual);
            assertEquals(expectedValue, actual);
        });
    }
}