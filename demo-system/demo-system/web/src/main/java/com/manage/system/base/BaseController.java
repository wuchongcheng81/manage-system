package com.manage.system.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manage.system.response.ResultData;
import com.manage.system.response.ResultPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

public abstract class BaseController<MAPPER extends BaseService<T, ID>, T, ID extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MAPPER mapper;

    public BaseController() {}

    @GetMapping(value = "/get")
    public ResultData<T> get(ID id) {
        T data =  mapper.findById(id);
        if(data != null)
            return new ResultData<>(true, data);
        return new ResultData<>(false);
    }

    @PostMapping(value = "/save")
    public ResultData<T> save(T t) {
        int result = mapper.save(t);
        if(result > 0)
            return new ResultData<>(true);
        return new ResultData<>(false);
    }

    @PostMapping(value = "/update")
    public ResultData<T> update(T t) {
        int result = mapper.update(t);
        if(result > 0)
            return new ResultData<>(true);
        return new ResultData<>(false);
    }

    @PostMapping(value = "/delete")
    public ResultData<T> delete(ID id) {
        int result = mapper.delete(id);
        if(result > 0)
            return new ResultData<>(true);
        return new ResultData<>(false);
    }

    @PostMapping(value = "/deleteByIds")
    public ResultData<T> deleteByIds(String ids) {
        ids = ids.replace("\"", "");
        int result = mapper.deleteByIds(ids.split(","));
        if(result > 0)
            return new ResultData<>(true);
        return new ResultData<>(false);
    }

    @RequestMapping(value = "/findPage")
    public ResultPage<T> findPage(T t) {
        IPage<T> result = mapper.findPage(t);
        if(result != null)
            return new ResultPage<>(result.getTotal(), result.getRecords());
        return new ResultPage<>();
    }
}
