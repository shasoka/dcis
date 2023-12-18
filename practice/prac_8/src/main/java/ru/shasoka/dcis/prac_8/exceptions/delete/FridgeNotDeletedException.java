package ru.shasoka.dcis.prac_8.exceptions.delete;

public class FridgeNotDeletedException extends ModelNotDeletedException {

    public static final String entityType = "Fridge";

    public FridgeNotDeletedException(String msg) {
        super(entityType, msg);
    }
}