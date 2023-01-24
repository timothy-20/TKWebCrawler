package com.timothy20.TKWebCrawler.dto;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class TKProductInformationURLDTO
{
    private URL url;

    public TKProductInformationURLDTO(String urlString) throws IOException
    {
        this.url = new URL(URLDecoder.decode(urlString, StandardCharsets.UTF_8));

    }


}
