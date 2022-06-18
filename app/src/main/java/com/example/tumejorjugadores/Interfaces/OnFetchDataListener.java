package com.example.tumejorjugadores.Interfaces;

import com.example.tumejorjugadores.ApiModel.NewsHeadline;
import com.example.tumejorjugadores.ApiModel.NewsHeadline;

import java.util.List;

public interface OnFetchDataListener<T> {
    void onFetchData(List<NewsHeadline> data, String message);
    void onError(String message);

}
