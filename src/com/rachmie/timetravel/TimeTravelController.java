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

    private static final String XML_FILE = "/events.xml";

    private TimeTravelController() {
        //TODO load up xml
    }

    private static TimeTravelController instance = new TimeTravelController();

    public static TimeTravelController getInstance() {
        return instance;
    }

    public TimeEvent getEvent(int month, int year) {
        return null;
    }

}
