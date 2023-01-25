package com.timothy20.TKWebCrawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TKProductInformationURLBuilder
{
    final private String baseURLString;
    final private Map<String, Object> queries;

    public TKProductInformationURLBuilder(String protocol, String host, String path)
    {
        this.baseURLString = String.format(protocol, host, path);
        this.queries = new HashMap<>(5);
    }

    public TKProductInformationURLBuilder setCategoryNumber(Integer categoryNumber)
    {
        this.queries.put("cm", categoryNumber);
        return this;
    }

    public TKProductInformationURLBuilder setSearchType(String type)
    {
        this.queries.put("searchType", type);
        return this;
    }

    public TKProductInformationURLBuilder setDisplaySize(Integer size)
    {
        this.queries.put("displaySize", size);
        return this;
    }

    public TKProductInformationURLBuilder setPageNumber(Integer pageNumber)
    {
        this.queries.put("pageNumber", pageNumber);
        return this;
    }

    public TKProductInformationURLBuilder setSearchBrandCode(Integer brandCode)
    {
        this.queries.put("searchBrandCode", brandCode);
        return this;
    }

    public URL build() throws MalformedURLException
    {
        String urlString = this.queries.keySet().stream()
                .map(key -> new StringBuffer(key).append("=").append(this.queries.get(key)))
                .collect(Collectors.joining("&", this.baseURLString, ""));

        return new URL(urlString);
    }
}
