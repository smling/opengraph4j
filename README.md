# OpenGraph4j #

OpenGraph4j is a Java 17 library for reading webpage OpenGraph protocol metadata. 

As [`opengraph-java`](https://github.com/johndeverall/opengraph-java) is no longer maintain, so I create a repository and rewrite logic to support it.

## Features ##
1. Get corresponding OG tag property value.

## Usage ##
Get specific OG tag `og:description` value.
```java
public static vod main(String[] args) {
    OpenGraphReader openGraphReader = new OpenGraphReader(new URI("https://www.bbc.com/future/story/20140428-the-myth-of-tech-revolutions"));
    String description = openGraphReader.getContent("og:description");
    System.out.println(description);
    
    // Expected output : "If there’s an idea associated with breakthroughs and innovations that needs to be dropped, it's that we are witnessing a ‘revolution’, says Alice Bell.",
}
```