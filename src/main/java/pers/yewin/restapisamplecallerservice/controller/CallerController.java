package pers.yewin.restapisamplecallerservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.yewin.restapisamplecallerservice.model.response.CallerServiceResponse;
import pers.yewin.restapisamplecallerservice.service.CallerService;

/**
 * @author: Ye Win
 * @created: 04/10/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.controller
 */

@RestController // for rest endpoint
@Slf4j // for logging by using lombok
@RequestMapping("/caller")
public class CallerController {

    @Autowired
    CallerService callerService;

    @GetMapping("/callResponseSingleStringDemoAPI")
    public ResponseEntity<CallerServiceResponse> callResponseSingleStringDemoAPI(){

        try{
            log.info("Enter callResponseSingleStringDemoAPI method");

            CallerServiceResponse callerResponse = callerService.callResponseSingleStringDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callResponseSingleStringDemoAPI method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callResponseSingleObjectDemoAPI")
    public ResponseEntity<CallerServiceResponse> callResponseSingleObjectDemo(){

        try{
            log.info("Enter callResponseSingleObjectDemo method");

            CallerServiceResponse callerResponse = callerService.callResponseSingleObjectDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callResponseSingleObjectDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callResponseObjectDemoAPI")
    public ResponseEntity<CallerServiceResponse> callResponseObjectDemo(){

        try{
            log.info("Enter callResponseObjectDemo method");

            CallerServiceResponse callerResponse = callerService.callResponseObjectDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callResponseObjectDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callResponseStringListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callResponseStringListDemo(){

        try{
            log.info("Enter callResponseStringListDemo method");

            CallerServiceResponse callerResponse = callerService.callResponseStringListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callResponseStringListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callResponseObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callResponseObjectListDemo(){

        try{
            log.info("Enter callResponseObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callResponseObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callResponseObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callResponseCustomObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callResponseCustomObjectListDemo(){

        try{
            log.info("Enter callResponseCustomObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callResponseCustomObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callResponseCustomObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callResponseNestedObjectDemoAPI")
    public ResponseEntity<CallerServiceResponse> callResponseNestedObjectDemo(){

        try{
            log.info("Enter callResponseNestedObjectDemo method");

            CallerServiceResponse callerResponse = callerService.callResponseNestedObjectDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callResponseNestedObjectDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callResponseNestedObjectAndNestedObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callResponseNestedObjectAndNestedObjectListDemo(){

        try{
            log.info("Enter callResponseNestedObjectAndNestedObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callResponseNestedObjectAndNestedObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callResponseNestedObjectAndNestedObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestBodyObjectDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestBodyObjectDemo(){

        try{
            log.info("Enter callRequestBodyObjectDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestBodyObjectDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestBodyObjectDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestBodyObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestBodyObjectListDemo(){

        try{
            log.info("Enter callRequestBodyObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestBodyObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestBodyObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestBodyStringListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestBodyStringListDemo(){

        try{
            log.info("Enter callRequestBodyStringListDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestBodyStringListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestBodyStringListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestPathVariableIntegerDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestPathVariableIntegerDemo(){

        try{
            log.info("Enter callRequestPathVariableIntegerDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestPathVariableIntegerDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestPathVariableIntegerDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestMultiPathVariablesDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestMultiPathVariablesDemo(){

        try{
            log.info("Enter callRequestMultiPathVariablesDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestMultiPathVariablesDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestMultiPathVariablesDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestParamStringDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestParamStringDemo(){

        try{
            log.info("Enter callRequestParamStringDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestParamStringDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestParamStringDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestMultiParamsDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestMultiParamsDemo(){

        try{
            log.info("Enter callRequestMultiParamsDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestMultiParamsDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestMultiParamsDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestPathVariableAndParamsDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestPathVariableAndParamsDemo(){

        try{
            log.info("Enter callRequestPathVariableAndParamsDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestPathVariableAndParamsDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestPathVariableAndParamsDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestFromHeaderDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderDemo(){

        try{
            log.info("Enter callRequestFromHeaderDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestFromHeaderAndRequestParamDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndRequestParamDemo(){

        try{
            log.info("Enter callRequestFromHeaderAndRequestParamDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndRequestParamDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndRequestParamDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestFromHeaderAndRequestBodyDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndRequestBodyDemo(){

        try{
            log.info("Enter callRequestFromHeaderAndRequestBodyDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndRequestBodyDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndRequestBodyDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestFileUploadDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFileUploadDemo(){

        try{
            log.info("Enter callRequestFileUploadDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFileUploadDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFileUploadDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestFromHeaderAndBulkFileUploadAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndBulkFileUpload(){

        try{
            log.info("Enter callRequestFromHeaderAndBulkFileUploadDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndBulkFileUploadDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndBulkFileUploadDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestPathVariableAndResponseObjectDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestPathVariableAndResponseObjectDemo(){

        try{
            log.info("Enter callRequestPathVariableAndResponseObjectDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestPathVariableAndResponseObjectDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestPathVariableAndResponseObjectDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestParamStringAndResponseObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestParamStringAndResponseObjectListDemo(){

        try{
            log.info("Enter callRequestParamStringAndResponseObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestParamStringAndResponseObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestParamStringAndResponseObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo(){

        try{
            log.info("Enter callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo(){

        try{
            log.info("Enter callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemoAPI")
    public ResponseEntity<CallerServiceResponse> callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo(){

        try{
            log.info("Enter callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo method");

            CallerServiceResponse callerResponse = callerService.callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callErrorBadRequestResponseDemoAPI")
    public ResponseEntity<CallerServiceResponse> callErrorBadRequestResponseDemo(){

        try{
            log.info("Enter callErrorBadRequestResponseDemo method");

            CallerServiceResponse callerResponse = callerService.callErrorBadRequestResponseDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callErrorBadRequestResponseDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callErrorNotFoundResponseDemoAPI")
    public ResponseEntity<CallerServiceResponse> callErrorNotFoundResponseDemo(){

        try{
            log.info("Enter callErrorNotFoundResponseDemo method");

            CallerServiceResponse callerResponse = callerService.callErrorNotFoundResponseDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callErrorNotFoundResponseDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/callErrorInternalServerErrorResponseDemoAPI")
    public ResponseEntity<CallerServiceResponse> callErrorInternalServerErrorResponseDemo(){

        try{
            log.info("Enter callErrorInternalServerErrorResponseDemo method");

            CallerServiceResponse callerResponse = callerService.callErrorInternalServerErrorResponseDemo();
            log.info("Response data: {}", callerResponse);

            log.info("Exit callErrorInternalServerErrorResponseDemo method");
            return ResponseEntity.ok().body(callerResponse); // return ok response with body
            // here you can return like that ->
            // return new ResponseEntity(callerResponse, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
