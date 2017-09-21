package com.hardy.freeTest.mapper;

import com.hardy.freeTest.bean.ModuleRole;
import com.hardy.freeTest.bean.ModuleRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ModuleRoleMapper {
    long countByExample(ModuleRoleExample example);

    int deleteByExample(ModuleRoleExample example);

    int insert(ModuleRole record);

    int insertSelective(ModuleRole record);

    List<ModuleRole> selectByExample(ModuleRoleExample example);

    int updateByExampleSelective(@Param("record") ModuleRole record, @Param("example") ModuleRoleExample example);

    int updateByExample(@Param("record") ModuleRole record, @Param("example") ModuleRoleExample example);
}