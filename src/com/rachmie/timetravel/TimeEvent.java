package com.rachmie.timetravel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("event")
public class TimeEvent {
    private String eventText;
    private Date eventDate;
    private String eventSound;

    @XStreamAlias("events")
    public static class EventList {
        @XStreamImplicit(itemFieldName = "event")
        private List<TimeEvent> events = new ArrayList<>();

        @XStreamImplicit(itemFieldName = "randomEvent")
        private List<String> randomEvents;

        public List<TimeEvent> getEvents() {
            return events;
        }

        public List<String> getRandomEvents() {
            return randomEvents;
        }
    }

    /**
     * @return the eventText
     */
    public String getEventText() {
        return eventText;
    }

    /**
     * @param eventText
     *            the eventText to set
     */
    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    /**
     * @return the eventDate
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate
     *            the eventDate to set
     */
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the eventSound
     */
    public String getEventSound() {
        return eventSound;
    }

    /**
     * @param eventSound
     *            the eventSound to set
     */
    public void setEventSound(String eventSound) {
        this.eventSound = eventSound;
    }

}
