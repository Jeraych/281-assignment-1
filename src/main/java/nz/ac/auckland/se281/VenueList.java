package nz.ac.auckland.se281;

import java.util.ArrayList;

public class VenueList {

  private ArrayList<Venue> venueList = new ArrayList<Venue>();
  BookingList totalBookings = new BookingList();

  // Add new venue to the list
  public void addVenue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    
    // Venue Name cannot be empty
    if (venueName.trim().isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Venue Code must be unique
    for (Venue i : venueList) {
      if(i.code.equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, i.name);
        return;
      }
    }

    // Venue Capacity must be a number
    int capacity = 0;
    try {
      capacity = Integer.parseInt(capacityInput);
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    }

    // Venue Capacity must be a positive number
    if (capacity <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    }

    // Venue Hire Fee must be a number
    int hireFee = 0;
    try {
      hireFee = Integer.parseInt(hireFeeInput);
    } catch (Exception e) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    }

    // Venue Hire Fee must be a positive number
    if (hireFee <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      return;
    }

    // create venue
    Venue venue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
    this.venueList.add(venue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venue.name, venue.code);
    return;
  }

  public void displayVenues(String today) {

    // Print no venue
    if (venueList.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }

    // Detect number of venues in the list
    int n = 0;
    for (Venue i : venueList) {
      n++;
    }

    // Print one venue
    if (n == 1) {
      Venue venue = venueList.get(0);
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(venue.name, venue.code, venue.capacity, venue.hireFee, venue.getNextDate(today));
      return;
    }

    // Print 2 - 9 venues
    if (n >= 2 && n <= 9) {
      String word = "";
      switch(n) {
        case 1: 
        word = "one";
        break;
        case 2: 
        word = "two";
        break;
        case 3: 
        word = "three";
        break;
        case 4: 
        word = "four";
        case 5:
        word = "five";
        break;
        case 6:
        word = "six";
        break;
        case 7:
        word = "seven";
        break;
        case 8:
        word = "eight";
        break;
        case 9:
        word = "nine";
        break;
      }
      MessageCli.NUMBER_VENUES.printMessage("are", word, "s");
      for (Venue venue : venueList) {
        MessageCli.VENUE_ENTRY.printMessage(venue.name, venue.code, venue.capacity, venue.hireFee, venue.getNextDate(today));
      }
      return;
    }

    // Print 10+ venues
    if (n >= 10) {
      String number = String.valueOf(n);
      MessageCli.NUMBER_VENUES.printMessage("are", number, "s");
      for (Venue venue : venueList) {
        MessageCli.VENUE_ENTRY.printMessage(venue.name, venue.code, venue.capacity, venue.hireFee, venue.getNextDate(today));
      }
      return;
    }
  }

  public boolean venueEmpty() {
    if(venueList.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public void bookVenue(Booking plan, String date) {
    
    for (Venue venue : venueList) {
      if (plan.bCode.equals(venue.code)) {

        // check for duplicates
        for (Booking i : venue.bookedDate) {
          if (i.bDate.equals(plan.bDate)) {
            MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venue.name, i.bDate);
            return;
          }
        }

        // too few attendees
        int min = venue.getCapacity() / 4;
        String value = String.valueOf(min);
        if (plan.getAttendees() < min) {
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(plan.bAttendees, value, venue.capacity);
          plan.bAttendees = value;
        }

        // too many attendees
        int max = venue.getCapacity();
        value = String.valueOf(max);
        if (plan.getAttendees() > max) {
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(plan.bAttendees, value, venue.capacity);
          plan.bAttendees = value;
        }

        // add booking
        plan.ref = BookingReferenceGenerator.generateBookingReference();
        plan.dateCreated = date;
        venue.addBooking(plan);
        totalBookings.addBookingList(plan);
        return;
      }
    }

    // Can not find venue
    MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(plan.bCode);
    return;
    
  }

  public Venue getVenue(String code) {
    Venue error = new Venue();
    for (Venue i : venueList) {
      if (i.code.equals(code)) {
        return i;
      }
    }
    return error;
  }

  public boolean venueExist(String code) {

    if (venueList.isEmpty()) {
      return false;
    }

    for (Venue i : venueList) {
      if (i.code.equals(code)) {
        return true;
      }
    }
    
    return false;
  }
}
