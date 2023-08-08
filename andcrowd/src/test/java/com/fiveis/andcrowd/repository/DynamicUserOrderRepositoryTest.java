package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.dto.DynamicUserOrderDTO;
import com.fiveis.andcrowd.entity.DynamicUserOrder;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DynamicUserOrderRepositoryTest {
    @Autowired
    DynamicUserOrderRepository dynamicUserOrderRepository;

    String userEmail = "asdf@gmail.com";
    String tableName = "user_order_" + userEmail.replace('@', '_').replace('.', '_');

    @BeforeEach
    public void createDynamicUserOrderTableTest(){
        // Given
        // When
        dynamicUserOrderRepository.createDynamicUserOrderTable(tableName);

        // Then
    }

    @Test
    @Transactional
    @DisplayName("CR: 1번 주문 후 조회")
    public void saveTest(){
        // Given
        int uOrderId = 1;
        int orderId = 1;
        DynamicUserOrder dynamicUserOrder = DynamicUserOrder.builder()
                .uOrderId(uOrderId)
                .orderId(orderId)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserOrder", dynamicUserOrder
        );
        dynamicUserOrderRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uOrderId", uOrderId
        );
        DynamicUserOrderDTO.Find find = dynamicUserOrderRepository.findById(map1);

        // Then
        Assertions.assertEquals(uOrderId, find.getUOrderId());
    }

    @Test
    @Transactional
    @DisplayName("R: 모든 주문내역 조회")
    public void findAllTest(){
        // Given
        // When
        List<DynamicUserOrderDTO.Find> findList = dynamicUserOrderRepository.findAll(tableName);

        // Then
        Assertions.assertTrue(findList.isEmpty());
    }

    @Test
    @Transactional
    @DisplayName("CRD: 1번 주문 후 삭제 후 조회")
    public void deleteByIdTest(){
        // Given
        int uOrderId = 1;
        int orderId = 1;
        DynamicUserOrder dynamicUserOrder = DynamicUserOrder.builder()
                .uOrderId(uOrderId)
                .orderId(orderId)
                .build();

        // When
        Map<String, ?> map = Map.of(
                "tableName", tableName,
                "dynamicUserOrder", dynamicUserOrder
        );
        dynamicUserOrderRepository.save(map);

        Map<String, ?> map1 = Map.of(
                "tableName", tableName,
                "uOrderId", uOrderId
        );
        dynamicUserOrderRepository.deleteById(map1);

        // Then
        Assertions.assertNull(dynamicUserOrderRepository.findById(map1));
    }
}
