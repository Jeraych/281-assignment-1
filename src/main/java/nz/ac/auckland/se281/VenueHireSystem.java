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
    System.out.println("System date set to " + date + ".");
  }

  public void printSystemDate() {
    // TODO implement this method
    if (date == null) {
      System.out.println("Current system date is not set.");
    } else {
      System.out.println("Current system date is " + date + ".");
    }
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
    
    // no system date
    if (date == null) {
      System.out.println("Booking not made: system date not set. Set the date first.");
    }

    // no venue
    if (venues.venueEmpty()) {
      System.out.println("Booking not made: there are no venues in the system. Create one first.");
    }
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
