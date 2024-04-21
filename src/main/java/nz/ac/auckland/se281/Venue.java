package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  String name;
  String code;
  String capacity;
  String hireFee;

  ArrayList<Booking> bookedDates = new ArrayList<Booking>();

  public Venue() {}

  public Venue(String venueName, String venueCode, String venueCapacity, String venueHireFee) {
    this.name = venueName;
    this.code = venueCode;
    this.capacity = venueCapacity;
    this.hireFee = venueHireFee;
  }

  public void addBooking(Booking plan) {
    this.bookedDates.add(plan);
    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        plan.getRef(), this.name, plan.getDate(), plan.getStringAttendees());
  }

  public int getCapacity() {
    int cap = Integer.parseInt(this.capacity);
    return cap;
  }

  public int getHireFee() {
    int fee = Integer.parseInt(this.hireFee);
    return fee;
  }

  public String getNextDate(String today) {
    // task 1 fixes
    if (today.trim().isEmpty()) {
      return "";
    }

    // if no bookings are made, today is available
    if (bookedDates.isEmpty()) {
      return today;
    }

    String[] dateParts = today.split("/");
    boolean occupiedDate = false;
    int day = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int year = Integer.parseInt(dateParts[2]);

    // search for exact same date, if there is, look if the next one is occupied too
    while (!occupiedDate) {
      occupiedDate = true;
      for (Booking i : bookedDates) {
        if (year == i.getYear()) {
          if (month == i.getMonth()) {
            if (day == i.getDay()) {
              occupiedDate = false;
              day++;
              break;
            }
          }
        }
      }
    }

    String sDay;
    String sMonth;
    // turn to respective string with 1 digit
    if (day <= 9) {
      sDay = "0" + Integer.toString(day);
    } else {
      sDay = Integer.toString(day);
    }
    if (month <= 9) {
      sMonth = "0" + Integer.toString(month);
    } else {
      sMonth = Integer.toString(month);
    }

    return sDay + "/" + sMonth + "/" + Integer.toString(year);
  }
}
