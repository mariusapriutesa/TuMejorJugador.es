package com.example.tumejorjugadores.ApiModel;

import java.io.Serializable;
import java.util.List;

//el contructor
public class NewsApiResponse implements Serializable {
    String status;
    int totalResults;
    List<NewsHeadline> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<NewsHeadline> getArticles() {
        return articles;
    }
}
