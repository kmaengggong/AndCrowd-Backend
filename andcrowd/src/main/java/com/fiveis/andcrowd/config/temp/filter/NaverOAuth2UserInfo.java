//package com.fiveis.andcrowd.config.oauth;
//
//import java.util.Map;
//
//public class NaverOAuth2UserInfo extends OAuth2UserInfo{
//    public NaverOAuth2UserInfo(Map<String, Object> attributes){
//        super(attributes);
//    }
//
//    @Override
//    public String getSocialId(){
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//        if(response == null) return null;
//        return (String) response.get("socialId");
//    }
//
//    @Override
//    public String getUserNickname(){
//        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
//        if(response == null) return null;
//        return (String) response.get("userNickname");
//    }
//}
