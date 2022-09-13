package com.whatislove.mydisk.dto;

import java.util.Date;
import java.util.List;

public class ElementListDTO {

    private List<ElementDTO> items;

    private Date updateDate;

    public List<ElementDTO> getItems() {
        return items;
    }

    public void setItems(List<ElementDTO> items) {
        this.items = items;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
