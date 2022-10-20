package pers.yewin.restapisamplecallerservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Ye Win
 * @created: 28/09/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.model.response
 */

@Data // create getter, setter and toString method by using lombok
@NoArgsConstructor // create default no argument constructor by using lombok
@AllArgsConstructor // create all argument constructor by using lombok
public class CallerServiceResponse <T> {

    String message;
    String description;
    int thirdPartyResponseStatusCode;
    // T is generic data type and all other different data types can store into this variable
    T thirdPartyResponseData; // you need to take care about data type when you use generic type especially getting back data from that type.
}
