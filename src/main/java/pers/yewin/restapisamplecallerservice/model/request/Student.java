package pers.yewin.restapisamplecallerservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Ye Win
 * @created: 28/09/2022
 * @project: restapi-sample-caller-service
 * @package: pers.yewin.restapisamplecallerservice.model.request
 */

@Data // create getter, setter and toString method by using lombok
@NoArgsConstructor // create default no argument constructor by using lombok
@AllArgsConstructor // create all argument constructor by using lombok
public class Student {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String className;

}
