//package com.manage.system.base;
//
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.Serializable;
//import java.util.Arrays;
//
///**
// * @author wucc
// * @date 2019/8/30 17:09
// */
//public abstract class BaseServiceImpl<MAPPER extends BaseMapper<T>, T, ID extends Serializable> extends AbstractService<T, MAPPER> implements BaseService<T, ID> {
//
//    protected MAPPER mapper;
//
//    public BaseServiceImpl() {}
//
//    public int save(T entity) {
//        return this.mapper.insert(entity);
//    }
//
//    public int update(T entity) {
//        return this.mapper.updateById(entity);
//    }
//
//    public int deleteByIds(ID[] ids) {
//        return this.mapper.deleteBatchIds(Arrays.asList(ids));
//    }
//
//    @Transactional(readOnly = true)
//    public T findById(ID id) {
//        return this.mapper.selectById(id);
//    }
//}
