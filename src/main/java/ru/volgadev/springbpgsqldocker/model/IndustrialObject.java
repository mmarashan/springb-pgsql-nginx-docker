package ru.volgadev.springbpgsqldocker.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IndustrialObject {

    public IndustrialObject(){}

    public IndustrialObject(Long id){
        objectId = id;
    }

    @Id
    private Long objectId;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }
}
