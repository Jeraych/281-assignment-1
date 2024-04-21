package nz.ac.auckland.se281;

public class Floral extends Service {

  public Floral() {}

  public Floral(String name, int cost) {
    super(cost);
    this.name = name;
    this.type = "Floral";
  }

  public void bookService(BookingList list, String ref) {
    Booking booking = list.getBooking(ref);
    booking.addService(this);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(this.type + " (" + this.name + ")", ref);
  }
}
