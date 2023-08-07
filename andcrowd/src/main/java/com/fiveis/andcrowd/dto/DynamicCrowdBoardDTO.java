package com.fiveis.andcrowd.dto;

import com.fiveis.andcrowd.entity.DynamicCrowdBoard;
import lombok.*;

import java.time.LocalDateTime;

public class DynamicCrowdBoardDTO {

    @Getter @Setter @ToString @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Find {
        private int crowdId;

        private int crowdBoardId;

        private int crowdBoardTag;

        private String crowdBoardTitle;

        private String crowdBoardContent;

        private String crowdImg;

        public Find(DynamicCrowdBoard dynamicCrowdBoard) {
            this.crowdId = dynamicCrowdBoard.getCrowdId();
            this.crowdBoardId = dynamicCrowdBoard.getCrowdBoardId();
            this.crowdBoardTag = dynamicCrowdBoard.getCrowdBoardTag();
            this.crowdBoardTitle = dynamicCrowdBoard.getCrowdBoardTitle();
            this.crowdBoardContent = dynamicCrowdBoard.getCrowdBoardContent();
            this.crowdImg = dynamicCrowdBoard.getCrowdImg();
        }
    }

    @Getter @Setter @ToString @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Save {

        private int crowdId;

        private int crowdBoardTag;

        private String crowdBoardTitle;

        private String crowdBoardContent;

        private String crowdImg;

        public Save(DynamicCrowdBoard dynamicCrowdBoard){
            this.crowdId = dynamicCrowdBoard.getCrowdId();
            this.crowdBoardTag = dynamicCrowdBoard.getCrowdBoardTag();
            this.crowdBoardTitle = dynamicCrowdBoard.getCrowdBoardTitle();
            this.crowdBoardContent = dynamicCrowdBoard.getCrowdBoardContent();
            this.crowdImg = dynamicCrowdBoard.getCrowdImg();
        }
    }

    @Getter @Setter @ToString @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private int crowdId;

        private int crowdBoardId;

        private int crowdBoardTag;

        private String crowdBoardTitle;

        private String crowdBoardContent;

        private String crowdImg;

        public Update(DynamicCrowdBoard dynamicCrowdBoard){
            this.crowdBoardId = dynamicCrowdBoard.getCrowdBoardId();
            this.crowdBoardTag = dynamicCrowdBoard.getCrowdBoardTag();
            this.crowdBoardTitle = dynamicCrowdBoard.getCrowdBoardTitle();
            this.crowdBoardContent = dynamicCrowdBoard.getCrowdBoardContent();
            this.crowdImg = dynamicCrowdBoard.getCrowdImg();
        }
    }

}
