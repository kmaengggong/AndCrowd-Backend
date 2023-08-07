package com.fiveis.andcrowd.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AndDynamicRepository {
    void createDynamicAndQnaTable(int andId);
}
