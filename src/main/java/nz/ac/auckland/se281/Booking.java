package nz.ac.auckland.se281;

public class Booking {
  String bCode;
  String bDate;
  String bEmail;
  String bAttendees;

  public Booking(String[] options) {
    
    String code = options[0];
    String date = options[1];
    String email = options[2];
    String attendees = options[3];

    this.bCode = code;
    this.bDate = date;
    this.bEmail = email;
    this.bAttendees = attendees;
  }
}
