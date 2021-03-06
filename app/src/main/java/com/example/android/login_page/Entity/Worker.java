package com.example.android.login_page.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Worker implements Serializable {
  private int workerId;
  private String workerName;
  private int workerSalary;
  private String street;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    private String city;
  private String houseNumber;
  private ArrayList<String> phoneNumbers;
  private int adminId;

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getWorkerSalary() {
        return workerSalary;
    }

    public void setWorkerSalary(int workerSalary) {
        this.workerSalary = workerSalary;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumber(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Worker(int workerId, String workerName, int workerSalary, String street, String city, String houseNumber, ArrayList<String> phoneNumbers, int adminId) {
        this.workerId = workerId;
        this.workerName = workerName;
        this.workerSalary = workerSalary;
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.phoneNumbers = phoneNumbers;
        this.adminId = adminId;
    }

    public Worker() {
    }
}
