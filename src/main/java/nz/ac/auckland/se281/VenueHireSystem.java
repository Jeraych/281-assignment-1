package nz.ac.auckland.se281;
import java.sql.Date;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;

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
    venues.bookVenue(plan, date);
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
    String caterName = cateringType.getName();
    int caterCost = cateringType.getCostPerPerson();
    Catering catering = new Catering(caterName, caterCost);

    if(catering.validBooking(venues.totalBookings, bookingReference)) {
      catering.bookService(venues.totalBookings, bookingReference);
    };
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
    Music music = new Music();

    if(music.validBooking(venues.totalBookings, bookingReference)) {
      music.bookService(venues.totalBookings, bookingReference);
    };
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
    String floralName = floralType.getName();
    int floralCost = floralType.getCost();
    Floral floral = new Floral(floralName, floralCost);

    if(floral.validBooking(venues.totalBookings, bookingReference)) {
      floral.bookService(venues.totalBookings, bookingReference);
    };
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
    if (venues.totalBookings.bookingExist(bookingReference)) {
      Booking booking = venues.totalBookings.getBooking(bookingReference);
      Venue venue = venues.getVenue(booking.bCode);
      int totalCost = 0;
      MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(booking.ref, booking.bEmail, booking.dateCreated, booking.bDate, booking.bAttendees, venue.name);
      MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(venue.hireFee);
      totalCost += venue.getHireFee();

      // Check if catering service is booked
      if (booking.caterExist()) {
        Service cater = booking.getCater();
        int caterCost = cater.cost * booking.getAttendees();
        MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(cater.name, Integer.toString(caterCost));
        totalCost += caterCost;
      }

      // Check if music service is booked
      if (booking.musicExist()) {
        Service music = booking.getMusic();
        int musicCost = music.cost;
        MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(Integer.toString(musicCost));
        totalCost += musicCost;
      }

      // Check if floral service is booked
      if (booking.floralExist()) {
        Service floral = booking.getFloral();
        int floralCost = floral.cost;
        MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(floral.name, Integer.toString(floralCost));
        totalCost += floralCost;
      }
      MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(Integer.toString(totalCost));
    } else {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
    }
    
  }
}
