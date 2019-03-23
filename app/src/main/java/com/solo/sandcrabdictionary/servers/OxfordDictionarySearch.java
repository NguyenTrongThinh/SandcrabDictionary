package com.solo.sandcrabdictionary.servers;

public class OxfordDictionarySearch {
    private static String base_url = "https://od-api.oxforddictionaries.com:443/api/v1/";
    public static OxfordDictionarySearchInterface getService() {
        return DictionaryRetrofitBase.getClient(base_url).create(OxfordDictionarySearchInterface.class);
    }
}
