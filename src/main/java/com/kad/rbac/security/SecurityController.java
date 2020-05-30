package com.kad.rbac.security;
import com.kad.rbac.security.model.AuthUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


@Controller
public class SecurityController  {

   private AuthUserDetail user;
   public AuthUserDetail getAuthCurrentUser() {


         user = (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return user;
   }


}
