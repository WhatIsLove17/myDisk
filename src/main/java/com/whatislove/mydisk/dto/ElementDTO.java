package com.whatislove.mydisk.dto;

import com.whatislove.mydisk.models.Type;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

public class ElementDTO {

    @NotEmpty
    private String id;

    private String url;

    private String parentId;

    @PositiveOrZero
    private long size;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
