package com.recollective.panicsensor.models;

public class REPORT {

    String id_tracking;
    String lat;
    String lon;
    String address;
    int id_vial;
    String photo;
    String observacion;

    public REPORT() {
    }

    public REPORT(String id_tracking, String lat, String lon, String address, int id_vial, String photo, String observacion) {
        this.id_tracking = id_tracking;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.id_vial = id_vial;
        this.photo = photo;
        this.observacion = observacion;
    }

    public String getId_tracking() {
        return id_tracking;
    }

    public void setId_tracking(String id_tracking) {
        this.id_tracking = id_tracking;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_vial() {
        return id_vial;
    }

    public void setId_vial(int id_vial) {
        this.id_vial = id_vial;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return observacion;
    }
}
