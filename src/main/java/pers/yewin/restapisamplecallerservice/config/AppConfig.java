package pers.yewin.restapisamplecallerservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Ye Win
 * @created: 07/10/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.config
 */


@Configuration // declare class as bean
@ConfigurationProperties(prefix = "callerapp.api.receiverservice.endpoint") // read values from properties which behind 'app.config' prefix
@Data // using lombok to get getter and setter method which is needed to inject data from properties file into fields by setter method, getter method is need for access field value from other class.
public class AppConfig {

    /**
     * You can reference reading properties values by using Configuration Properties in below url.
     * Refer to <a href="https://github.com/yewin-mm/reading-properties-file-values">Reading Properties File Values</a>
     */

    private String responseSingleStringDemoUrl;
    private String responseSingleObjectDemoUrl;
    private String responseObjectDemoUrl;
    private String responseStringListDemoUrl;
    private String responseObjectListDemoUrl;
    private String responseCustomObjectListDemoUrl;
    private String responseNestedObjectDemoUrl;
    private String responseNestedObjectAndNestedObjectListDemoUrl;

    private String requestBodyObjectDemoUrl;
    private String requestBodyObjectListDemoUrl;
    private String requestBodyStringListDemoUrl;
    private String requestPathVariableIntegerDemoUrl;
    private String requestMultiPathVariablesDemoUrl;
    private String requestParamStringDemoUrl;
    private String requestMultiParamsDemoUrl;
    private String requestPathVariableAndParamsDemoUrl;
    private String requestFromHeaderDemoUrl;
    private String requestFromHeaderAndRequestParamDemoUrl;
    private String requestFromHeaderAndRequestBodyDemoUrl;
    private String requestFileUploadDemoUrl;
    private String requestFromHeaderAndBulkFileUploadDemoUrl;


    private String requestPathVariableAndResponseObjectDemoUrl;
    private String requestParamStringAndResponseObjectListDemoUrl;
    private String requestFromHeaderAndRequestBodyAndResponseNestedObjectDemoUrl;
    private String requestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemoUrl;
    private String requestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemoUrl;

    private String errorBadRequestResponseDemoUrl;
    private String errorNotFoundResponseDemoUrl;
    private String errorInternalServerErrorResponseDemoUrl;

}
