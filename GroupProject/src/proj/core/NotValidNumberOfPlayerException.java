package proj.core;

public class NotValidNumberOfPlayerException extends Exception {

    public NotValidNumberOfPlayerException() { 
        this("Number Of players can be from 2 to 6 with corresponding number of names");
    }

    public NotValidNumberOfPlayerException(String message) { 
        super(message);
    }

}
