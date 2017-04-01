package com.ntq.baseMgr.service.impl;



import com.ntq.baseMgr.mapper.BaseMapper;
import com.ntq.baseMgr.service.BaseService;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title:
 * @Description:
 * @Company:
 * @Author 杨爽【247677857yh@gmail.com】
 * @Date: 2016/4/1
 * @Time: 11:03
 */


public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
    //注入的mapper
    private BaseMapper<T, ID> baseMapper;

    //获取当前的mapper对象
    public void setBaseMapper(BaseMapper<T, ID> baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 插入一条数据
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record
     * @return
     */
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    /**
     * 插入一条数据并返回主键
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record
     * @return
     */
    public ID insertAndReturnKey(T record) {
        return baseMapper.insertAndGetKey(record);
    }

    /**
     * 插入一条数据,只插入不为null的字段,不会影响有默认值的字段
     * 支持Oracle序列,UUID,类似Mysql的INDENTITY自动增长(自动回写)
     * 优先使用传入的参数值,参数值空时,才会使用序列、UUID,自动增长
     *
     * @param record
     * @return
     */

    public int insertSelective(T record) throws Exception {
        return baseMapper.insertSelective(record);
    }

    /**
     * 根据id删除一条数据
     *
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(ID id) throws Exception {
        return baseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id集合批量删除数据
     *
     * @param ids
     * @return
     */
    public int deleteBatch(String ids) {
        List<Long> idList = new ArrayList<>();
        String[] tempIDs = ids.split(",");
        for (String id : tempIDs) {
            idList.add(Long.valueOf(id));
        }
        return baseMapper.deleteBatch((List<ID>) idList);
    }

    /**
     * 根据id查询一条数据
     *
     * @param id
     * @return
     */

    public T selectByPrimaryKey(ID id) throws Exception {
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键进行更新
     * 只会更新不是null的数据
     *
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(T record) throws Exception {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(T record) throws Exception {
        return baseMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    /**
     * 更新记录
     *
     * @param record
     * @return
     * @throws Exception
     */
    public int updateByPrimaryKey(T record) throws Exception {
        return baseMapper.updateByPrimaryKey(record);
    }


    /********************************扩展方法****************************************/
    /**
     * 查询总记录条数
     *
     * @return
     * @throws Exception
     */
    public int totalCount() throws Exception {
        return baseMapper.totalCount();
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<T> selectAll() throws Exception {
        return baseMapper.selectAll();
    }

    /**
     * 根据参数查询记录
     *
     * @param params
     * @return
     * @throws Exception
     */
    public List<T> selectByPro(@Param("params") Map<String, Object> params) throws Exception {
        return baseMapper.selectByPro(params);
    }

    /**
     * 分页查询数据
     *
     * @param page           当前页码
     * @param size           每页条数
     * @param whereCondition 查询条件
     * @return
     * @throws Exception
     */
    public List<T> selectByCondition(int page, int size, String whereCondition) throws Exception {

        //return baseMapper.selectEntiesByCondition((page - 1) * size, page * size);
      return baseMapper.selectByCondition((page - 1) * size, page * size, whereCondition);
    }
}
