package com.demoqa.component;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class City {

    public static @NotNull Set<String> loadCities(State state){
        Set<String> cities = new HashSet<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                        City.class.getResourceAsStream("/state_cities/"
                                + state.name().toLowerCase() + ".txt"))))){
            String city;
            while ((city = bufferedReader.readLine()) != null){
                cities.add(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;
    }

}
