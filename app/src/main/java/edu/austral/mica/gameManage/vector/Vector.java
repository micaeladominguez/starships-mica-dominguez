package edu.austral.mica.gameManage.vector;

import static java.lang.Math.*;

public class Vector {
   private final float x;
   private final float y;
   private final double radians;

    public float getAngle() {
        return (float) Math.toDegrees(radians);
    }
    public float getModule() { return (float) Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);}
    public Vector(float x, float y){
        this.x = x;
        this.y = y;
        double temp = Math.acos(x / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
        this.radians = temp > 2*Math.PI ? temp - 2*Math.PI : temp;
    }
    public Vector(float x, float y, double radians){
        this.x = x;
        this.y = y;
        this.radians = radians;
    }
    public Vector changeAngle(float angle){
       float degrees = getAngle();
       float new_x = (float) Math.cos(Math.toRadians(degrees + angle));
       float new_y = (float) Math.sin(Math.toRadians(degrees + angle));
       double temp = Math.toRadians(degrees + angle);
       double new_radians = temp > 2*Math.PI ? temp - 2*Math.PI : temp;
       return new Vector(new_x, new_y, new_radians);
    }
    public Vector add(Vector other) { return new Vector(x + other.x, y + other.y); }
    public Vector multiply(Float scalar) {
        return new Vector(x * scalar, y * scalar);
    }
    public Vector multiplyProjectileAndShip(Float scalar) {
        double new_x =  Math.cos(Math.toRadians(getAngle() + 90));
        double new_y =  Math.sin(Math.toRadians(getAngle() + 90));
        return new Vector((float) (new_x * scalar), (float) (new_y * scalar));
    }
    public Vector subtract(Vector other) { return new Vector(x - other.x, y - other.y); }

    public Vector asUnitary() {
        final float module = getModule();
        return new Vector(x / module, y / module);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
