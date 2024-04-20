package nz.ac.auckland.se281;
import java.util.ArrayList;

public class BookingList {

  ArrayList<Booking> bookingList = new ArrayList<Booking>();
  
  public void addBookingList(Booking booking) {
    bookingList.add(booking);
  }

  public boolean bookingExist(String ref) {
    for (Booking i : bookingList) {
      if (i.ref.equals(ref)) {
        return true;
      }
    }
    return false;
  }

  public Booking getBooking(String ref) {
    Booking error = new Booking();
    for (Booking i : bookingList) {
      if (i.ref.equals(ref)) {
        return i;
      }
    }
    return error;
  }

}
