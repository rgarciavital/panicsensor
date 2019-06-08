package com.recollective.panicsensor.models;

public class STATUS {

    int id_status;
    String name_status;

    public STATUS(int id_status, String name_status) {
        this.id_status = id_status;
        this.name_status = name_status;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getName_status() {
        return name_status;
    }

    public void setName_status(String name_status) {
        this.name_status = name_status;
    }
}
