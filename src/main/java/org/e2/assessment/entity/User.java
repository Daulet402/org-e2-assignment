package org.e2.assessment.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private List<Role> roles = new ArrayList<>();
    private List<Privilege> privileges = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
