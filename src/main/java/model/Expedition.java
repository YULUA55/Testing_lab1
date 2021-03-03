package model;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Math.*;


public class Expedition {

    private String name;
    private double startX;
    private double startY;
    private double startRadius;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Find> finds;
    private static final double searchRadius = 1;

    public Expedition(String name, double startX, double startY, double startRadius) {
        this.name = name;
        this.startX = startX;
        this.startY = startY;
        this.startRadius = startRadius;
        this.startDate = LocalDate.now();
        finds = new ArrayList<>();
    }

    public String finishExpedition() {
        endDate = LocalDate.now();

        return (finds.size() != 0) ? "The Expedition \"" + name + "\" has successfully completed" : "The Expedition \"" + name + "\" has failed";


    }

    public double getArea() {

        double area = PI * pow(startRadius, 2);


        for (Find find : finds) {
            double between = sqrt(pow(find.getX() - startX, 2) + pow(find.getY() - startY, 2));
            if (startRadius < between + searchRadius) {
                area += PI * pow(searchRadius, 2);
                if (!(startRadius + searchRadius <= between)) {
                    area = area - getCommonArea(between);
                }

            }
        }

        return area;

    }

    private double getCommonArea(double between) {
        double s1;
        double s2;

        double f1 = 2 * acos((pow(startRadius, 2) - pow(searchRadius, 2) + pow(between, 2)) / (2 * startRadius * between));
        double f2 = 2 * acos((pow(searchRadius, 2) - pow(startRadius, 2) + pow(between, 2)) / (2 * searchRadius * between));

        s1 = (pow(startRadius, 2) * (f1 - sin(f1))) / 2;
        s2 = (pow(searchRadius, 2) * (f2 - sin(f2))) / 2;

        return s1 + s2;

    }

    public String getName() {
        return name;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getStartRadius() {
        return startRadius;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ArrayList<Find> getFinds() {
        return finds;
    }


}
