package com.solo.sandcrabdictionary.servers;

import com.solo.sandcrabdictionary.models.lemmatron.OxfordLemmatron;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface OxfordDictionaryLemmatronInterface {
    @Headers({
            "Accept: application/json",
            "app_id: af7f5930",
            "app_key: df9201d60ffd539fe336588e9334a4bc"
    })
    @GET("inflections/{source_lang}/{word_id}")
    Call<OxfordLemmatron> lookUpWordLemmatron(@Path(value = "source_lang", encoded = true) String sourcelanguage, @Path(value = "word_id", encoded = true) String wordid);

}
