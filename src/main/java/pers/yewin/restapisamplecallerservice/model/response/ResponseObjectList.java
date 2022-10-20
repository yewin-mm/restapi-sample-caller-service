package pers.yewin.restapisamplecallerservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.yewin.restapisamplecallerservice.model.request.Student;

import java.util.List;

/**
 * @author: Ye Win
 * @created: 28/09/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.model.response
 */

// this class will customize response class
@Data // create getter, setter and toString method by using lombok
@NoArgsConstructor // create default no argument constructor by using lombok
@AllArgsConstructor // create all argument constructor by using lombok
public class ResponseObjectList <T> {
    private String timestamp;
    private StatusObject status;
    // T is generic data type and all other different data types can store into this variable
    private T dataList; // you need to take care about data type when you use generic type especially getting back data from that type.
}
