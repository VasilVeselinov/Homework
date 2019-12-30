package javaproject.testfinalproject.controller;

import javaproject.testfinalproject.data_validators.UserValidator;
import javaproject.testfinalproject.model.DB_and_DAO.UserDAO;
import javaproject.testfinalproject.model.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class UserController {

    private static final String WRONG_CREDENTIALS = "Your username, email or password is wrong!";
    private static final String FAILED_CREDENTIALS = "Validate your data is failed!";
    private static final String WRONG_PASSWORD = "Invalid password!";
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserValidator userValidator;


    @GetMapping(value = "/TestHello")
    public String saiHello() {
        return "First hello from test final project!";
    }

    @PostMapping(value = "/user/registration")
    public void regUser(@RequestBody User user, HttpServletResponse response) {
        try {
            if (
                    user.getUserName().isEmpty() ||
                            user.getUserEmail().isEmpty() ||
                            user.getUserPassword().isEmpty() ||
                            user.getVerificationPassword().isEmpty()
            ) {
                response.setStatus(400);
                try {
                    response.getWriter().append(WRONG_CREDENTIALS);
                    return;
                } catch (IOException e) {
                    // return message by response
                    response.setStatus(500);
                    System.out.println(e.getMessage());
                }
            }
            boolean checkLengthUsername = this.userValidator.usernameLengthCheck(user.getUserName());
            boolean checkUsernameExists = this.userDAO.existsByUsername(user.getUserName());
            boolean checkValidEmail = this.userValidator.emailValidation(user.getUserEmail());
            boolean checkEmailExists = this.userDAO.existsByEmail(user.getUserEmail());
            boolean checkValidPassword = this.userValidator.passwordValidation(user.getUserPassword());
            boolean checkVerification = user.getUserPassword().equals(user.getVerificationPassword());

            if (!checkLengthUsername || checkUsernameExists ||
                    !checkValidEmail || checkEmailExists ||
                    !checkValidPassword || !checkVerification
            ) {
                response.setStatus(400);
                response.getWriter().append(FAILED_CREDENTIALS);
            } else {
                this.userDAO.registerUser(user);
                response.getWriter().append("You are successful registration!");
                // todo response URL /user/loginForm
            }
        } catch (IOException e) {
            // return message by response
            response.setStatus(500);
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // return message by response
            response.setStatus(500);
            System.out.println(e.getMessage());
        }
    }

    @PostMapping(value = "/user/login")
    public void loginUser(@RequestBody User user, HttpServletResponse response) {
        if (user.getUserName().isEmpty() || user.getUserEmail().isEmpty() || user.getUserPassword().isEmpty()) {
            response.setStatus(400);
            try {
                response.getWriter().append(WRONG_CREDENTIALS);
            } catch (IOException e) {
                // return message by response
                response.setStatus(500);
                System.out.println(e.getMessage());
            }
        } else {
            try {
                User loginUser = this.userDAO.loginUser(user);
                if (loginUser == null) {
                    response.setStatus(400);
                    try {
                        response.getWriter().append(WRONG_CREDENTIALS);
                    } catch (IOException e) {
                        // return message by response
                        response.setStatus(500);
                        System.out.println(e.getMessage());
                    }
                } else {
                    response.getWriter().append("You are successful login!");
                }
            } catch (Exception e) {
                // return message by response
                response.setStatus(500);
                System.out.println(e.getMessage());
            }
        }
    }

    @PutMapping(value = "/user/change_password")
    public void changePasswordOfUser(@RequestBody User user, HttpServletResponse response) {
        System.out.println(user.toString());
        try {
            String passwordOfExistUser = this.userDAO.searchUserByUsernameReturnPasswordAndSetID(user);
            System.out.println("userExistsWithPassword = " + passwordOfExistUser);
            boolean dbCheckVerification = passwordOfExistUser.equals(user.getUserPassword());
            System.out.println("dbCheckVerification = " + dbCheckVerification);
            boolean checkValidNewPassword = this.userValidator.passwordValidation(user.getNewPassword());
            System.out.println("checkValidNewPassword = " + checkValidNewPassword);
            boolean checkVerification = user.getNewPassword().equals(user.getVerificationPassword());
            System.out.println("checkVerification = " + checkVerification);
            if (
                    dbCheckVerification &&
                            checkValidNewPassword &&
                            checkVerification
            ) {
                if (this.userDAO.changePassword(user) > 0) {
                    response.getWriter().append("You are successful change password!");
                } else {
                    response.getWriter().append("Please try again!");
                }
            } else {
                response.setStatus(400);
                response.getWriter().append(WRONG_PASSWORD);
            }
        } catch (Exception e) {
            // return message by response
            response.setStatus(500);
            System.out.println(e.getMessage());
        }
    }

    @GetMapping( value = "/user/logout")
    public String logoutUser(HttpSession session, HttpServletResponse response) {
        if (session.getAttribute("id") == null) {
            response.setStatus(405);
            return "LoginForm.html";
        }
        session.setAttribute("id", null);
        return "You are successful logout!";
    }

    // todo to connect to the html file
    @GetMapping(value = "/user/RegistrationForm")
    public String userRegistrationForm(){
        return "RegistrationForm.html";
    }

    // todo to connect to the html file
    @GetMapping(value = "/user/loginForm")
    public String userPushLoginButton(){
        return "LoginForm.html";
    }
}
