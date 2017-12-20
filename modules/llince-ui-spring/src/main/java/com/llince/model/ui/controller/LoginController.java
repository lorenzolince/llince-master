/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.ui.controller;

import com.llince.model.ui.dto.Login;
import com.llince.model.ui.dto.LoginStatus;
import com.llince.model.ui.dto.StatusLogin;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author loren
 */
@Controller
public class LoginController {
     
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("name", "Lorenzo Lince");
        return "login";
    }

    @RequestMapping(value = "/Welcome", method = RequestMethod.POST)
    public String welcome(ModelMap model, LoginStatus status) {
        if (status != null) {
            System.out.println("getUsername:  " + status.getUsername());
            System.out.println("isLoggedIn:  " + status.isLoggedIn());
            if (status.getUserDetail() != null) {
                System.out.println("getLastName:  " + status.getUserDetail().getLastName());
            }
        }
        model.addAttribute("profile", status);
        return "Welcome";
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public String statusTemplate(ModelMap model, HttpServletRequest request, HttpSession session) {
        System.out.println("######### status #######  " + session.getId());
        String doamin="http://";
        String server="/lurin-rest-spring-security-1.0-SNAPSHOT";
        String opertion="/rest/loginStatus";
        try {
//             doamin += InetAddress.getLocalHost().getHostName()+":"+request.getServerPort();
             doamin += InetAddress.getLocalHost().getHostName()+":8084";
        } catch (UnknownHostException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        String token = request.getParameter("token");
        System.out.println("token:  " + token);
        String url = doamin+server+opertion;
        System.out.println("url:  " + url);
        StatusLogin sttus = getRestService(StatusLogin.class, url, token);
        if (sttus != null) {
            System.out.println("getUsername:  " + sttus.getUsername());
            System.out.println("isLoggedIn:  " + sttus.isLoggedIn());
        }
        return "login";
    }

    private  HttpEntity<String> getAuthHeader(String token) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", token);
        return new HttpEntity<String>(requestHeaders);
    }
    private  <T> T getRestService(Class<T> objeto, String url,String token) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity rssResponse=null;
        try {
           rssResponse = restTemplate.exchange(url,
                HttpMethod.GET,
                getAuthHeader(token),
                objeto);   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    return (T) rssResponse.getBody();
    }
     private  <T> T postRestService(Class<T> objeto, String url,String token) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity rssResponse = restTemplate.exchange(url,
                HttpMethod.POST,
                getAuthHeader(token),
                objeto);
    return (T) rssResponse.getBody();
    }
}
