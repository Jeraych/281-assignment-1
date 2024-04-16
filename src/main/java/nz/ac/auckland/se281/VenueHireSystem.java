package nz.ac.auckland.se281;
import java.sql.Date;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  VenueList venues = new VenueList();
  String date;

  public VenueHireSystem() {}

  public void printVenues() {
    // TODO implement this method
    venues.displayVenues();

  }

  public void createVenue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // TODO implement this method
    venues.addVenue(venueName, venueCode, capacityInput, hireFeeInput);
        
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
    date = dateInput;
    MessageCli.DATE_SET.printMessage(date);
  }

  public void printSystemDate() {
    // TODO implement this method
    if (date == null) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(date);
    }
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
    
    // no system date
    if (date == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    }

    // no venue
    if (venues.venueEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
    }

    venues.bookVenue(options);
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
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
