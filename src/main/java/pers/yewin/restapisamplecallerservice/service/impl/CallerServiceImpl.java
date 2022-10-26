package pers.yewin.restapisamplecallerservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pers.yewin.restapisamplecallerservice.config.AppConfig;
import pers.yewin.restapisamplecallerservice.model.request.StringListObject;
import pers.yewin.restapisamplecallerservice.model.request.Student;
import pers.yewin.restapisamplecallerservice.model.request.StudentListObject;
import pers.yewin.restapisamplecallerservice.model.response.*;
import pers.yewin.restapisamplecallerservice.service.CallerService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Ye Win
 * @created: 28/09/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.service.impl
 */

@Slf4j // for logging by using lombok
@Service // create bean class
public class CallerServiceImpl implements CallerService {
    /**
     * we already inject restTemplate as bean in RestapiSampleCallerServiceApplication class.
     * you can also inject by using restTemplate builder
     * you can also add some global request like Basic Auth in restTemplate by using builder in all every api request
     * if you want to customize restTemplate, you can also create customize class
     * you can also instantiate restTemplate by new RestTemplate() in every class and method.
     * but that will repeat restTemplate instantiate code and so, I do with below way.
     */
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppConfig appConfig;

    /**
     * You can reference reading properties values by using @Value in below url.
     * Refer to <a href="https://github.com/yewin-mm/reading-properties-file-values">Reading Properties File Values</a>
     */

    @Value("callerapp.api.receiverservice.basicauth.credentials.username")
    String receiverServiceUsername; // read properties value and inject that value into variable.

    @Value("callerapp.api.receiverservice.basicauth.credentials.password")
    String receiverServicePassword;

    @Value("classpath:test.xlsx") // read file with given name under resources folder
    Resource testResourceFile;


    private void callAPIByUsingRestTemplate(){

        // note - spring framework introduce WebClient in spring 5. So, restTemplate will Deprecate later, and you need to use WebClient to call api later.
        // WebClient support not only traditional synchronized api calling like restTemplate but also Asynchronous api calling.
        // but in this class, I will show demo api calling by using restTemplate, and it's good enough to use so far for synchronized api calling.
        // for WebClient api calling, you can find more in google.

        /**
         * there are popular 3 ways to call http get api by using restTemplate.
         * 1. Using getForEntity method // it's easy and understandable
         * 2. Using getForObject method // it's almost same with getForEntity except getting http response code and headers.
         * 3. Using exchange method // it's use when we need to add customize header, like basicAuth, Authorization, etc.
         */

        /**
         * there are popular 3 ways to call http post api by using restTemplate.
         * 1. Using postForEntity method // it's easy and understandable
         * 2. Using postForObject method // it's almost same with postForEntity except getting http response code and headers.
         * 3. Using exchange method // it's use when we need to add customize header, like basicAuth, Authorization, etc.
         */

        /**
         * I recommend to use getForEntity than getForObject because it can get http response status code.
         * I recommend to use postForEntity than postForObject because it can get http response status code.
         * But if it's need to add customize headers, like basicAuth, Authorization, etc, we need to use exchange method.
         * For more about calling api by using restTemplate, you can find more about in google.
         * You can find more usage for exchange method, postForEntity and getForEntity in google.
         */

    }

    @Override
    public CallerServiceResponse callResponseSingleStringDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        // create try catch to catch error while calling api.
        try {

            // getForEntity is used when you want to call Http GET method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.GET, httpEntity, String.class);

            // you can use URI type instead of url string (appConfig.getResponseSingleStringDemoUrl()) in restTemplate getForEntity, getForEntity methods.
//            URI url = new URI("http://localhost:8081/receiver-service/api/responseSingleStringDemo");

            log.info("API Calling info, url: {}, request data: {}", appConfig.getResponseSingleStringDemoUrl(), null);

            // add URL which you want to call and add Response Type from third party in restTemplate getForEntity method
            ResponseEntity<String> response =restTemplate.getForEntity(appConfig.getResponseSingleStringDemoUrl(), String.class); // string is response type
            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can do your logic with response data like storing into db or can do other logic and that's depend on your application logic.
            // but here, this project is demo and so, I don't do any logic and just return all response data.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                // create new object, and you can add data by using arguments constructor rather than using setter method.
                callerResponse = new CallerServiceResponse();
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ResponseSingleStringDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else{
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse();
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ResponseSingleStringDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }
        return callerResponse;

    }

