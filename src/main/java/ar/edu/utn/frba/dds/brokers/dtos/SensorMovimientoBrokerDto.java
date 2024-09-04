package ar.edu.utn.frba.dds.brokers.dtos;

import lombok.Getter;

@Getter
public class SensorMovimientoBrokerDto {
    // formato del mensaje: ID_SENSOR;TIMESTAMP
    public long idSensor;
    public long timestamp;

    private SensorMovimientoBrokerDto(int idSensor, long timestamp) {
        this.idSensor = idSensor;
        this.timestamp = timestamp;
    }

    public static SensorMovimientoBrokerDto fromString(String s) {
        String[] fields = s.split(";");
        return new SensorMovimientoBrokerDto(Integer.parseInt(fields[0]), Long.parseLong(fields[1]));
    }

    public String toString() {
        return String.format("ID: %d --- Timestamp: %d", this.idSensor, this.timestamp);
    }
}
