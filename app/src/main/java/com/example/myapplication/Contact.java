package com.example.myapplication;
/**
 * Created by Agata on 2016-11-11.
 */
public class Contact {

    String name, phone, uname, password;
//utworzenie setterów i getterów dla każdej zmiennej - aby móc w odpowiednim miejscu je wywoływać lub zapisywać
//id jest nie potrzebne bo zawiera auto_increment

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setUname(String uname){
        this.uname=uname;
    }
    public String getUname(){
        return this.uname;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }

}