    @Override
    public CallerServiceResponse callResponseSingleObjectDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            log.info("API Calling info, url: {}, request data: {}", appConfig.getResponseSingleObjectDemoUrl(), null);

            // count is response type from thirdparty, and you can also catch with string as response (it will json string format).
            // but if you catch with string for response, you need to convert it to pojo class to do other logic.
            // So, I created response pojo class and directly catch with that class to avoid getting json string.
            ResponseEntity<Count> response =restTemplate.getForEntity(appConfig.getResponseSingleObjectDemoUrl(), Count.class);
            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can do your logic with response data like storing into db or can do other logic and that's depend on your application logic.
            // but here, this project is demo and so, I don't do any logic and just return all response data.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ResponseSingleObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ResponseSingleObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;

    }

    @Override
    public CallerServiceResponse callResponseObjectDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            log.info("API Calling info, url: {}, request data: {}", appConfig.getResponseObjectDemoUrl(), null);

            // student is response type from thirdparty, and you can also catch with string as response (it will json string format).
            // but if you catch with string for response, you need to convert it to pojo class to do other logic.
            // So, I created response pojo class and directly catch with that class to avoid getting json string.
            ResponseEntity<Student> response =restTemplate.getForEntity(appConfig.getResponseObjectDemoUrl(), Student.class);
            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can do your logic with response data like storing into db or can do other logic and that's depend on your application logic.
            // but here, this project is demo and so, I don't do any logic and just return all response data.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ResponseObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ResponseObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callResponseStringListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            log.info("API Calling info, url: {}, request data: {}", appConfig.getResponseStringListDemoUrl(), null);

            // String List is response type from thirdparty, and you can also catch with string as response (it will json string format).
            // but if you catch with string for response, you need to convert it to pojo List class to do other logic.
            // So, I did catch with String [] to avoid getting json string.
            ResponseEntity<String[]> response =restTemplate.getForEntity(appConfig.getResponseStringListDemoUrl(), String[].class);
            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can do your logic with response data like storing into db or can do other logic and that's depend on your application logic.
            // but here, this project is demo and so, I don't do any logic and just return all response data.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ResponseStringListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ResponseStringListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callResponseObjectListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            log.info("API Calling info, url: {}, request data: {}", appConfig.getResponseObjectListDemoUrl(), null);

            // Student List is response type from thirdparty, and you can also catch with string as response (it will json string format).
            // but if you catch with string for response, you need to convert it to pojo List class to do other logic.
            // So, I created response pojo class and directly catch with that class to avoid getting json string.
            ResponseEntity<Student[]> response =restTemplate.getForEntity(appConfig.getResponseObjectListDemoUrl(), Student[].class);
            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can do your logic with response data like storing into db or can do other logic and that's depend on your application logic.
            // but here, this project is demo and so, I don't do any logic and just return all response data.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ResponseObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ResponseObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callResponseCustomObjectListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            log.info("API Calling info, url: {}, request data: {}", appConfig.getResponseCustomObjectListDemoUrl(), null);

            // StudentNameAndClass List is response type from thirdparty, and you can also catch with string as response (it will json string format).
            // but if you catch with string for response, you need to convert it to pojo List class to do other logic.
            // So, I created response pojo class and directly catch with that class to avoid getting json string.
            ResponseEntity<StudentNameAndClass[]> response =restTemplate.getForEntity(appConfig.getResponseCustomObjectListDemoUrl(), StudentNameAndClass[].class);
            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can do your logic with response data like storing into db or can do other logic and that's depend on your application logic.
            // but here, this project is demo and so, I don't do any logic and just return all response data.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ResponseCustomObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ResponseCustomObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callResponseNestedObjectDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            log.info("API Calling info, url: {}, request data: {}", appConfig.getResponseNestedObjectDemoUrl(), null);

            // ResponseObject is response type from thirdparty, and you can also catch with string as response (it will json string format).
            // but if you catch with string for response, you need to convert it to pojo class to do other logic.
            // So, I created response pojo class and directly catch with that class to avoid getting json string.
            ResponseEntity<ResponseObject> response =restTemplate.getForEntity(appConfig.getResponseNestedObjectDemoUrl(), ResponseObject.class);
            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can do your logic with response data like storing into db or can do other logic and that's depend on your application logic.
            // but here, this project is demo and so, I don't do any logic and just return all response data.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse();
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ResponseNestedObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse();
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ResponseNestedObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callResponseNestedObjectAndNestedObjectListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            log.info("API Calling info, url: {}, request data: {}", appConfig.getResponseNestedObjectAndNestedObjectListDemoUrl(), null);

            // ResponseObjectList is response type from thirdparty, and you can also catch with string as response (it will json string format).
            // but if you catch with string for response, you need to convert it to pojo class to do other logic.
            // So, I created response pojo class and directly catch with that class to avoid getting json string.
            ResponseEntity<ResponseObjectList> response =restTemplate.getForEntity(appConfig.getResponseNestedObjectAndNestedObjectListDemoUrl(), ResponseObjectList.class);
            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can do your logic with response data like storing into db or can do other logic and that's depend on your application logic.
            // but here, this project is demo and so, I don't do any logic and just return all response data.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ResponseNestedObjectAndNestedObjectListDemoNestedObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse();  // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ResponseNestedObjectAndNestedObjectListDemoNestedObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestBodyObjectDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {
            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request
