package com.solo.sandcrabdictionary.servers;

public class OxfordDictionaryLemmatron {
    private static String base_url = "https://od-api.oxforddictionaries.com:443/api/v1/";
    public static OxfordDictionaryLemmatronInterface getService() {
        return DictionaryRetrofitBase.getClient(base_url).create(OxfordDictionaryLemmatronInterface.class);
    }
}
