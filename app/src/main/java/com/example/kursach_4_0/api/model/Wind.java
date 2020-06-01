package com.example.kursach_4_0.api.model;

import com.google.gson.annotations.SerializedName;

public class Wind {
   @SerializedName("speed")
   float speed;

   @SerializedName("deg")
   float degree;

   public float getSpeed() {
       return speed;
   }

   public float getDegree() {
       return degree;
   }
}
