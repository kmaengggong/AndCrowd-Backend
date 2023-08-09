package com.fiveis.andcrowd.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AndDynamicRepository {
    void createDynamicAndQnaTable(int andId);
    void createDynamicAndQnaReplyTable(int andId);
    void createDynamicAndRoleTable(int andId);
    void createDynamicAndApplicantTable(int andId);
}
