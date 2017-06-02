package com.mycompany.flightstatuslistview;

/**
 * Created by mkaratadipalayam on 28/05/17.
 */

public class ArrayEmptyException extends RuntimeException {

    private String msg;

    public ArrayEmptyException() {
        msg = "ArrayEmptyException";
    }

    public ArrayEmptyException(String exceptionMessage) {
        msg = exceptionMessage;
    }

    public String getMessage() {
        return msg;
    }

    public String toString() {
        return (msg);
    }

}