package com.recollective.panicsensor.models;

public class TYPE_VIAL {

    int id_vial;
    String  name_vial;

    public TYPE_VIAL(int id_vial, String name_vial) {
        this.id_vial = id_vial;
        this.name_vial = name_vial;
    }

    public int getId_vial() {
        return id_vial;
    }

    public void setId_vial(int id_vial) {
        this.id_vial = id_vial;
    }

    public String getName_vial() {
        return name_vial;
    }

    public void setName_vial(String name_vial) {
        this.name_vial = name_vial;
    }
}
