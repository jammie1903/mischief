/*
 * $Header$
 *
 * Copyright (c) 2015 Novacroft. All Rights Reserved.
 */
package com.rachmie.timetravel;

/**
 * Description to go here.
 */
public class TimeTravelController {
    private TimeTravelController() {
    }

    private static TimeTravelController instance = new TimeTravelController();

    public static TimeTravelController getInstance() {
        return instance;
    }

    public TimeEntry getTimeEntry(int month, int year) {
        return null;
    }

}
