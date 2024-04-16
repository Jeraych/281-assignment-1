package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

    String name;
    String code;
    String capacity;
    String hireFee;

    ArrayList<Booking> bookedDate = new ArrayList<Booking>();

    public Venue(String venueName, String venueCode, String venueCapacity, String venueHireFee) {
      
      this.name = venueName;
      this.code = venueCode;
      this.capacity = venueCapacity;
      this.hireFee = venueHireFee;

    }

   public void addBooking(Booking plan) {
    this.bookedDate.add(plan);
   }


}
