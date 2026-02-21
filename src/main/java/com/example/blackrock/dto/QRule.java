package com.example.blackrock.dto;

import java.time.LocalDateTime;

public class QRule extends TimeWindow {

    private Integer fixed;

    public QRule() {}

    public QRule(LocalDateTime start, LocalDateTime end, int fixed) {
        super(start, end);
        this.fixed = fixed;
    }

    public Integer getFixed() {
        return fixed;
    }

    public void setFixed(Integer fixed) {
        this.fixed = fixed;
    }
}
