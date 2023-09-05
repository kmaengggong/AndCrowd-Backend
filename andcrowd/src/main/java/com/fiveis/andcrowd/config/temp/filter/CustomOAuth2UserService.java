//package com.fiveis.andcrowd.config.oauth;
//
//import com.fiveis.andcrowd.entity.user.User;
//import com.fiveis.andcrowd.enums.SocialType;
//import com.fiveis.andcrowd.repository.user.UserJPARepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.Map;
//
//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    private final UserJPARepository userJPARepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        SocialType socialType = getSocialType(registrationId);
//        String userNameAttributeName = userRequest.getClientRegistration()
//                .getProviderDetails()
//                .getUserInfoEndpoint()
//                .getUserNameAttributeName();
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        OAuthAttributesDTO extractAttributesDTO = OAuthAttributesDTO.of(socialType,
//                userNameAttributeName,
//                attributes);
//        User createdUser = getUser(extractAttributesDTO, socialType);
//
//        return new CustomOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().getKey())),
//                    attributes,
//                    extractAttributesDTO.getNameAttributeKey(),
//                    createdUser.getUserEmail(),
//                    createdUser.getRole()
//        );
//    }
//
//    private SocialType getSocialType(String registrationId){
//        if(registrationId.equals("naver")){
//            return SocialType.NAVER;
//        }
//        else return null;
//    }
//
//    private User getUser(OAuthAttributesDTO attributesDTO, SocialType socialType){
//        User user = userJPARepository.findBySocialTypeAndSocialId(socialType,
//                attributesDTO.getOAuth2UserInfo().getSocialId()).orElse(null);
//
//        if(user == null){
//            return saveUser(attributesDTO, socialType);
//        }
//        return user;
//    }
//
//    private User saveUser(OAuthAttributesDTO attributesDTO, SocialType socialType){
//        User user = attributesDTO.toEntity(socialType, attributesDTO.getOAuth2UserInfo());
//        return userJPARepository.save(user);
//    }
//}
