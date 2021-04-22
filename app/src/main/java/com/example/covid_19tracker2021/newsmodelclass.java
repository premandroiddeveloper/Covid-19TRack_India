package com.example.covid_19tracker2021;

public class newsmodelclass {
    String news_title,news_source,news_date,news_description,image,news_Content;

    public newsmodelclass(String news_title, String news_source, String news_date, String news_description,String Image,String news_Content) {
        this.news_title = news_title;
        this.news_source = news_source;
        this.news_date = news_date;
        this.news_description = news_description;
        this.image=Image;
        this.news_Content=news_Content;
    }

    public String getImage() {
        return image;
    }

    public String getNews_Content() {
        return news_Content;
    }

    public String getNews_title() {
        return news_title;
    }

    public String getNews_source() {
        return news_source;
    }

    public String getNews_date() {
        return news_date;
    }

    public String getNews_description() {
        return news_description;
    }
}
