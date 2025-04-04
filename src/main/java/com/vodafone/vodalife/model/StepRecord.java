package com.vodafone.vodalife.model;

import java.time.LocalDate;

public class StepRecord {
    private LocalDate date; // Tarih
    private int steps; // O gün atılan adım sayısı

    public StepRecord(LocalDate date, int steps) {
        this.date = date;
        this.steps = steps;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}