package nz.ac.auckland.se281;
import java.util.ArrayList;

public class Booking {

  String bCode;
  String bDate;
  String bEmail;
  String bAttendees;
  String day;
  String month;
  String year;
  String ref;
  String dateCreated;

  ArrayList<Service> services = new ArrayList<Service>();

  public Booking() {}

  public Booking(String[] options) {
    
    String code = options[0];
    String date = options[1];
    String email = options[2];
    String attendees = options[3];

    String[] dateParts = date.split("/");

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

  public void addService(Service service) {
    services.add(service);
  }

  public boolean caterExist() {
    for (Service i : services) {
      if (i.type.equals("Catering")) {
        return true;
      }
    }
    return false;
  }

  public Service getCater() {
    Service error = new Catering();
    for (Service i : services) {
      if (i.type.equals("Catering")) {
        return i;
      }
    }
    return error;
  }

  public boolean musicExist() {
    for (Service i : services) {
      if (i.type.equals("Music")) {
        return true;
      }
    }
    return false;
  }

  public Service getMusic() {
    Service error = new Music();
    for (Service i : services) {
      if (i.type.equals("Music")) {
        return i;
      }
    }
    return error;
  }

  public boolean floralExist() {
    for (Service i : services) {
      if (i.type.equals("Floral")) {
        return true;
      }
    }
    return false;
  }

  public Service getFloral() {
    Service error = new Floral();
    for (Service i : services) {
      if (i.type.equals("Floral")) {
        return i;
      }
    }
    return error;
  }

 }
