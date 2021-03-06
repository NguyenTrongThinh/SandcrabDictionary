package com.solo.sandcrabdictionary.models.lemmatron;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

@SerializedName("id")
@Expose
private String id;
@SerializedName("language")
@Expose
private String language;
@SerializedName("lexicalEntries")
@Expose
private List<LexicalEntry> lexicalEntries = null;
@SerializedName("word")
@Expose
private String word;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getLanguage() {
return language;
}

public void setLanguage(String language) {
this.language = language;
}

public List<LexicalEntry> getLexicalEntries() {
return lexicalEntries;
}

public void setLexicalEntries(List<LexicalEntry> lexicalEntries) {
this.lexicalEntries = lexicalEntries;
}

public String getWord() {
return word;
}

public void setWord(String word) {
this.word = word;
}

}