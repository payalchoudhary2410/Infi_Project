package com.example.infi_project;

class Users {



    String userName;
    String userPhone;
    String userEmail;
    String dateofBirth;
    String rollno;

    public Users(){

    }

    public Users( String userName, String userEmail,String userPhone,String dateofBirth,String rollno) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone=userPhone;
        this.dateofBirth=dateofBirth;
        this.rollno=rollno;
    }





    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public String getRollno() {
        return rollno;
    }
}
