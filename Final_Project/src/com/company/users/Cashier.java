package com.company.users;

import com.company.payment.Payable;

import java.time.LocalDateTime;

public class Cashier extends User implements Payable {

    private static final int WORK_HOURS = 8;
    private static final int OVERTIME_SALARY = 10;
    private int internalPhoneNumber;
    private static final double BASE_SALARY_RATE = 7.5;
    private int hoursOfWork;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;

//   private LocalDateTime endTime;

    public Cashier(String name, String surname, String username, String password, String address, int phoneNumber, int internalPhoneNumber) {
        super(name, surname, username, password, address, phoneNumber);
        this.internalPhoneNumber = internalPhoneNumber;
    }

    public int getInternalPhoneNumber() {
        return internalPhoneNumber;
    }

    public void setInternalPhoneNumber(int internalPhoneNumber) {
        this.internalPhoneNumber = internalPhoneNumber;
    }

    public static double getBaseSalaryRate() {
        return BASE_SALARY_RATE;
    }

    public static int getWorkHours() {
        return WORK_HOURS;
    }

    public double calculateBonus(){

        int workingHours = getHoursOfWork();

        int calculateBonus = 0;

        if(workingHours > WORK_HOURS){
            calculateBonus =  (workingHours - WORK_HOURS) * OVERTIME_SALARY;
        }

        return Math.round(calculateBonus * 100.0) / 100.0;
    }

    public double pay() {
        return Math.round((this.calculateBonus() + (getBaseSalaryRate() * getWorkHours())) * 100.0) / 100.0;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public int getHoursOfWork() {
        return this.hoursOfWork;
    }

    public void setHoursOfWork(int hoursOfWork) {
        this.hoursOfWork = hoursOfWork;
    }

    public void calculateHoursOfWork() {
        setHoursOfWork(this.logoutTime.getHour() - this.loginTime.getHour());
    }


    public void printInfo(){
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Cashier{" +
                super.toString()  +
                ", internalPhoneNumber=" + internalPhoneNumber +
                ", LogInTime=" + getLoginTime() +
                ", LogOutTime=" + getLogoutTime() +
                ", HoursOfWork=" + getHoursOfWork() +
                ", CalculatedSalary=" + pay() +
                ", CalculatedBonus=" + calculateBonus() +
                '}';
    }
}
