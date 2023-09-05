//package com.fiveis.andcrowd.config.oauth;
//
//import com.fiveis.andcrowd.entity.user.User;
//import com.fiveis.andcrowd.enums.Role;
//import com.fiveis.andcrowd.enums.SocialType;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//import java.util.UUID;
//
//@Getter
//public class OAuthAttributesDTO {
//    private String nameAttributeKey;
//    private OAuth2UserInfo oAuth2UserInfo;
//
//    @Builder
//    public OAuthAttributesDTO(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo){
//        this.nameAttributeKey = nameAttributeKey;
//        this.oAuth2UserInfo = oAuth2UserInfo;
//    }
//
//    public static OAuthAttributesDTO of(SocialType socialType,
//                                        String userNameAttributeName,
//                                        Map<String, Object> attributes){
//        if(socialType == SocialType.NAVER){
//            return ofNaver(userNameAttributeName, attributes);
//        }
//        return null;
//    }
//
//    public static OAuthAttributesDTO ofNaver(String userNameAttributeName,
//                                            Map<String, Object> attributes){
//        return OAuthAttributesDTO.builder()
//                .nameAttributeKey(userNameAttributeName)
//                .oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
//                .build();
//    }
//
//    public User toEntity(SocialType socialType, OAuth2UserInfo oAuth2UserInfo){
//        return User.builder()
//                .socialType(socialType)
//                .socialId(oAuth2UserInfo.getSocialId())
//                .userEmail(UUID.randomUUID() + "@socialUser.com")
//                .userNickname(oAuth2UserInfo.getUserNickname())
//                .role(Role.USER)
//                .build();
//    }
//}
