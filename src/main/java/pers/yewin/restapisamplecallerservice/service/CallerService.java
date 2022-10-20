package pers.yewin.restapisamplecallerservice.service;

import pers.yewin.restapisamplecallerservice.model.request.Student;
import pers.yewin.restapisamplecallerservice.model.request.StringListObject;
import pers.yewin.restapisamplecallerservice.model.request.StudentListObject;
import pers.yewin.restapisamplecallerservice.model.response.*;

import java.io.File;
import java.util.Map;

/**
 * @author: Ye Win
 * @created: 28/09/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.service
 */

public interface CallerService {

    /** to call response demo apis **/
    CallerServiceResponse callResponseSingleStringDemo();
    CallerServiceResponse callResponseSingleObjectDemo();
    CallerServiceResponse callResponseObjectDemo();
    CallerServiceResponse callResponseStringListDemo();
    CallerServiceResponse callResponseObjectListDemo();
    CallerServiceResponse callResponseCustomObjectListDemo();
    CallerServiceResponse callResponseNestedObjectDemo();
    CallerServiceResponse callResponseNestedObjectAndNestedObjectListDemo();


    /** to call request demo apis **/
    CallerServiceResponse callRequestBodyObjectDemo();
    CallerServiceResponse callRequestBodyObjectListDemo();
    CallerServiceResponse callRequestBodyStringListDemo();
    CallerServiceResponse callRequestPathVariableIntegerDemo();
    CallerServiceResponse callRequestMultiPathVariablesDemo();
    CallerServiceResponse callRequestParamStringDemo();
    CallerServiceResponse callRequestMultiParamsDemo();
    CallerServiceResponse callRequestPathVariableAndParamsDemo();
    CallerServiceResponse callRequestFromHeaderDemo();
    CallerServiceResponse callRequestFromHeaderAndRequestParamDemo();
    CallerServiceResponse callRequestFromHeaderAndRequestBodyDemo();
    CallerServiceResponse callRequestFileUploadDemo();
    CallerServiceResponse callRequestFromHeaderAndBulkFileUploadDemo();



    /** to call request and response demo apis **/
    CallerServiceResponse callRequestPathVariableAndResponseObjectDemo();
    CallerServiceResponse callRequestParamStringAndResponseObjectListDemo();
    CallerServiceResponse callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo();
    CallerServiceResponse callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo();
    CallerServiceResponse callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo();


    /** to call error demo apis **/
    CallerServiceResponse callErrorBadRequestResponseDemo();
    CallerServiceResponse callErrorNotFoundResponseDemo();
    CallerServiceResponse callErrorInternalServerErrorResponseDemo();
}
