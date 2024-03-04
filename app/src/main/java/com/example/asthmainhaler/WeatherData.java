package com.example.asthmainhaler;

public class WeatherData {

    private String cityName;
    private String temperature;
    private String weatherDescription;
    private String icon;

    public String getCityName(){
        return cityName;
    }
    public String getTemperature(){
        return temperature;
    }
    public String getWeatherDescription(){
        return weatherDescription;
    }
    public String getIcon(){
        return icon;
    }

    public void setCityName(String city){
        cityName=city;
    }
    public void setTemperature(String temp){
        temperature=temp;
    }
    public void setWeatherDescription(String weather){
        weatherDescription=weather;
    }
    public void setIcon(String i){
        icon = i;
    }

    public String toString(){
        return("tempetature: " + temperature
                +"\ncity name: " + cityName);
    }
}
