
package com.llince.model.rest.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 *
 * @author LALince
 */
@Component( "restAuthenticationEntryPoint" )
public class RestAuthenticationEntryPoint
  implements AuthenticationEntryPoint{

	
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response, 
            org.springframework.security.core.AuthenticationException authException) 
            throws IOException, ServletException {
         if(isPreflight(request)){
        	response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

	private boolean isPreflight(HttpServletRequest request) {
		return "OPTIONS".equals(request.getMethod());
	}

}
