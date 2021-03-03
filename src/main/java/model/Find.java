package model;

public class Find {
    private String description;
    private double x;
    private double y;

    public Find(String description, double x, double y) {
        this.description = description;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
