//package com.fiveis.andcrowd.config.oauth;
//
//import com.fiveis.andcrowd.enums.Role;
//import lombok.Getter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//
//import java.util.Collection;
//import java.util.Map;
//
//@Getter
//public class CustomOAuth2User extends DefaultOAuth2User {
//    private String userEmail;
//    private Role role;
//
//    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
//                            Map<String, Object> attributes,
//                            String nameAttributeKey,
//                            String userEmail,
//                            Role role){
//        super(authorities, attributes, nameAttributeKey);
//        this.userEmail = userEmail;
//        this.role = role;
//    }
//}
