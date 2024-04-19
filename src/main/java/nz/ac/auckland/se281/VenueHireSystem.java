package nz.ac.auckland.se281;
import java.sql.Date;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  VenueList venues = new VenueList();
  String date = "";

  String[] dateParts;
  int day;
  int month;
  int year;

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method
    venues.displayVenues(date);

  }

  public void createVenue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    venues.addVenue(venueName, venueCode, capacityInput, hireFeeInput);
        
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
    date = dateInput;
    MessageCli.DATE_SET.printMessage(date);
    dateParts = date.split("/");

    day = Integer.parseInt(dateParts[0]);
    month = Integer.parseInt(dateParts[1]);
    year = Integer.parseInt(dateParts[2]);
  }

  public void printSystemDate() {
    // TODO implement this method
    if (date.trim().isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(date);
    }
  }

  public void makeBooking(String[] options) {
    // TODO implement this method

    // no system date
    if (date.trim().isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    // using booking class
    Booking plan = new Booking(options);

    // past date
    if (year > plan.getYear()) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(plan.bDate, date);
      return;
    }
    if (month > plan.getMonth()) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(plan.bDate, date);
      return;
    }
    if (day > plan.getDay()) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(plan.bDate, date);
      return;
    }

    // no venue
    if (venues.venueEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // make booking
    venues.bookVenue(plan);
  }

  public void printBookings(String venueCode) {
    // TODO implement this method

    // no venue exist
    if (!venues.venueExist(venueCode)) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // print booking
    Venue i = venues.getVenue(venueCode);
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(i.name);

    // not booked
    if (i.bookedDate.isEmpty()) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(i.name);
      return;
    }

    for (Booking j : i.bookedDate) {
      MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(j.ref, j.bDate);
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
