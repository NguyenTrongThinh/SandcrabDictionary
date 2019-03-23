package com.solo.sandcrabdictionary.models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

@SerializedName("sourceLanguage")
@Expose
private String sourceLanguage;
@SerializedName("provider")
@Expose
private String provider;
@SerializedName("limit")
@Expose
private Integer limit;
@SerializedName("offset")
@Expose
private Integer offset;
@SerializedName("total")
@Expose
private Integer total;

public String getSourceLanguage() {
return sourceLanguage;
}

public void setSourceLanguage(String sourceLanguage) {
this.sourceLanguage = sourceLanguage;
}

public String getProvider() {
return provider;
}

public void setProvider(String provider) {
this.provider = provider;
}

public Integer getLimit() {
return limit;
}

public void setLimit(Integer limit) {
this.limit = limit;
}

public Integer getOffset() {
return offset;
}

public void setOffset(Integer offset) {
this.offset = offset;
}

public Integer getTotal() {
return total;
}

public void setTotal(Integer total) {
this.total = total;
}

}