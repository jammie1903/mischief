package com.rachmie.timetravel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.rachmie.timetravel.TimeEvent.EventList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

public class TimeTravelController {

    private static final String XML_FILE = TimeTravelController.class.getResource("/events.xml").getPath();

    private List<TimeEvent> events;
    private List<String> randomEvents;

    private TimeTravelController() {
        // TODO load up xml
        FileReader reader = null;
        try {
            reader = new FileReader(XML_FILE);
            XStream xstream = new XStream();
            xstream.registerConverter(new DateConverter("MM/yyyy", new String[0]));
            xstream.processAnnotations(TimeEvent.class); // inform XStream to parse
                                                         // annotations in Data class
            xstream.processAnnotations(EventList.class);
            EventList event = (EventList) xstream.fromXML(reader);
            System.out.println("Number of events = " + event.getEvents().size());
            events = event.getEvents();
            randomEvents = event.getRandomEvents();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // load file
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }

    }

    private static TimeTravelController instance = new TimeTravelController();

    public static TimeTravelController getInstance() {
        return instance;
    }

    public TimeEvent getEvent(int month, int year) {
        Calendar c = Calendar.getInstance();
        for (TimeEvent event : events) {
            c.setTime(event.getEventDate());
            if (c.get(Calendar.YEAR) == year && c.get(Calendar.MONTH) == month) {
                return event;
            }
        }
        return generateRandomEvent();
    }

    private TimeEvent generateRandomEvent() {
        int index = (int) (Math.random() * randomEvents.size());
        TimeEvent event = new TimeEvent();
        event.setEventText(randomEvents.get(index));
        return event;
    }
}
