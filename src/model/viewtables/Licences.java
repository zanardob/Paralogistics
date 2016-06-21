package model.viewtables;

public class Licences {
    private String deliverer;
    private Integer scheduling;
    private String vehicle;

    public Licences(String deliverer, Integer scheduling, String vehicle) {
        this.deliverer = deliverer;
        this.scheduling = scheduling;
        this.vehicle = vehicle;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public Integer getScheduling() {
        return scheduling;
    }

    public void setScheduling(Integer scheduling) {
        this.scheduling = scheduling;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
