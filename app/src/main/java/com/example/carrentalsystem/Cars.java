package com.example.carrentalsystem;

public class Cars {

    String carid, carname, fueltype, mileage, noofseat,rent;

    public Cars(String carid, String carname, String fueltype, String mileage, String noofseat, String rent) {
        this.carid = carid;
        this.carname = carname;
        this.fueltype = fueltype;
        this.mileage = mileage;
        this.noofseat = noofseat;
        this.rent = rent;
    }

    public Cars(){

    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getNoofseat() {
        return noofseat;
    }

    public void setNoofseat(String noofseat) {
        this.noofseat = noofseat;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }
}
