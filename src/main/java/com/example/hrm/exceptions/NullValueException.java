package com.example.hrm.exceptions;

public class NullValueException extends Exception{
    public NullValueException(String key){
       super("The object.properties have Key "+ key +" but Missing Value");
    }
}
