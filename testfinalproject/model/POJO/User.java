package javaproject.testfinalproject.model.POJO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {

    private long id;
    private String userName;
    private String userPassword;
    private String newPassword;
    private String verificationPassword;
    private String userEmail;
    private Boolean isAdmin;
}
