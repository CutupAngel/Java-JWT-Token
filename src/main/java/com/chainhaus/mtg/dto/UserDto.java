package com.chainhaus.mtg.dto;

import java.util.Set;

public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private boolean isActive;

    private String img;

    private String img2x;

    private Set<String> roles;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getImg() {
        return img;
    }

    public String getImg2x() {
        return img2x;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setImg2x(String img2x) {
        this.img2x = img2x;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
