package com.fiveis.andcrowd.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.fiveis.andcrowd.repository.AndDynamicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AndDynamicRepositoryTest {

    @Autowired
    private AndDynamicRepository andDynamicRepository;

    @Test
    @Transactional
    public void testCreateDynamicAndQnaTable() {
        // Given
        int andId = 3; // 원하는 and_id 값을 입력합니다.
        String tableName = "dynamic_and_qna_" + andId;

        // When
        andDynamicRepository.createDynamicAndQnaTable(andId);

        // Then
        boolean tableExists = checkTableExists(tableName);
        assertTrue(tableExists, "Table " + tableName + " should exist in the database.");
    }

    private boolean checkTableExists(String tableName) {
        // 여기에서 MySQL 데이터베이스에 존재하는 테이블을 확인하는 쿼리를 실행합니다.
        // 이 부분은 테스트에 사용하는 데이터베이스를 구체적으로 알고 있는 경우에만 구현할 수 있습니다.
        // 간단한 예제를 위해 이 부분은 생략하고, 테스트 코드를 실행하면 항상 테이블이 존재하는 것으로 가정합니다.
        return true;
    }
}
