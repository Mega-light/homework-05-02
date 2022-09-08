package com.demoqa.component;


import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;

import java.util.Set;

public enum State {
    NCR(By.id("react-select-3-option-0")),
    UTTAR_PRADESH(By.id("react-select-3-option-1")),
    HARYANA(By.id("react-select-3-option-2")),
    RAJASTHAN(By.id("react-select-3-option-3"));

    private final By by;
    private final Set<String > cities;
    State(By by){
        this.by = by;
        cities = City.loadCities(this);
    }

    public By getBy() {
        return by;
    }

    public Set<String> getCities() {
        return cities;
    }

    @Override
    public @NotNull String toString() {
        String[] s = name().split("_");
        StringBuilder word = new StringBuilder();
        if (s.length > 1){
            for (String w : s) {
                word.append(w.charAt(0)).append(w.substring(1)).append(" ");
            }
        } else {
            String w = s[0];
            word.append(w.charAt(0)).append(w.substring(1));
        }
        return word.toString();
    }
}