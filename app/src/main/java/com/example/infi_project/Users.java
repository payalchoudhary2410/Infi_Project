package com.example.infi_project;

class Users {



    String userName;
    String userPhone;
    String userEmail;
    String dateofBirth;
    String rollno;
    String pass;

    public Users() {
    }

    public Users(String userName, String userPhone, String userEmail, String dateofBirth, String rollno, String pass) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.dateofBirth = dateofBirth;
        this.rollno = rollno;
        this.pass = pass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
