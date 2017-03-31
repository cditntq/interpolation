package com.ntq.baseMgr.dao;


import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Title: baseMapper
 * @Description: 基本baseMapper, 构造基本的mapper，增删查改
 * @Company:
 * @Author 杨爽【247677857yh@gmail.com】
 * @Date: 2016/3/29
 * @Time: 21:07
 */
public interface BaseMapper<T, ID extends Serializable> {


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
    int insertSelective(T record);

    /**
     * 根据id删除一条数据
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(ID id);

    /**
     * 根据id集合批量删除数据
     *
     * @param ids
     * @return
     */
    int deleteBatch(List<ID> ids);

    /**
     * 根据id查询一条记录
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(ID id);

    /**
     * 根据主键进行更新
     * 只会更新不是null的数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);


    /**************************************扩展的方法后面继续添加*********************************************/
    /**
     * 查询所有记录
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据参数查询记录
     *
     * @param params
     * @return
     * @throws Exception
     */
    List<T> selectByPro(@Param("params") Map<String, Object> params);


    /**
     * 查询总记录条数
     *
     * @return
     * @throws Exception
     */
    int totalCount() throws Exception;

    /**
     * 分页查询
     *
     * @param start          起始页码
     * @param end            结束页码
     * @param whereCondition 中间查询条件
     * @return
     */
    List<T> selectByCondition(@Param(value = "start") int start, @Param(value = "end") int end, @Param(value = "whereCondition") String whereCondition);



    List<T> selectEntiesByCondition(@Param(value = "startPos") int startPos, @Param(value = "pageSize") int pageSize);


}
