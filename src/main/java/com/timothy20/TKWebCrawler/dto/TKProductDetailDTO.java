package com.timothy20.TKWebCrawler.dto;

public class TKProductDetailDTO
{
    private String brandName;
    private String name;
    private String code;
    private Integer tagPrice;
    private Integer salePrice;

    public TKProductDetailDTO(String brandName, String name, String code, Integer tagPrice, Integer salePrice)
    {
        this.brandName = brandName;
        this.name = name;
        this.code = code;
        this.tagPrice = tagPrice;
        this.salePrice = salePrice;
    }

    public String GetBrandName()
    {
        return brandName;
    }

    public String GetName()
    {
        return name;
    }

    public String GetCode()
    {
        return code;
    }

    public Integer GetTagPrice()
    {
        return tagPrice;
    }

    public Integer GetSalePrice()
    {
        return salePrice;
    }
}
