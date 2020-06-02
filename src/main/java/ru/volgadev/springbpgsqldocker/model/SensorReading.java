package ru.volgadev.springbpgsqldocker.model;

import javax.persistence.*;

@Entity
public class SensorReading {

    public SensorReading(){}

    @Id
    @GeneratedValue
    private Long id;

    private double value;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sensorId")
    private Sensor sensor;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "objectId")
    private IndustrialObject object;

    private Long time;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public IndustrialObject getObject() {
        return object;
    }

    public void setObject(IndustrialObject object) {
        this.object = object;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
