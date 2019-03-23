package com.solo.sandcrabdictionary.models.entries;

import android.os.Parcel;
import android.os.Parcelable;

public class ExampleModelWord implements Parcelable {
    private String word;
    private String meaning;
    private String ipa;

    public String getIpa() {
        return ipa;
    }

    public void setIpa(String ipa) {
        this.ipa = ipa;
    }

    public ExampleModelWord(String word, String meaning, String ipa) {
        this.word = word;
        this.meaning = meaning;
        this.ipa = ipa;
    }

    protected ExampleModelWord(Parcel in) {
        word = in.readString();
        meaning = in.readString();
    }

    public static final Creator<ExampleModelWord> CREATOR = new Creator<ExampleModelWord>() {
        @Override
        public ExampleModelWord createFromParcel(Parcel in) {
            return new ExampleModelWord(in);
        }

        @Override
        public ExampleModelWord[] newArray(int size) {
            return new ExampleModelWord[size];
        }
    };

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
        dest.writeString(meaning);
    }
}
