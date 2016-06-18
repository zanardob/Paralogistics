package model;

public class Vehicles {
  private String plate;
  private java.sql.Date concession_start;
  private java.sql.Date concessione_end;

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }

  public java.sql.Date getConcession_start() {
    return concession_start;
  }

  public void setConcession_start(java.sql.Date concession_start) {
    this.concession_start = concession_start;
  }

  public java.sql.Date getConcessione_end() {
    return concessione_end;
  }

  public void setConcessione_end(java.sql.Date concessione_end) {
    this.concessione_end = concessione_end;
  }
}
