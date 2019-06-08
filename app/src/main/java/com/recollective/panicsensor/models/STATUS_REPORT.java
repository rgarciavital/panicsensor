package com.recollective.panicsensor.models;

public class STATUS_REPORT {

    int id_tracking;
    int id_status;
    String status_observacion;

    public STATUS_REPORT(int id_tracking, int id_status, String status_observacion) {
        this.id_tracking = id_tracking;
        this.id_status = id_status;
        this.status_observacion = status_observacion;
    }

    public int getId_tracking() {
        return id_tracking;
    }

    public void setId_tracking(int id_tracking) {
        this.id_tracking = id_tracking;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public String getStatus_observacion() {
        return status_observacion;
    }

    public void setStatus_observacion(String status_observacion) {
        this.status_observacion = status_observacion;
    }
}
