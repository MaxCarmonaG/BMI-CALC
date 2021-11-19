package co.edu.unab.BmiCalc.model;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private boolean admin;

    public User(String firstName, String lastName, String phone, String email, String password, boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public User() {
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public HashMap<String, Object> getMap() {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("firstName", getFirstName());
        userMap.put("lastName", getLastName());
        userMap.put("phone", getPhone());
        userMap.put("email", getEmail());
        userMap.put("admin", isAdmin());

        return userMap;
    }
}
