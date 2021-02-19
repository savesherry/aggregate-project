package com.example.aggregate_methods.dialog.single.bottom;

/**
 * CREATE BY liyang
 * ON 2021-02-19
 * SUPPLY : Thanks for watching
 */
public class SingleChoiceModel {
    private String id;
    private String name;
    private boolean choice = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }
}
