package com.timothy20.TKWebCrawler.dto;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TKProductInformationURLQueryDTO
{
    public String urlPath;
    private Map<String, List<String>> queries;

    public TKProductInformationURLQueryDTO(String urlString) throws IOException {
        URL url = new URL(URLDecoder.decode(urlString, StandardCharsets.UTF_8));
        this.urlPath = url.getPath();
        this.queries = Arrays.stream(url.getQuery().split("&"))
                .map(this::splitQueryParameter)
                .collect(Collectors.groupingBy(
                        AbstractMap.SimpleImmutableEntry::getKey,
                        LinkedHashMap::new,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    private AbstractMap.SimpleImmutableEntry<String, String> splitQueryParameter(String element)
    {
        final int index = element.indexOf("=");
        final String key = index > 0 ? element.substring(0, index) : element;
        final String value = index > 0 && element.length() > index + 1 ? element.substring(index + 1) : null;

        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

    private Integer parseInteger(String text)
    {
        Pattern pattern = Pattern.compile("[^0-9]");

        if (pattern.matcher(text).find())
            return 0;

        return Integer.parseInt(text);
    }

    public Integer getCategoryNumber()
    {
        return this.queries.get("cm").get(0).equals("all") ? 0 : this.parseInteger(this.queries.get("cm").get(0));
    }

    public String getSearchType()
    {
        return this.queries.get("searchType").get(0);
    }

    public Integer getDisplaySize()
    {
        return this.parseInteger(this.queries.get("displaySize").get(0));
    }

    public Integer getPageNumber()
    {
        return this.parseInteger(this.queries.get("pageNumber").get(0));
    }

    public Integer getSearchBrandCode()
    {
        return this.parseInteger(this.queries.get("searchBrandLCode").get(0));
    }

    public void setCategoryNumber(Integer categoryNumber)
    {

    }

    public String buildURL()
    {
        this.queries.keySet().stream();
    }
}
