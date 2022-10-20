package pers.yewin.restapisamplecallerservice.apierrorhandler;

/**
 * @author: Ye Win
 * @created: 12/10/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.apierrorhandler
 */

public class CustomizeServiceUnAvailableException extends RuntimeException {
    public CustomizeServiceUnAvailableException() {
        super();
    }
    public CustomizeServiceUnAvailableException(String message) {
        super(message);
    }
}
