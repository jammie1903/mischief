package com.rachmie.timetravel;

import java.util.Date;

public class TimeEvent {
  private String eventText;
  private Date eventDate;
  private String eventSound;

  /**
   * @return the eventText
   */
  public String getEventText() {
    return eventText;
  }

  /**
   * @param eventText
   *          the eventText to set
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
   *          the eventDate to set
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
   *          the eventSound to set
   */
  public void setEventSound(String eventSound) {
    this.eventSound = eventSound;
  }

}
