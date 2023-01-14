package com.timothy20.TKWebCrawler.dao;

public class TKProductProvisionNotice
{
    private String name;
    private String materials;
    private String color;
    private String sizes;
    private String company;
    private String country;

    public TKProductProvisionNotice(String name, String materials, String color, String sizes, String company, String country)
    {
        this.name = name;
        this.materials = materials;
        this.color = color;
        this.sizes = sizes;
        this.company = company;
        this.country = country;
    }

    public String GetName()
    {
        return name;
    }

    public String GetMaterials()
    {
        return materials;
    }

    public String GetColor()
    {
        return color;
    }

    public String GetSizes()
    {
        return sizes;
    }

    public String GetCompany()
    {
        return company;
    }

    public String GetCountry()
    {
        return country;
    }
}
