package com.example.db.entity;

import java.util.HashSet;

public enum Role {

    DIRECTOR("DIRECTOR"), //директор Restaurants edit, the rest is read only
    MANAGER("MANAGER"), //менеджер dishes, products edit
    HR("HR"); //кадры-бухгалтерия positions, employees edit

    private final String role;

    Role(String role) {
        this.role = role;
    }

//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
//        return authorities;
//    }
}
