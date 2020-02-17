package com.dhruv.demo.model.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    private String userName;
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String city;
    private Date dateOfBirth;
    private String gender;
    private byte deleted;
    private String contactNumber;
    private String date;
}
