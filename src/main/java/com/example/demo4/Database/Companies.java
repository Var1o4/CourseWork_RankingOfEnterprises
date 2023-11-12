package com.example.demo4.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Companies {
    private final SimpleStringProperty name;
    private final SimpleStringProperty result;
    private final IntegerProperty year;

    public Companies(String name, String result, int year) {
        this.name = new SimpleStringProperty(name);
        this.result = new SimpleStringProperty(result);
        this.year = new SimpleIntegerProperty(year);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getResult() {
        return result.get();
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty resultProperty() {
        return result;
    }

    public IntegerProperty yearProperty() {
        return year;
    }

}
