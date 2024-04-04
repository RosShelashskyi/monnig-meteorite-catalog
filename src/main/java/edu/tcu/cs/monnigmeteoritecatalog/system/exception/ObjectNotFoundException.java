package edu.tcu.cs.monnigmeteoritecatalog.system.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String objectName, String id){
        super("Could not find " + objectName + " with Id " + id + " :(");
    }
}
