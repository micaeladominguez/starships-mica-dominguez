package edu.austral.mica.application.listeners;

public class Utils {
    public static String getColor(String id){
        if (id.contains("BLUE")) return "BLUE";
        if(id.contains("RED")) return "RED";
        return id;
    }
}
