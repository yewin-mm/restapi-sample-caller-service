package pers.yewin.restapisamplecallerservice.apierrorhandler;

/**
 * @author: Ye Win
 * @created: 12/10/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.apierrorhandler
 */

public class CustomizeUnAuthorizedException extends RuntimeException {
    public CustomizeUnAuthorizedException() {
        super();
    }
    public CustomizeUnAuthorizedException(String message) {
        super(message);
    }
}
