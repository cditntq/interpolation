package com.ntq.baseMgr.service;


import com.ntq.baseMgr.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Title: BaseService
 * @Description: 基础service
 * @Company:
 * @Author 杨爽【247677857yh@gmail.com】
 * @Date: 2016/4/1
 * @Time: 11:02
 */
public interface BaseService<T, ID extends Serializable> {
    void setBaseMapper(BaseMapper<T, ID> baseMapper);

    /**
     * 插入一条数据
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 插入并返回主键
     * @param record
     * @return
     */
    ID insertAndReturnKey(T record);
    /**
     * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record
     * @return
     */
    int insertSelective(T record) throws Exception;

    /**
     * 根据id删除一条数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(ID id) throws Exception;


    /**
     * 根据id集合批量删除数据
     *
     * @param ids
     * @return
     */
    int deleteBatch(String ids);

    /**
     * 根据id查询一条记录
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(ID id) throws Exception;

    /**
     * 根据主键进行更新
     * 只会更新不是null的数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record) throws Exception;

    int updateByPrimaryKeyWithBLOBs(T record) throws Exception;

    int updateByPrimaryKey(T record) throws Exception;


}