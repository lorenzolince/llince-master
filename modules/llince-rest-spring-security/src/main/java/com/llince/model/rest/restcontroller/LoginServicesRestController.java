package com.llince.model.rest.restcontroller;

import com.llince.model.rest.dto.UserDto;
import com.llince.model.rest.security.Login;
import com.llince.model.rest.security.LoginStatus;
import com.llince.model.rest.security.MySavedRequestAwareAuthenticationSuccessHandler;
import com.llince.model.rest.security.ValidStatus;
import com.llince.model.rest.service.UserService;
import com.llince.model.rest.util.Emcriptacion;
import java.net.InetAddress;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LLince
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginServicesRestController {

    private static final Logger logger = Logger.getLogger(LoginServicesRestController.class);
    @Autowired
    MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;
    @Autowired
    UserService userService;
    @Autowired
    @Qualifier("authenticationManager")
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/rest/loginStatus", method = RequestMethod.GET)
    @ResponseBody
    public ValidStatus getStatus(Principal principal, HttpSession session, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            if (auth != null && !auth.getName().equals("anonymousUser") && auth.isAuthenticated()) {

                return new ValidStatus(true, auth.getName(), "JSESSIONID=" + session.getId() + "." + InetAddress.getLocalHost().getHostName());
            } else {
                return new ValidStatus(false, null, null);
            }
        } catch (Exception e) {
            logger.error("Error loginStatus", e);
            return new ValidStatus(false, null, null);
        }
    }
   
    @RequestMapping(value = "/rest/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginStatus login(@RequestBody Login userReguest, HttpServletRequest request,
            HttpServletResponse response, HttpSession session
    ) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userReguest.getJ_username(), userReguest.getJ_password());
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            mySuccessHandler.onAuthenticationSuccess(request, response, auth);
            UserDto us = userService.get(auth.getName());
            return new LoginStatus(auth.isAuthenticated(), auth.getName(), "JSESSIONID=" + session.getId() /*+ "." + InetAddress.getLocalHost().getHostName()*/, us);
        } catch (Exception e) {
            logger.error("Error Login", e);
            return new LoginStatus(false, null, null, null);
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Login> index() {
        Login response = new Login();
        response.setJ_username("Lorenzo");
        response.setJ_password("23456");
        return new ResponseEntity<Login>(response, HttpStatus.OK);
    } 

    @RequestMapping(value = "/rest/logout", method = RequestMethod.GET)
    public @ResponseBody
    LoginStatus logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new LoginStatus(false, null, null, null);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody
    String logoutt() {
        System.out.println(Emcriptacion.encripta("admin", "Md5"));
        return "succes";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public @ResponseBody
    LoginStatus ini(HttpSession session, HttpServletRequest request) {
        try {
            return new LoginStatus(true, getPrincipal(), "JSESSIONID=" + session.getId() + "." + InetAddress.getLocalHost().getHostName(), null);
        } catch (Exception e) {
            logger.error("Error welcome", e);
            return new LoginStatus(false, null, null, null);
        }
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public LoginStatus loginerror(HttpSession session) {
        return new LoginStatus(false, "Login failed", null, null);
    }

    @RequestMapping(value = "/expiresession", method = RequestMethod.GET)
    public LoginStatus expiresession(HttpSession session) {
        try {
            return new LoginStatus(false, "Expire session", "JSESSIONID=" + session.getId() + "." + InetAddress.getLocalHost().getHostName(), null);
        } catch (Exception e) {
            logger.error("Error welcome", e);
            return new LoginStatus(false, null, null, null);
        }
    }

    /**
     * Muestra la pantalla de acceso denegado
     *
     * @return String
     */
    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String accessdenied() {
        return "accessdenied";
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.POST)
    public String accessdenied2() {
        return "accessdenied";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
