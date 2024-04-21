package nz.ac.auckland.se281;

import java.util.ArrayList;

public class BookingList {

  private ArrayList<Booking> bookings = new ArrayList<Booking>();

  public void addBookingList(Booking booking) {
    bookings.add(booking);
  }

  public boolean bookingExist(String ref) {
    for (Booking i : bookings) {
      if (i.getRef().equals(ref)) {
        return true;
      }
    }
    return false;
  }

  public Booking getBooking(String ref) {
    Booking error = new Booking();
    for (Booking i : bookings) {
      if (i.getRef().equals(ref)) {
        return i;
      }
    }
    return error;
  }
}
