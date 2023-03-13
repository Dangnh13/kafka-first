package com.example.demo.aop;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        DangNH d1 = new DangNH();
        d1.setId(1);
        d1.setUsername("name");
        d1.setAge(1);
        DangNH d2 = new DangNH();
        d2.setId(1);
        d2.setUsername("name");
        d2.setAge(2);

        System.out.println(d1.equals(d2));
   /*     System.out.println(d1==d2);

        String personalLoan = new String("cheap personal loans");
        String homeLoan = new String("cheap personal loans");
        System.out.println(personalLoan == homeLoan);*/

        Set<DangNH> set = new HashSet<>();
        set.add(d1);
        set.add(d2);
        System.out.println(set.size());
    }
}
