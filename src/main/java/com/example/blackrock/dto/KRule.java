package com.example.blackrock.dto;

import java.time.LocalDateTime;

public class KRule extends TimeWindow {

    public KRule() {}

    public KRule(LocalDateTime start, LocalDateTime end) {
        super(start, end);
    }
}