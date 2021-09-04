package com.example.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JsonOrdinaryBean {

    @JsonProperty("_id")
    private String _id;

    public String get_id() {
        return _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getUsed() {
        return used;
    }

    public String getWho() {
        return who;
    }

    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("publishedAt")
    private String publishedAt;
    @JsonProperty("source")
    private String source;
    @JsonProperty("type")
    private String type;

    public JsonOrdinaryBean(String _id, String createdAt, String desc, String publishedAt, String source, String type, String url, Boolean used, String who) {
        this._id = _id;
        this.createdAt = createdAt;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.source = source;
        this.type = type;
        this.url = url;
        this.used = used;
        this.who = who;
    }

    @JsonProperty("url")
    private String url;
    @JsonProperty("used")
    private Boolean used;
    @JsonProperty("who")
    private String who;
}
