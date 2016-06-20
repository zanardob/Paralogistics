package model.viewtables;

import java.sql.Timestamp;

public class Vehicles {
  private String plate;
  private Timestamp concession_start;
  private Timestamp concession_end;

  public Vehicles(String plate, Timestamp concession_start, Timestamp concession_end) {
    this.plate = plate;
    this.concession_start = concession_start;
    this.concession_end = concession_end;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public Timestamp getConcession_start() {
    return concession_start;
  }

  public void setConcession_start(Timestamp concession_start) {
    this.concession_start = concession_start;
  }

  public Timestamp getConcession_end() {
    return concession_end;
  }

  public void setConcession_end(Timestamp concession_end) {
    this.concession_end = concession_end;
  }
}
