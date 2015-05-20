/*
 * $Header$
 *
 * Copyright (c) 2015 Novacroft. All Rights Reserved.
 */
package com.rachmie.timetravel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description to go here.
 */
public class TimeTravelController {

    private static final String XML_FILE = "/events.xml";
    private final static DateFormat dateFormat = new SimpleDateFormat("MM/YYYY");

    private List<TimeEvent> events = new ArrayList<>();

    private TimeTravelController() {

    }

    private static TimeTravelController instance = new TimeTravelController();

    public static TimeTravelController getInstance() {
        return instance;
    }

    public TimeEvent getEvent(int month, int year) {
        Date date;
        try {
            date = dateFormat.parse(month + "/" + year);
        } catch (ParseException e) {
            return null;
        }
        for (TimeEvent event : events) {
            if (date.equals(event.getEventDate())) {
                return event;
            }
        }
        return generateRandomEvent();
    }

    private TimeEvent generateRandomEvent() {
        TimeEvent event = new TimeEvent();
        event.setEventText("This is soooooo random");
        return event;
    }
}
