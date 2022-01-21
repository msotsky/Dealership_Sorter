/**
 * exception class for Truck and car
 * thrown when invalid arguments for truck and car
 * 
 * @author Maxie Sotsky
 */

public class InvalidVehicleException extends Exception {

    /**
     * @param message the message when exception is thrown
     */
    
    public InvalidVehicleException(String message) {
        super(message);
    }
}
