package com.mkboss.MkbossManage.Event.Listener;

import com.mkboss.MkbossManage.Auth.AuthenticationService;
import com.mkboss.MkbossManage.Email.EmailService;
import com.mkboss.MkbossManage.Entity.User;
import com.mkboss.MkbossManage.Event.RegistrationCompleteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the Verification Token for the User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        authenticationService.saveVerificationTokenForUser(token,user);
        //Send Mail to user
        String url =
                event.getApplicationUrl()
                        + "/api/v1/auth/verifyRegistration?token="
                        + token;
        //sendVerificationEmail()
        emailService.sendEmail(user.getEmail(),"Verify account","Click the link to verify your account:" + url);
        log.info("Email send successfull");
    }
}
