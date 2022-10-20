package pers.yewin.restapisamplecallerservice.apierrorhandler;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author: Ye Win
 * @created: 12/10/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.apierrorhandler
 */

public class CustomizeRestTemplateErrorHandler implements ResponseErrorHandler {

    /**
     * This class will do process when api response was error for http status code 4xx and 5xx. (starting from 400 and from 500).
     * This class will catch error and will prevent not to go try-catch block when calling api and if api response was error.
     * You need to add this as global declaration in restTemplate as below restTemplateBean() method
     * @see pers.yewin.restapisamplecallerservice.RestapiSampleCallerServiceApplication#restTemplateBean() method
     */


    // below method will serve when the error was occurred.
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return new DefaultResponseErrorHandler().hasError(clientHttpResponse);
        // you can also validate with below code for starting from 400 and from 500 status code error.
//        return clientHttpResponse.getStatusCode().is4xxClientError() || clientHttpResponse.getStatusCode().is5xxServerError();

    }

    // below method is for specific error code.
    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

//        if (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
//            // handle 5xx errors
//            // raw http status code e.g `500`
//            System.out.println(clientHttpResponse.getRawStatusCode());
//
//            // http status code e.g. `500 INTERNAL_SERVER_ERROR`
//            System.out.println(clientHttpResponse.getStatusCode());
//        }

        // you can check by status code series instead of below if statement
        // eg. -> if (clientHttpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
        if (clientHttpResponse.getStatusCode().is5xxServerError()) { // handle 5xx errors

            // you can print out the code value like below
//            System.out.println(clientHttpResponse.getRawStatusCode());
//            System.out.println(clientHttpResponse.getStatusCode());

            // you can handle more server errors by creating new customize exception classes like below
            /*if (clientHttpResponse.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
                throw new CustomizeServiceUnAvailableException("Service is currently unavailable");
            }*/


        } else if (clientHttpResponse.getStatusCode().is4xxClientError()) {
            // you can handle more 4xx errors by creating new customize exception classes like above
            /*if (clientHttpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new CustomizeUnAuthorizedException("Unauthorized access");
            }*/

        }

    }
}
