package com.recollective.panicsensor.models;

public class LOGIN {

    String id_auto;
    String user;
    String pass;

    public LOGIN(String id_auto, String user, String pass) {
        this.id_auto = id_auto;
        this.user = user;
        this.pass = pass;
    }

    public LOGIN() {
    }

    public String getId_auto() {
        return id_auto;
    }

    public void setId_auto(String id_auto) {
        this.id_auto = id_auto;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public String toString() {
        return id_auto+" "+user+"-"+pass;
    }
}
