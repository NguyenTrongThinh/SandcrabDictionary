package com.solo.sandcrabdictionary.servers;

public class OxfordDictionaryClient {
    private static String base_url = "https://od-api.oxforddictionaries.com:443/api/v1/entries/en/";
    public static OxfordDictionaryInterface getService() {
        return DictionaryRetrofitBase.getClient(base_url).create(OxfordDictionaryInterface.class);
    }
}
