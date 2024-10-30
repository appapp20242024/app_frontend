package com.midterm.brainupdate;

public class Note {
    private String title;
    private int quantity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Note(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }
}
