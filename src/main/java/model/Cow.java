package model;

public class Cow {
    private String id;
    private int year;
    private int month;
    private Integer milk;  // เปลี่ยนจาก int เป็น Integer

    // Constructor
    public Cow(String id, int year, int month, Integer milk) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.milk = milk;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Integer getMilk() {
        return milk;
    }

    public void setMilk(Integer milk) {
        this.milk = milk;
    }
}