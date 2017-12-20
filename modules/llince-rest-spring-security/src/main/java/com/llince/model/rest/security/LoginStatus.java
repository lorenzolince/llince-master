
package com.llince.model.rest.security;

import com.llince.model.rest.dto.UserDto;

/**
 *
 * @author LLince
 */
public class LoginStatus {
    private final boolean loggedIn;
    private final String username;
    private final String token;
    private UserDto userDetail;
    public LoginStatus(boolean loggedIn, String username,String token,UserDto userDetail) {
      this.loggedIn = loggedIn;
      this.username = username;
      this.token = token;
      this.userDetail = userDetail;
    }

    public boolean isLoggedIn() {
      return loggedIn;
    }

    public String getUsername() {
      return username;
    } 

    public String getToken() {
        return token;
    }

    public UserDto getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDto userDetail) {
        this.userDetail = userDetail;
    }
    
}
