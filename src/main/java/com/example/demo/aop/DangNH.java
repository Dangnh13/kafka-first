package com.example.demo.aop;

import lombok.Data;

import java.util.Objects;

@Data
public class DangNH {
    private int id;
    private String username;

    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DangNH dangNH = (DangNH) o;
        return id == dangNH.id && Objects.equals(username, dangNH.username);
    }

    @Override
    public int hashCode() {
        return age;
    }
}
