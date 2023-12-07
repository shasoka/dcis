package ru.shasoka.dcis.prac_5.exceptions.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

  private String message;

  private Timestamp timestamp;

  public ErrorResponse(String message) {
    this.message = message;
    this.timestamp = new Timestamp(System.currentTimeMillis());
  }
}
