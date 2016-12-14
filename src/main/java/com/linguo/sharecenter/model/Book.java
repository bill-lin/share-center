package com.linguo.sharecenter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by bin on 14/12/2016.
 */
@Document(collection = "PublishedBook")
public class Book {
    @Id
    @Field("_id")
    private String id;

    @Field("guid")
    private String guid;

    @Field("AuthorName")
    private String authorName;

    @Field("countryCode")
    private String countryCode;

    @Field("thumb")
    private String thumbId;

    @Field("title")
    private String title;

    @Field("summary")
    private String summary;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getThumbId() {
        return thumbId;
    }

    public void setThumbId(String thumbId) {
        this.thumbId = thumbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
