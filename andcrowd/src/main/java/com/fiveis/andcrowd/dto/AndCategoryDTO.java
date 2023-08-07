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
        public Update (AndCategory andCategory){
            this.andCategoryId = andCategory.getAndCategoryId();
            this.andCategoryName = andCategory.getAndCategoryName();
            this.isDeleted = andCategory.isDeleted();
        }
    }

    @Getter @Setter
    @Builder @ToString
    @AllArgsConstructor @NoArgsConstructor
    public static class findById {
        private int andCategoryId;
        private String andCategoryName;
        private boolean isDeleted;

        public findById (AndCategory andCategory){
            this.andCategoryId = andCategory.getAndCategoryId();
            this.andCategoryName = andCategory.getAndCategoryName();
            this.isDeleted = andCategory.isDeleted();
        }
    }



}
