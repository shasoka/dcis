package ru.shasoka.dcis.prac_8.exceptions.find;

public class FridgeNotFoundException extends ModelNotFoundException {

    public static final String entityType = "Fridge";

    public FridgeNotFoundException(String msg) {
        super(entityType, msg);
    }

    public FridgeNotFoundException(int id) {
        super(entityType, id);
    }

}
