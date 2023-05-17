package com.mkboss.MkbossManage.Controller;

import com.mkboss.MkbossManage.Auth.AuthenticationRequest;
import com.mkboss.MkbossManage.Auth.AuthenticationResponse;
import com.mkboss.MkbossManage.Auth.AuthenticationService;
import com.mkboss.MkbossManage.Email.EmailService;
import com.mkboss.MkbossManage.Entity.User;
import com.mkboss.MkbossManage.Entity.VerificationToken;
import com.mkboss.MkbossManage.Event.RegistrationCompleteEvent;
import com.mkboss.MkbossManage.Model.PasswordChangeModel;
import com.mkboss.MkbossManage.Model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final ApplicationEventPublisher publisher;
    private final UserDetailsService userDetailsService;
    @PostMapping("/register")
    private ResponseEntity<AuthenticationResponse> register(@RequestBody UserModel user, HttpServletRequest request){
        AuthenticationResponse authenticationResponse = authenticationService.register(user);
        publisher.publishEvent(new RegistrationCompleteEvent(
                authenticationService.findUserByEmail(user.getEmail()),
                applicationUrl(request)
        ));
        return ResponseEntity.ok(authenticationResponse);
    }
    @GetMapping("/authenticate")
    private ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = authenticationService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")) {
            return "User Verified Successfully";
        }
        return "Bad User";
    }
    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request) {
        VerificationToken verificationToken
                = authenticationService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link Sent";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam PasswordChangeModel passwordChangeModel){
        User user = authenticationService.findUserByEmail(passwordChangeModel.getEmail());
        if (!authenticationService.checkIfValidOldPassword(user, passwordChangeModel.getOldPassword())){
            return "Old and New password do not match";
        }
        authenticationService.changePassword(user, passwordChangeModel.getNewPassword());
        return "Change password successfully";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url =
                applicationUrl
                        + "/verifyRegistration?token="
                        + verificationToken.getToken();

        //sendVerificationEmail()
        emailService.sendEmail(user.getEmail(),"Verify account","Click the link to verify your account:" + url);
        log.info("Email send successfull");
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
