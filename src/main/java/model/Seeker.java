package model;

import java.util.ArrayList;

public class Seeker {

    private String name;
    private String surname;
    private ArrayList<Expedition> expeditions;

    public Seeker(String name, String surname) {
        this.name = name;
        this.surname = surname;
        expeditions = new ArrayList<>();
    }

    public void createExpedition(String name, double startX, double startY, double startRadius) throws IllegalStateException {
        if (expeditions.isEmpty() || expeditions.get(expeditions.size() - 1).getEndDate() != null) {
            expeditions.add(new Expedition(name, startX, startY, startRadius));
        } else
            throw new IllegalStateException("You can't create a new expedition until you complete the previous one");
    }

    public String finishExpedition() {
        Expedition lastExpedition = expeditions.get(expeditions.size() - 1);
        if (lastExpedition.getEndDate() == null)
            return lastExpedition.finishExpedition();
        return "You've already finished last expedition";

    }

    public String findSomething(String description, double x, double y) throws IllegalStateException {
        Find newFind = new Find(description, x, y);
        Expedition lastExpedition = expeditions.get(expeditions.size() - 1);
        if (lastExpedition.getEndDate() == null) {
            lastExpedition.getFinds().add(newFind);
            return "Congratulations! It's your " + lastExpedition.getFinds().size() + " find in this expedition";
        } else {
            throw new IllegalStateException("You're not in an expedition anymore. You can't find something right now");

        }
    }

    public double getSquareOfLastExpedition() {
        Expedition lastExpedition = expeditions.get(expeditions.size() - 1);
        return lastExpedition.getArea();
    }

    public ArrayList<Expedition> getExpeditions() {
        return expeditions;
    }
}
