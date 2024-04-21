package nz.ac.auckland.se281;

public class Catering extends Service {

  public Catering() {}

  public Catering(String name, int cost) {
    super(cost);
    this.name = name;
    this.type = "Catering";
  }

  public void bookService(BookingList list, String ref) {
    Booking booking = list.getBooking(ref);
    booking.addService(this);
    MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(this.type + " (" + this.name + ")", ref);
  }
}
