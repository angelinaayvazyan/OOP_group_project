package proj.core;

public class NotValidNumberOfPlayerException extends Exception {

    public NotValidNumberOfPlayerException() { 
        this("Number Of player can be 2 - 6 with equal names");
    }

    public NotValidNumberOfPlayerException(String message) { 
        super(message);
    }

}