//          headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // for form urlencoded request


            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */
            Student student = new Student();
            student.setName("Ye Win");
            student.setPhone("+959123456789");
            student.setAddress("Yangon");
            student.setClassName("Room A");

            // build request data and headers into HttpEntity.
            // here we can add student object into httpEntity directly.
            // but if request was the list object, you need to convert it to Json String format by using object mapper.
            HttpEntity<Student> httpEntity = new HttpEntity<>(student, headers);

            // we can also convert that student object into json format string by using objectMapper and can add that json format string into httpEntity.
            /*ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(student); // convert list object into json format string
            HttpEntity<Student> httpEntity = new HttpEntity<>(jsonString, headers);*/


            // you can build request like below instead of using java pojo class
            /*MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
            map.add("name", "Ye Win");
            map.add("phone", "+959123456789");
            map.add("address", "Yangon");
            map.add("className", "Room A");
            HttpEntity<Student> httpEntity = new HttpEntity<>(map, headers);*/

            // you can use URI type instead of url string in restTemplate getForEntity, postForEntity methods.
//            URI url = new URI("http://localhost:8081/receiver-service/api/requestBodyObjectDemo");

            log.info("API Calling info, url: {}, request data: {}, headers: {}", appConfig.getRequestBodyObjectDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());


            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getRequestBodyObjectDemoUrl(), httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // receiver service response with 201 created for this api and so, I checked with that code.
            if(response.getStatusCode() == HttpStatus.CREATED) {
                callerResponse = new CallerServiceResponse(); // create new object and you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestBodyObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // create new object and you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestBodyObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }
        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestBodyObjectListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // for form urlencoded request


            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            List<Student> stuList = Arrays.asList(new Student(1L, "Ye Win", "Yangon", "+959123456789", "Room A"),
                    new Student(2L, "Mg Mg", "Mandalay", "+959123456789", "Room B"),
                    new Student(3L, "Aung Aung", "NayPyiTaw", "+959123456789", "Room C"));

            StudentListObject stuListObject = new StudentListObject();
            stuListObject.setDataList(stuList); // prepare for request body as api request format.

            // build request data and headers into HttpEntity. here, you can use Map object or Json String format value rather than using Pojo Object in httpEntity.
            HttpEntity<StudentListObject> httpEntity = new HttpEntity<>(stuListObject, headers);

            log.info("API Calling info, url: {}, request data: {}, headers: {}", appConfig.getRequestBodyObjectListDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());

            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getRequestBodyObjectListDemoUrl(), httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestBodyObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestBodyObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestBodyStringListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // for form urlencoded request


            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            List<String> strList = Arrays.asList("Ye Win", "Mr. Ye Win"); // add using Arrays.asList instead of list.add("Ye Win"), etc.

            StringListObject strListObject = new StringListObject();
            strListObject.setStrListObject(strList); // prepare for request body as api request format.

            // build request data and headers into HttpEntity. here, you can use Map object or Json String format value rather than using Pojo Object in httpEntity.
            HttpEntity<StringListObject> httpEntity = new HttpEntity<>(strListObject, headers);

            log.info("API Calling info, url: {}, request data: {}, headers: {}", appConfig.getRequestBodyStringListDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());

            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getRequestBodyStringListDemoUrl(), httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestBodyStringListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestBodyStringListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestPathVariableIntegerDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            int id = 1; // prepare for path variable as api request, this is demo and so, I used hard coded value.
            // api request link is like http://localhost:8081/receiver-service/api/requestPathVariableIntegerDemo/{id}
            // you can also use map instead of integer id for adding multiple path variables.

            log.info("API Calling info, url: {}, request data: {}", appConfig.getRequestPathVariableIntegerDemoUrl(), id);

            // add url which you want to call, add Response Type from third party and add require parameters in restTemplate getForEntity method
            // path variable will auto add after we put parameters
            ResponseEntity<String> response = restTemplate.getForEntity(appConfig.getRequestPathVariableIntegerDemoUrl(), String.class, id);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            if(response.getStatusCode()==HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestPathVariableIntegerDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestPathVariableIntegerDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestMultiPathVariablesDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            // prepare for multi path variable by using Map as api request,
            // this is demo and so, I used hard coded values.
            Map<String, String> pathVarsMap = new HashMap<>();
            pathVarsMap.put("user profile","Java Developer");
            pathVarsMap.put("name","Ye Win");
            // api request link is like localhost:8081/receiver-service/api/requestMultiPathVariablesDemo/{profile}/{name}

            log.info("API Calling info, url: {}, request data: {}", appConfig.getRequestMultiPathVariablesDemoUrl(), pathVarsMap);

            // add url which you want to call, add Response Type from third party and add require parameters in restTemplate getForEntity method
            // path variable will auto add after we put parameters by using map
            ResponseEntity<String> response = restTemplate.getForEntity(appConfig.getRequestMultiPathVariablesDemoUrl(), String.class, pathVarsMap);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestMultiPathVariablesDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestMultiPathVariablesDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestParamStringDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Ye Win";
            // prepare url with param value like -> http://localhost:8081/receiver-service/api/requestParamStringDemo?name=Ye Win
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestParamStringDemoUrl())
                    .queryParam("name",nameValue)  // the key name call 'name' have to same with param field which need to send in api. (same with receiver api param field name)
                    .build();

            String url = urlBuilder.toUriString(); // it will -> http://localhost:8081/receiver-service/api/requestParamStringDemo?name=Ye Win

            log.info("API Calling info, url: {}, request data: {}", url, null);

            // add url which you want to call and add Response Type from third party in restTemplate getForEntity method
            // param value is already included in url variable
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestParamStringDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else{
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestParamStringDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestMultiParamsDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // for form urlencoded request


            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Ye Win";
            long id = 1;
            // prepare url with param value like -> http://localhost:8081/receiver-service/api/requestMultiParamsDemo?id=1&name=Mr.Ye Win
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestMultiParamsDemoUrl())
                    .queryParam("id",id)
                    .queryParam("name",nameValue)
                    .build();

            String url = urlBuilder.toUriString(); // it will -> http://localhost:8081/receiver-service/api/requestMultiParamsDemo?id=1&name=Mr.Ye Win

            HttpEntity httpEntity = new HttpEntity(headers); // there is no Body data, so I don't put that in HttpEntity

            log.info("API Calling info, url: {}, request data: {}, headers: {}", url, null, httpEntity.getHeaders());

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate exchange method
            // receiver application requested as PUT method, so, I used restTemplate exchange method instead of getForEntity method
            // param values is already included in url variable
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestMultiParamsDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestMultiParamsDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestPathVariableAndParamsDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {


            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            // prepare for path variable. here, I don't use map as we need only single variable as api request.
            String nameValue = "Ye Win";

            String actionType = "bill";
            // prepare url with param value like -> http://localhost:8081/receiver-service/api/requestPathVariableAndParamsDemo?actionType=Bill
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestPathVariableAndParamsDemoUrl())
                    .queryParam("actionType",actionType)
                    .build();

            String url = urlBuilder.toUriString(); // it will -> http://localhost:8081/receiver-service/api/requestPathVariableAndParamsDemo?actionType=Bill

            log.info("API Calling info, url: {}, request data: {}", url, nameValue);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate getForEntity method
            // param values is already included in url variable -> final api will be -> localhost:8081/receiver-service/api/requestPathVariableAndParamsDemo/Ye Win?actionType=Bill
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, nameValue);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestPathVariableAndParamsDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestPathVariableAndParamsDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            // old way to add username and password as basicAuth in HttpHeaders.
            // create auth credentials
            /*String authStr = receiverServiceUsername + ":" + receiverServicePassword;
            String base64Credentials = Base64.getEncoder().encodeToString(authStr.getBytes());

            headers.add("Authorization", "Basic " + base64Credentials);*/

            // after spring framework 5, you can add directly into headers by setBasicAuth method like below
            headers.setBasicAuth(receiverServiceUsername, receiverServicePassword);


            // build request data and headers into HttpEntity.
            // Receiver API (server/other service/ThirdParty api) don't request body data. So, I don't put that in HttpEntity.
            HttpEntity httpEntity = new HttpEntity<>(headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", appConfig.getRequestFromHeaderDemoUrl(), null, httpEntity.getHeaders());

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            // here, we can't use getForEntity as we need to add headers, So, I used exchange method.
            ResponseEntity<String> response = restTemplate.exchange(appConfig.getRequestFromHeaderDemoUrl(), HttpMethod.GET, httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderAndRequestParamDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Ye Win";
            // prepare url with param value
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestFromHeaderAndRequestParamDemoUrl())
                    .queryParam("name",nameValue)  // the key name call 'name' have to same with param field which need to send in api. (same with receiver api param field name)
                    .build();

            String url = urlBuilder.toUriString(); // it will -> http://localhost:8081/receiver-service/api/requestFromHeaderAndRequestParamDemo?name=Ye Win


            // build request data and headers into HttpEntity.
            // Receiver API (server/other service/ThirdParty api) don't request body data. So, I don't put that in httpEntity.
            HttpEntity httpEntity = new HttpEntity<>(headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", url, null, httpEntity.getHeaders());

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            // here, we can't use getForEntity as we need to add headers, So, I used exchange method.
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestParamDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestParamDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderAndRequestBodyDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */
            Student student = new Student();
            student.setName("Ye Win");
            student.setPhone("+959123456789");
            student.setAddress("Yangon");
            student.setClassName("Room A");

            // build request data and headers into HttpEntity.
            HttpEntity<Student> httpEntity = new HttpEntity<>(student, headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", appConfig.getRequestFromHeaderAndRequestBodyDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());

            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);
            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getRequestFromHeaderAndRequestBodyDemoUrl(), httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestBodyDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestBodyDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFileUploadDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();

            /** here, I put MediaType as MULTIPART_FORM_DATA in headers because we are going to add file as input in api */
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);


            /**
             * For request, you can get that require file from your api request or from file server or your application or other place or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded file which in our application.
             */

            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            // get file under resources folder from our project path by using @Value and add into map
            map.add("file", testResourceFile);
            // you can use below way to get file under resources folder
//            map.add("file", getFileResource("test.xlsx")); // get file under resources folder from our project path



            // build file which are in map as above step and headers into HttpEntity.
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", appConfig.getRequestFileUploadDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());

            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);
            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getRequestFileUploadDemoUrl(), httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFileUploadDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFileUploadDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderAndBulkFileUploadDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();

            /** here, I put MediaType as MULTIPART_FORM_DATA in headers because we are going to add file as input in api */
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.


            /**
             * For request, you can get that require file from your api request or from file server or your application or other place or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded file which in our application.
             */
            Resource re = getFileResource("test.xlsx");
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("files", getFileResource("test.xlsx")); // get file under resources folder and add into map
            map.add("files", getFileResource("test2.xlsx")); // get file under resources folder


            // build file which are in map as above step and headers into HttpEntity.
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", appConfig.getRequestFromHeaderAndBulkFileUploadDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());

            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);
            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getRequestFromHeaderAndBulkFileUploadDemoUrl(), httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndBulkFileUpload API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndBulkFileUpload API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }


    public Resource getFileResource(String fileName) {
        return new ClassPathResource(fileName);
    }

    @Override
    public CallerServiceResponse callRequestPathVariableAndResponseObjectDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String actionType = "bill payment"; // prepare for path variable as api request, this is demo and so, I used hard coded value.

            // you can also use map instead of integer id for adding multiple path variables.

            log.info("API Calling info, url: {}, request data: {}", appConfig.getRequestPathVariableAndResponseObjectDemoUrl(), actionType);

            // add url which you want to call, add Response Type from third party and add require parameters in restTemplate getForEntity method
            // path variable will auto add after we put parameters
            // catch response object with pojo class as api response fields
            ResponseEntity<Student> response = restTemplate.getForEntity(appConfig.getRequestPathVariableAndResponseObjectDemoUrl(), Student.class, actionType);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            if(response.getStatusCode()==HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestPathVariableAndResponseObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestPathVariableAndResponseObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestParamStringAndResponseObjectListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Ye Win";
            String phone = "+959123456789";
            // prepare url with param value
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestParamStringAndResponseObjectListDemoUrl())
                    .queryParam("name",nameValue)
                    .queryParam("phone",phone)
                    .build();

            String url = urlBuilder.toUriString();

            log.info("API Calling info, url: {}, request data: {}", url, null);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate getForEntity method
            // param values is already included in url variable
            // catch response object with pojo class as api response fields
            // response is object list, so, I did catch response object as list [] in response
            ResponseEntity<Student []> response = restTemplate.getForEntity(url, Student [].class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestParamStringAndResponseObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestParamStringAndResponseObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */
            Student student = new Student();
            student.setName("Ye Win");
            student.setPhone("+959123456789");
            student.setAddress("Yangon");
            student.setClassName("Room A");

            // build request data and headers into HttpEntity.
            HttpEntity<Student> httpEntity = new HttpEntity<>(student, headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", appConfig.getRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());

            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);
            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<ResponseObject> response = restTemplate.postForEntity(appConfig.getRequestFromHeaderAndRequestBodyAndResponseNestedObjectDemoUrl(), httpEntity, ResponseObject.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestBodyAndResponseNestedObjectDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Ye Win";
            String phone = "+959123456789";
            // prepare url with param value
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemoUrl())
                    .queryParam("name",nameValue)
                    .queryParam("phone",phone)
                    .build();

            String url = urlBuilder.toUriString();


            // build request data and headers into HttpEntity.
            // Receiver API (server/other service/ThirdParty api) don't request body data. So, I don't put that in httpEntity.
            HttpEntity httpEntity = new HttpEntity<>(headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", url, null, httpEntity.getHeaders());

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate exchange method
            // here, we can't use getForEntity as we need to add headers, So, I used exchange method.
            // catch response object with pojo class as api response fields
            ResponseEntity<ResponseObjectList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ResponseObjectList.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndRequestParamStringAndResponseNestedObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request

            // sample hard coded token
            String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXBlcmFkbWluQGdtYWlsLmNvbSIsInJvbGVzIjpbIkFETUlOIiwiTUFOQUdFUiIsIk5PUk1BTF9VU0VSIiwiU1VQRVJfQURNSU4iXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3NwcmluZy1zZWN1cml0eS1qd3QvbG9naW4iLCJleHAiOjE2NjMwMTczMDl9.qWnl1aA63p2XXchc6P4Kv2yS4ghVCnEdwB0-06-0qXU";
            String authorizationHeaderValue = "Bearer "+token; // add Bearer in front of token.

            headers.set("Authorization", authorizationHeaderValue); // add Bearer token as Authorization in header.

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            Map<String, String> pathVarsMap = new HashMap<>();
            pathVarsMap.put("accName","YE WIN"); // hard coded values, real values will get from somewhere else in real world project
            pathVarsMap.put("card","VISA");

            // hard coded values, real values will get from somewhere else in real world project
            int pageNumber = 0;
            int pageSize = 10;
            String sortBy = "id";
            String actionType = "Bill";
            // prepare url with param value
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getRequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemoUrl())
                    .queryParam("pageNo",pageNumber)
                    .queryParam("pageSize",pageSize)
                    .queryParam("sortBy",sortBy)
                    .queryParam("actionType",actionType)
                    .build();

            String url = urlBuilder.toUriString();
            // final url will be ->
            // http://localhost:8081/receiver-service/api/requestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo/accountName/{accName}/cardType/{card}?pageNo=0&pageSize=10&sortBy=Id&actionType=Bill

            // build request data and headers into HttpEntity.
            // Receiver API (server/other service/ThirdParty api) don't request body data. So, I don't put that in httpEntity.
            HttpEntity httpEntity = new HttpEntity<>(headers);

            log.info("API Calling info, url: {}, request data: {}, headers:{}", url, pathVarsMap, httpEntity.getHeaders());

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate exchange method
            // here, we can't use getForEntity as we need to add headers, So, I used exchange method.
            // catch response object with pojo class as api response fields
            ResponseEntity<ResponseObjectList> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, ResponseObjectList.class, pathVarsMap);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call RequestFromHeaderAndPathVarAndParamsAndResponseNestedObjectListDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callErrorBadRequestResponseDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            String nameValue = "Ye Win";
            String phoneValue = "+959123456789";
            // prepare url with param value like -> http://localhost:8081/receiver-service/api/requestMultiParamsDemo?id=1&name=Mr.Ye Win
            UriComponents urlBuilder = UriComponentsBuilder.fromHttpUrl(appConfig.getErrorBadRequestResponseDemoUrl())
                    .queryParam("name",nameValue)
                    .queryParam("phone",phoneValue)
                    .build();

            String url = urlBuilder.toUriString(); // it will -> http://localhost:8081/receiver-service/api/errorBadRequestResponseDemo?name=Ye Win&phone=+959123456789

            log.info("API Calling info, url: {}, request data: {}", url, null);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate getForEntity method
            // param values is already included in url variable
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ErrorBadRequestResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ErrorBadRequestResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                ObjectMapper objectMapper = new ObjectMapper();
                // here we can predict api response by calling via Postman first and catch with same field java pojo class to convert json string response to object by using objectMapper.
                ResponseObjectList responseObject = objectMapper.readValue(response.getBody(), ResponseObjectList.class);
                callerResponse.setThirdPartyResponseData(responseObject);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callErrorNotFoundResponseDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {

            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */

            // prepare for multi path variable by using Map as api request,
            // this is demo and so, I used hard coded values.
            Map<String, Long> pathVarsMap = new HashMap<>();
            pathVarsMap.put("id",1L);
            // api request link is like localhost:8081/receiver-service/api/errorNotFoundResponseDemo/{id}

            log.info("API Calling info, url: {}, request data: {}", appConfig.getErrorNotFoundResponseDemoUrl(), pathVarsMap);

            // add url which you want to call, add Response Type from third party and add require parameters in restTemplate getForEntity method
            // path variable will auto add after we put parameters by using map
            ResponseEntity<String> response = restTemplate.getForEntity(appConfig.getErrorNotFoundResponseDemoUrl(), String.class, pathVarsMap);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.


            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            // check response code for success or fail
            if(response.getStatusCode() == HttpStatus.OK) {
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ErrorNotFoundResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ErrorNotFoundResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                ObjectMapper objectMapper = new ObjectMapper();
                // here we can predict api response by calling via Postman first and catch with same field java pojo class to convert json string response to object by using objectMapper.
                ResponseObjectList responseObject = objectMapper.readValue(response.getBody(), ResponseObjectList.class);
                callerResponse.setThirdPartyResponseData(responseObject);
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }

        return callerResponse;
    }

    @Override
    public CallerServiceResponse callErrorInternalServerErrorResponseDemo() {

        /** @see #callAPIByUsingRestTemplate() method for reference */
        // you can go above method behind # by ctrl + left-click on that method name.

        CallerServiceResponse callerResponse;
        try {
            // prepare for header type.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON); // for body json type request
//          headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // for form urlencoded request


            /**
             * For request, you can get that require data from your api request or you can get require data from db or other class or etc..
             * But here, this is demo project and so, I don't take require data from other places, I will go with hard coded values.
             */
            Student student = new Student();
            student.setName("Ye Win");
            student.setPhone("+959123456789");
            student.setAddress("Yangon");
            student.setClassName("Room A");

            HttpEntity<Student> httpEntity = new HttpEntity<>(student, headers);

            // you can use URI type instead of url string in restTemplate getForEntity, postForEntity methods.
//            URI url = new URI("http://localhost:8081/receiver-service/api/requestBodyObjectDemo");

            log.info("API Calling info, url: {}, request data: {}, headers: {}", appConfig.getErrorInternalServerErrorResponseDemoUrl(), httpEntity.getBody(), httpEntity.getHeaders());


            // postForEntity is used when you want to call Http POST method based api.
            // you can also use restTemplate exchange method in here, eg. exchange(url, HttpMethod.POST, httpEntity, String.class);

            // add URL which you want to call, add httpEntity which included headers and require data to call api and add Response Type from third party in restTemplate postForEntity method
            ResponseEntity<String> response = restTemplate.postForEntity(appConfig.getErrorInternalServerErrorResponseDemoUrl(), httpEntity, String.class);

            log.info("thirdparty response status code: {}", response.getStatusCode().value());
            log.info("thirdparty response data: {}", response.getBody());

            // thirdparty service don't response for this api and so, response body will be null.
            // you can check response http status code for your api calling process is success or not by checking 200, 201, etc.

            /**
             * @see pers.yewin.restapisamplecallerservice.apierrorhandler.CustomizeRestTemplateErrorHandler#hasError(ClientHttpResponse) method
             * without above method, we can't check http status code and it will always go into catch block if status code was error (4xx or 5xx) code
             */
            if(response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
                callerResponse = new CallerServiceResponse(); // create new object and you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Response from third party");
                callerResponse.setDescription("call ErrorInternalServerErrorResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());

            }else {
                /**
                 * Here, you can do your logic for error handling if thirdparty api calling was something wrong.
                 * You can check api error response by checking http status code like this if-else condition.
                 * eg. saving error code, message into db or return customize error response message to frontend or other service or etc.
                 * eg. you can also call another api if first api was error, like that, depend on your application logic.
                 * but, here, I don't do any logic as this is demo project and so, I did directly return that error message.
                 */
                callerResponse = new CallerServiceResponse(); // create new object and you can add data by using arguments constructor rather than using setter method.
                callerResponse.setMessage("Error Response from third party");
                callerResponse.setDescription("call ErrorInternalServerErrorResponseDemo API");
                callerResponse.setThirdPartyResponseStatusCode(response.getStatusCodeValue());
                callerResponse.setThirdPartyResponseData(response.getBody());
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("error: {}", e.getMessage());
            // create new object and add error response by using arguments constructor.
            callerResponse = new CallerServiceResponse("Error while calling api", e.getMessage(), 500, null);
        }
        return callerResponse;
    }
}
