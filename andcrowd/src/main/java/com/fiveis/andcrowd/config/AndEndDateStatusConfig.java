package com.fiveis.andcrowd.config;

import com.fiveis.andcrowd.service.and.AndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class AndEndDateStatusConfig {

    @Autowired
    private AndService andService;

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void updateStatusForExpiredItems() {
        andService.updateStatusForExpiredItems();
    }
}
