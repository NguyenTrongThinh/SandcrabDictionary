package com.solo.sandcrabdictionary.servers;

import com.solo.sandcrabdictionary.models.OxfordWord;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface OxfordDictionaryInterface {

    @Headers({
            "Accept: application/json",
            "app_id: af7f5930",
            "app_key: df9201d60ffd539fe336588e9334a4bc"
    })
    @GET("{word}")
    Call<OxfordWord> lookUpWord(@Path(value = "word", encoded = true) String word);
}
