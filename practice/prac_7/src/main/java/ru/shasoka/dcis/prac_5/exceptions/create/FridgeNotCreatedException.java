package ru.shasoka.dcis.prac_5.exceptions.create;

public class FridgeNotCreatedException extends ModelNotCreatedException {

    public static final String entityType = "Fridge";

    public FridgeNotCreatedException(String msg) {
        super(entityType, msg);
    }
}
