package nz.ac.auckland.se281;

public class Music extends Service {

  public Music () {
    this.cost = 500;
    this.type = "Music";
  };

  public void bookService (BookingList list, String ref) {
    Booking booking = list.getBooking(ref);
    booking.addService(this);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(this.type, ref);
  }

}
