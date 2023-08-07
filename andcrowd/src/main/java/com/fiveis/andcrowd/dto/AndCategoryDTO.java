package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.AndCategory;
import lombok.*;

public class AndCategoryDTO {
    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class Update {
        private int andCategoryId;
        private String andCategoryName;
        private boolean isDeleted;
    }

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class FindById {
        private int andCategoryId;
        private String andCategoryName;
        private boolean isDeleted;
    }

}
