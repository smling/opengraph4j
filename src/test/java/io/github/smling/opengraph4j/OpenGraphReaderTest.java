package io.github.smling.opengraph4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class OpenGraphReaderTest {

    @Test
    void get() {
        Assertions.assertDoesNotThrow(() -> {
            OpenGraphReader openGraphReader = new OpenGraphReader(new URI("https://www.bbc.com/future/story/20140428-the-myth-of-tech-revolutions"));
            assertNotNull(openGraphReader);
            String value = openGraphReader.get("og:description");
            assertNotNull(value);
            assertEquals(
                    "If there’s an idea associated with breakthroughs and innovations that needs to be dropped, it's that we are witnessing a ‘revolution’, says Alice Bell.",
                    value
            );
        });
    }
}