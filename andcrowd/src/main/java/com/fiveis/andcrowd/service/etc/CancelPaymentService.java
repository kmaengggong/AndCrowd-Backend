package com.fiveis.andcrowd.service.etc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiveis.andcrowd.repository.crowd.CrowdOrderDetailsJPARepository;
import com.fiveis.andcrowd.repository.etc.AdPaymentJPARepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CancelPaymentService {

    private AdPaymentJPARepository adPaymentJPARepository;
    private CrowdOrderDetailsJPARepository crowdOrderDetailsJPARepository;

    private static final String API_HOST = "https://api.iamport.kr";
    private static final String TOKEN_URL = API_HOST + "/users/getToken";
    private static final String CANCEL_PAYMENT_URL = API_HOST + "/payments/cancel";

    // application.yml에 정의된 키값과 시크릿값을 가져옴
    @Value("${import.key}")
    private String impKey;

    @Value("${import.secret}")
    private String impSecret;

    // 아임포트에서 엑세스 토큰을 얻어오는 기능
    public String getAccessToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("charset", "UTF-8");
        headers.set("Accept", "*/*");

        ObjectMapper mapper = new ObjectMapper();
        String body = null;
        try {
            body = mapper.writeValueAsString(Map.of("imp_key", impKey, "imp_secret", impSecret));
        } catch (Exception e) {
            throw new RuntimeException("JSON으로 변환에 실패했습니다.", e);
        }

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(TOKEN_URL, body, String.class);

           return response;
    }

    // 얻어온 토큰과 프론트에서 보내준 정보를 토대로 아임포트에 취소요청을 넣는 기능
    public String cancelPayment(String merchant_uid, Map<String, Object> requestBody, String paymentType, int paymentId) throws Exception {
        String accessToken = getAccessToken();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(Map.of(
                "reason", requestBody.get("reason"),
                "imp_uid", merchant_uid,
                "amount", requestBody.get("cancel_request_amount"),
                "checksum", requestBody.get("checksum")
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", accessToken);

        RestTemplate restTemplate = new RestTemplate();
        if(paymentType.equals("ad")){
            adPaymentJPARepository.setAdPaymentStatusByAdId(paymentId);
        }else if (paymentType.equals("reward")){
            crowdOrderDetailsJPARepository.setRefundStatusForOrder(paymentId);
        }
        return restTemplate.postForObject(CANCEL_PAYMENT_URL, jsonString, String.class, headers);
    }
}
