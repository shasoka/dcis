package ru.shasoka.dcis.prac_5.exceptions.update;

public class ModelNotUpdatedException extends RuntimeException {
  public ModelNotUpdatedException(String entityType , String msg) {
    super(entityType + " not updated because: " + msg);
  }

}
