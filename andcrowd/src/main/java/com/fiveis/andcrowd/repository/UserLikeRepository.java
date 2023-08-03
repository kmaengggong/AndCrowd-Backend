package com.fiveis.andcrowd.repository;

import com.fiveis.andcrowd.entity.Crowd;
import com.fiveis.andcrowd.entity.DynamicUserLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserLikeRepository {
    List<Crowd> findAll();
    Crowd findById(int uLikeId);
    void save(DynamicUserLike userLike);
    void deleteById(int uLikeId);
}
