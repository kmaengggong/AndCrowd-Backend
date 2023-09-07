//package com.fiveis.andcrowd.controller.etc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fiveis.andcrowd.service.etc.AdPaymentService;
//import com.fiveis.andcrowd.service.etc.CancelPaymentService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/payments/cancel")
//@CrossOrigin(origins = "http://localhost:3000")
//public class CancelPaymentController {
//
//    private final CancelPaymentService cancelPaymentService;
//
//    public CancelPaymentController(CancelPaymentService cancelPaymentService) {
//        this.cancelPaymentService = cancelPaymentService;
//    }
//
//    @RequestMapping(value = "/{paymentType}/{paymentId}", method = RequestMethod.POST)
//    public ResponseEntity<String> cancelPayment(@PathVariable String paymentType,
//                                                @PathVariable int paymentId,
//                                                @RequestBody Map<String, Object> requestBody) {
//        try {
//            if(paymentType.equals("ad")) {
//                String response = cancelPaymentService.cancelPayment((String) requestBody.get("merchant_uid"), requestBody, paymentType, paymentId);
//                return ResponseEntity.ok(response);
//            } else {
//                return ResponseEntity.badRequest().body("Invalid payment type");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//}
