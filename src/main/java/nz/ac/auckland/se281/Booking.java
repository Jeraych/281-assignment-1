package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {

  private String bCode;
  private String bDate;
  private String bEmail;
  private String bAttendees;
  private String day;
  private String month;
  private String year;
  private String ref;
  private String dateCreated;

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

  public String getStringAttendees() {
    return bAttendees;
  }

  public void setAttendees(String attendees) {
    this.bAttendees = attendees;
  }

  public String getEmail() {
    return bEmail;
  }

  public String getCreationDate() {
    return dateCreated;
  }

  public void setCreationDate(String date) {
    this.dateCreated = date;
  }

  public String getRef() {
    return ref;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }

  public String getDate() {
    return bDate;
  }

  public String getCode() {
    return bCode;
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
