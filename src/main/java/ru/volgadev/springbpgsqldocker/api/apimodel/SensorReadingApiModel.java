package ru.volgadev.springbpgsqldocker.api.apimodel;

import ru.volgadev.springbpgsqldocker.model.SensorReading;

import javax.validation.constraints.NotNull;

public class SensorReadingApiModel {
    @NotNull
    Long objectId;
    @NotNull
    Long sensorId;
    @NotNull
    Long time;
    double value;

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SensorReadingApiModel{" +
                "objectId=" + objectId +
                ", sensorId=" + sensorId +
                ", time=" + time +
                ", value=" + value +
                '}';
    }


    // TODO: нарушение SPR, мб убрать отсюда
    public static SensorReadingApiModel fromSensorReading(SensorReading reading){
        SensorReadingApiModel sensorReadingApiModel = new SensorReadingApiModel();
        sensorReadingApiModel.setObjectId(reading.getObject().getObjectId());
        sensorReadingApiModel.setSensorId(reading.getSensor().getSensorId());
        sensorReadingApiModel.setTime(reading.getTime());
        sensorReadingApiModel.setValue(reading.getValue());
        return sensorReadingApiModel;
    }

}
