package com.fiveis.andcrowd.service_;

import com.fiveis.andcrowd.dto.DynamicAndQnaReplyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DynamicAndQnaReplyService {
    List<DynamicAndQnaReplyDTO.FindById> findAll(int andId);
    List<DynamicAndQnaReplyDTO.FindById> findAllByAndQnaId(int andId, int andQnaId);
    DynamicAndQnaReplyDTO.FindById findByAndReplyId(int andId, int andReplyId);
    void save(DynamicAndQnaReplyDTO.Update andReplySaveDTO);
    void update(DynamicAndQnaReplyDTO.Update andReplyUpdateDTO);
    void deleteByAndReplyId(int andId, int andReplyId);
    void deleteByAndQnaId(int andId, int andQnaId);

}
