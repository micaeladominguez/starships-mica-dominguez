package main.vector;

import static java.lang.Math.*;

public class Vector {
   private final float x;
   private final float y;

    public float getAngle() {return (float) (atan2(y, x) - atan2(0, 1));}
    public float getModule() { return (float) Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);}
    public Vector(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Vector changeAngle(float angle){
       float x_new = (float) (x * cos(angle) - y * sin(angle));
       float y_new = (float) (x * sin(angle) + y * cos(angle));
       return new Vector(x_new, y_new);
    }
    public Vector add(Vector other) { return new Vector(x + other.x, y + other.y); }
    public Vector multiply(Float scalar) { return new Vector(x * scalar, y * scalar); }
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
