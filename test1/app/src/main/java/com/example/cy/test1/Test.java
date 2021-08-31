package com.example.cy.test1;

/**
 * Created by cy on 21-8-16.
 */

public class Test {

    private static final int AVAILABLE = 1;
    private static final int UNSUPPORTED_ON_DEVICE = 0;


    public static void main(String[] args) {


        System.out.println(getAvailabilityStatus(true, false));
    }

    public static int getAvailabilityStatus(boolean a, boolean b) {
        return a && !b ? AVAILABLE : UNSUPPORTED_ON_DEVICE;
    }
}
