package edu.tcu.cs.monnigmeteoritecatalog.sample;

public class SampleNotFoundException extends RuntimeException{
    public SampleNotFoundException(String id) {
        super("Could not find sample with Id " + id + " :(");
    }
}
