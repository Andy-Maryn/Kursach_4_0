package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

public class MainData {
   @SerializedName("temp")
   float temperature;

   public float getTemperature() {
       return temperature;
   }
}
