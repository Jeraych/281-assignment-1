package nz.ac.auckland.se281;

public class Booking {
  String bCode;
  String bDate;
  String bEmail;
  String bAttendees;
  String day;
  String month;
  String year;

  public Booking(String[] options) {
    
    String code = options[0];
    String date = options[1];
    String email = options[2];
    String attendees = options[3];

    String dateParts[] = date.split("/");

    this.day = dateParts[0];
    this.month = dateParts[1];
    this.year = dateParts[2];
    this.bCode = code;
    this.bDate = date;
    this.bEmail = email;
    this.bAttendees = attendees;
  }

  public int getAttendees() {
    int attend = Integer.parseInt(this.bAttendees);
    return attend;
  }

  public int getDay() {
    int day = Integer.parseInt(this.day);
    return day;
  }

  public int getMonth() {
    int month = Integer.parseInt(this.month);
    return month;
  }

  public int getYear() {
    int year = Integer.parseInt(this.year);
    return year;
  }

 }
