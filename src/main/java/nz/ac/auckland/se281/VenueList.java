package nz.ac.auckland.se281;

import java.util.ArrayList;

public class VenueList {

  private ArrayList<Venue> venues = new ArrayList<Venue>();
  BookingList totalBookings = new BookingList();

  public void addVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    // Venue Name cannot be empty
    if (venueName.trim().isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Venue Code must be unique
    for (Venue i : venues) {
      if (i.code.equals(venueCode)) {
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
    this.venues.add(venue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venue.name, venue.code);
    return;
  }

  public void displayVenues(String today) {

    // Print no venue
    if (venues.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
      return;
    }

    // Detect number of venues in the list
    int numberValue = venues.size();

    // Print one venue
    if (numberValue == 1) {
      Venue venue = venues.get(0);
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(
          venue.name, venue.code, venue.capacity, venue.hireFee, venue.getNextDate(today));
      return;
    }

    // Print 2 - 9 venues
    if (numberValue >= 2 && numberValue <= 9) {
      String word = "";
      switch (numberValue) {
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
      for (Venue venue : venues) {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.name, venue.code, venue.capacity, venue.hireFee, venue.getNextDate(today));
      }
      return;
    }

    // Print 10+ venues
    if (numberValue >= 10) {
      String number = String.valueOf(numberValue);
      MessageCli.NUMBER_VENUES.printMessage("are", number, "s");
      for (Venue venue : venues) {
        MessageCli.VENUE_ENTRY.printMessage(
            venue.name, venue.code, venue.capacity, venue.hireFee, venue.getNextDate(today));
      }
      return;
    }
  }

  public boolean venueEmpty() {
    if (venues.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }

  public void bookVenue(Booking plan, String date) {
    for (Venue venue : venues) {
      if (plan.getCode().equals(venue.code)) {

        // check for duplicates
        for (Booking i : venue.bookedDates) {
          if (i.getDate().equals(plan.getDate())) {
            MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venue.name, i.getDate());
            return;
          }
        }

        // too few attendees
        int min = venue.getCapacity() / 4;
        String value = String.valueOf(min);
        if (plan.getAttendees() < min) {
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
              plan.getStringAttendees(), value, venue.capacity);
          plan.setAttendees(value);
        }

        // too many attendees
        int max = venue.getCapacity();
        value = String.valueOf(max);
        if (plan.getAttendees() > max) {
          MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
              plan.getStringAttendees(), value, venue.capacity);
          plan.setAttendees(value);
        }

        // add booking
        plan.setRef(BookingReferenceGenerator.generateBookingReference());
        plan.setCreationDate(date);
        venue.addBooking(plan);
        totalBookings.addBookingList(plan);
        return;
      }
    }

    // Can not find venue
    MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(plan.getCode());
    return;
  }

  public Venue getVenue(String code) {
    Venue error = new Venue();
    for (Venue i : venues) {
      if (i.code.equals(code)) {
        return i;
      }
    }
    return error;
  }

  public boolean venueExist(String code) {
    // no such venue if there are no venues at all
    if (venues.isEmpty()) {
      return false;
    }

    for (Venue i : venues) {
      if (i.code.equals(code)) {
        return true;
      }
    }
    return false;
  }
}
