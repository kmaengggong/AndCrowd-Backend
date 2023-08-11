package com.fiveis.andcrowd.dto;
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
    public static class FindByCategoryId {
        private int andCategoryId;
        private String andCategoryName;
        private boolean isDeleted;
    }

}
