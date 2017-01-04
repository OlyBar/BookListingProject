package com.example.olympiabardis.booklistingproject;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by olympiabardis on 1/2/17.
 */

public class Book implements Parcelable {
    private String mTitle;
    private String mCaption;
    private ArrayList<String> mAuthor;
    private String mPublicationDate;

    @Override
    public void writeToParcel(Parcel out, int flags){
        out.writeString(mTitle);
        out.writeString(mCaption);
        out.writeStringList(mAuthor);
        out.writeString(mPublicationDate);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    private Book (Parcel in){
        mTitle = in.readString();
        mCaption = in.readString();
        in.readStringList(mAuthor);
        mPublicationDate = in.readString();
    }

    public Book (JSONObject object){
        try {
            JSONObject volumeInfo = object.getJSONObject("volumeInfo");
            this.mTitle = volumeInfo.getString("title");
            this.mCaption = volumeInfo.getString("caption");
            this.mAuthor = new ArrayList<String>();

            JSONArray jsonArrayAuthor = volumeInfo.getJSONArray("author");
            if (jsonArrayAuthor != null){
                for(int i=0; i<jsonArrayAuthor.length();i++){
                    mAuthor.add(jsonArrayAuthor.get(i).toString());
                }
            }

            this.mPublicationDate = volumeInfo.getString("publicationDate");
        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    public String getTitle() { return mTitle;}
    public String getCaption() { return mCaption;}
    public String getPublicationDate() { return mPublicationDate;}
    public String getAuthor(){
        if(mAuthor == null || mAuthor.size() == 0) return "";
        return TextUtils.join(", ", mAuthor);
    }

    public static ArrayList<Book> fromJson(JSONArray jsonObjects){
        ArrayList<Book> users = new ArrayList<Book>();
        for(int i = 0; i < jsonObjects.length(); i++){
            try{
                users.add(new Book(jsonObjects.getJSONObject(i)));
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return users;
    }
}