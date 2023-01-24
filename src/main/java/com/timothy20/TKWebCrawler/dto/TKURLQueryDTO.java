package com.timothy20.TKWebCrawler.dto;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TKURLQueryDTO
{
    public TKURLQueryDTO(String urlString) throws IOException {
        URL url = new URL(urlString);
        String urlProtocol = url.getProtocol();
        Object urlContent = url.getContent();

        String urlQueryString = url.getQuery();

        Arrays.stream(url.getQuery().split("&"))
                .map(this::splitQueryParameter)
                .collect(Collectors.groupingBy());
    }

    private AbstractMap.SimpleImmutableEntry<String, String> splitQueryParameter(String element)
    {
        final Integer index = element.indexOf("=");
        final String key = index > 0 ? element.substring(0, index) : element;

    }
}
