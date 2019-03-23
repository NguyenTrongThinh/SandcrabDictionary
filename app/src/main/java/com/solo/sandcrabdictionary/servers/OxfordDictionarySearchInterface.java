package com.solo.sandcrabdictionary.servers;

import com.solo.sandcrabdictionary.models.search.OxfordSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface OxfordDictionarySearchInterface {
    @Headers({
            "Accept: application/json",
            "app_id: af7f5930",
            "app_key: df9201d60ffd539fe336588e9334a4bc"
    })
    @GET("search/{source_lang}?q={word_id}")
    Call<OxfordSearch> searchWord(@Path(value = "source_lang", encoded = true) String sourcelanguage, @Path(value = "word_id", encoded = true) String wordid);

}
