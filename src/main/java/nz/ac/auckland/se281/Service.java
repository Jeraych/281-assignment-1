package nz.ac.auckland.se281;

public abstract class Service {

  String type = "";
  String name;
  int cost;

  public Service() {}

  public Service(int cost) {
    this.cost = cost;
  }

  public abstract void bookService(BookingList list, String ref);

  public boolean validBooking(BookingList list, String ref) {
    if (list.bookingExist(ref)) {
      return true;
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage(this.type, ref);
      return false;
    }
  }
}
