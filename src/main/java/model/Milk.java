package model;


public class Milk {
    private int id;
    private int bottle;

    // Constructor
    public Milk(int id, int bottle) {
        this.id = id;
        this.bottle = bottle;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBottle() {
        return bottle;
    }

    public void setBottle(int bottle) {
        this.bottle = bottle;
    }

    @Override
    public String toString() {
        return "Milk{" +
                "id=" + id +
                ", bottle=" + bottle +
                '}';
    }
}