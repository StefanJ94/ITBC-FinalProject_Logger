package com.example.ITBC_Logger_Endpoints.model;

import com.example.ITBC_Logger_Endpoints.enums.LogType;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="Log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;
    private String message;
    private LogType logType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDate dateTime;
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Log(int logId, String message, LogType logType, LocalDate dateTime, UUID id) {
        this.logId = logId;
        this.message = message;
        this.logType = logType;
        this.dateTime = dateTime;
        this.id = id;
        User user;
    }

    public Log(String message, LogType logType, LocalDate dateTime, UUID id) {
        this.message = message;
        this.logType = logType;
        this.dateTime = dateTime;
        this.id = id;
        User user;
    }

    public Log(String message) {
        this.message = message;
    }

    public Log() {
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", message='" + message + '\'' +
                ", logType=" + logType +
                ", dateTime=" + dateTime +
                ", id=" + id +
                '}';
    }
}
