package com.example.ITBC_Logger_Endpoints.model;

import com.example.ITBC_Logger_Endpoints.enums.logType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="Log")
public class Log {

    @Id
    private int id;
    private String message;
    private logType logType;
    private LocalDateTime dateTime;

    public Log(int id, String message, com.example.ITBC_Logger_Endpoints.enums.logType logType, LocalDateTime dateTime) {
        this.id = id;
        this.message = message;
        this.logType = logType;
        this.dateTime = dateTime;
    }

    public Log(String message, com.example.ITBC_Logger_Endpoints.enums.logType logType) {
        this.message = message;
        this.logType = logType;
    }

    public Log(String message) {
        this.message = message;
    }

    public Log() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public com.example.ITBC_Logger_Endpoints.enums.logType getLogType() {
        return logType;
    }

    public void setLogType(com.example.ITBC_Logger_Endpoints.enums.logType logType) {
        this.logType = logType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", logType=" + logType +
                ", dateTime=" + dateTime +
                '}';
    }
}
