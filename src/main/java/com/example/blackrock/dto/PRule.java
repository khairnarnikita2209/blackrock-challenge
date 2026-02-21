package com.example.blackrock.dto;

import java.time.LocalDateTime;

public class PRule extends TimeWindow {

    private Integer extra;

    public PRule() {}

    public PRule(LocalDateTime start, LocalDateTime end, int extra) {
        super(start, end);
        this.extra = extra;
    }

    // getters & setters

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }
}
