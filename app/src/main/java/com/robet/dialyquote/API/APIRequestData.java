package com.robet.dialyquote.API;

import com.robet.dialyquote.Model.QuoteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
//    berisi
    @GET("quotes")
    Call<List<QuoteModel>> ardRetrive();
}
