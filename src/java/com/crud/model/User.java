package com.crud.model;

import com.crud.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "user")
@RequestScoped
public class User {

    String name, email, password, gender, location, regdate, msg;
    int i;
    Connection con = null;
    PreparedStatement pst;
    ResultSet rs;
    ArrayList userList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void saveData() {
        String sql = "insert into user (name, email, password, gender, location, rdate) Values(?,?,?,?,?,?)";

        try {
            con = DBConnection.getConnect();
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, gender);
            pst.setString(5, location);
            pst.setString(6, regdate);
            pst.executeUpdate();
            setMsg("Data saved successfully");
            clear();
        } catch (Exception e) {
            e.printStackTrace();
            setMsg("Data saved failed");
        } finally {
            try {
                con.close();
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public String clear(){
       
        setName(name= "");
        setEmail(email="");
        setPassword(password = "");
        setGender(gender ="");
        setLocation(location="");
        setRegdate(regdate ="");
        return "clear";
    }

    public String beforeUpdate(User u) {
        setI(u.i);
        setName(u.name);
        setEmail(u.email);
        setPassword(u.password);
        setGender(u.gender);
        setLocation(u.location);
        setRegdate(u.regdate);
        return "update";
    }

    public String delUser(int idd) {
        String sql = "delete from user where id =?";
        try {
            con = DBConnection.getConnect();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idd);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                pst.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "index";
    }
    
    public void doUpdate(){
        String sql = "update user set email=?, password=?,gender=?,location=? where id = ?";
        try {
            con = DBConnection.getConnect();
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, gender);             
            pst.setString(4, location);
            pst.setInt(5, i);
            pst.executeUpdate();
            setMsg("Data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
             setMsg("Data saved failed");
        }finally{
            try {
                con.close();
                pst.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
               
    }

    public ArrayList getUserList() {
        String sql = "select * from user";
        try {
            con = DBConnection.getConnect();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            userList = new ArrayList();
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setLocation(rs.getString("location"));
                user.setRegdate(rs.getString("rdate"));
                user.setI(rs.getInt("id"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

}
