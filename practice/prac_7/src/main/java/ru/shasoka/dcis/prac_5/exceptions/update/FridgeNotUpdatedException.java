package ru.shasoka.dcis.prac_5.exceptions.update;

public class FridgeNotUpdatedException extends ModelNotUpdatedException {

    public static final String entityType = "Fridge";

    public FridgeNotUpdatedException(String msg) {
        super(entityType, msg);
    }
}