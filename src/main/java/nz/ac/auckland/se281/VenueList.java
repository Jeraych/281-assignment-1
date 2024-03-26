package nz.ac.auckland.se281;

import java.util.ArrayList;

public class VenueList {

  private ArrayList<Venue> venueList = new ArrayList<Venue>();

  class Venue {
    private String name;
    private String code;
    private String capacity;
    private String hireFee;

    public Venue(String venueName, String venueCode, String venueCapacity, String venueHireFee) {
      
      this.name = venueName;
      this.code = venueCode;
      this.capacity = venueCapacity;
      this.hireFee = venueHireFee;

    }



}

  // Add new venue to the list
  public void addVenue(String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    
    // Venue Name cannot be empty
    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // Venue Code must be unique
    for (Venue i : venueList) {
      if (venueCode == i.code) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, i.name);
        return;
      }
    }

    // Venue Capacity must be a number
    for (int i = 1; i < capacityInput.length(); i++) {
      if (!Character.isDigit(capacityInput.charAt(i))) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
        return;
      }
    }

    // Venue Capacity must be a positive number
    int capacity = Integer.parseInt(capacityInput);
    if (capacity < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    }

    // Venue Capacity must be over zero
    if (capacity == 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " over zero");
      return;
    }


    // Venue Hire Fee must be a number
    for (int i = 1; i < hireFeeInput.length(); i++) {
      if (!Character.isDigit(hireFeeInput.charAt(i))) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
        return;
      }
    }

    // Venue Hire Fee must be a positive number
    int hireFee = Integer.parseInt(hireFeeInput);
    if (hireFee < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      return;
    }

    // Venue Hire Fee must be over zero
    if (hireFee == 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " over zero");
      return;
    }

    // create venue
    Venue venue = new Venue(venueName, venueCode, capacityInput, hireFeeInput);
    this.venueList.add(venue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venue.name, venue.code);
  }

  public void displayVenues() {

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
      MessageCli.VENUE_ENTRY.printMessage(venue.name, venue.code, venue.capacity, venue.hireFee);
    }
  }



}
