package ru.volgadev.springbpgsqldocker.model;

import javax.persistence.*;

@Entity
public class Sensor {

    public Sensor(){}

    public Sensor(Long id, IndustrialObject obj){
        sensorId = id;
        industrialObject = obj;
    }

    @Id
    private Long sensorId;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "objectId")
    private IndustrialObject industrialObject;


    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public IndustrialObject getIndustrialObject() {
        return industrialObject;
    }

    public void setIndustrialObject(IndustrialObject industrialObject) {
        this.industrialObject = industrialObject;
    }
}
