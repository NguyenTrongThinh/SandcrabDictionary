package com.solo.sandcrabdictionary.models.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("word")
@Expose
private String word;
@SerializedName("inflection_id")
@Expose
private String inflectionId;
@SerializedName("id")
@Expose
private String id;
@SerializedName("matchType")
@Expose
private String matchType;
@SerializedName("score")
@Expose
private Double score;
@SerializedName("matchString")
@Expose
private String matchString;
@SerializedName("region")
@Expose
private String region;

public String getWord() {
return word;
}

public void setWord(String word) {
this.word = word;
}

public String getInflectionId() {
return inflectionId;
}

public void setInflectionId(String inflectionId) {
this.inflectionId = inflectionId;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getMatchType() {
return matchType;
}

public void setMatchType(String matchType) {
this.matchType = matchType;
}

public Double getScore() {
return score;
}

public void setScore(Double score) {
this.score = score;
}

public String getMatchString() {
return matchString;
}

public void setMatchString(String matchString) {
this.matchString = matchString;
}

public String getRegion() {
return region;
}

public void setRegion(String region) {
this.region = region;
}

}