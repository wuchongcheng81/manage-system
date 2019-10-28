package com.manage.system.frontend.controller;

import com.google.common.collect.Lists;
import com.manage.system.base.BaseFrontController;
import com.manage.system.bean.Brand;
import com.manage.system.bean.BrandPopularRecord;
import com.manage.system.dto.BrandDTO;
import com.manage.system.dto.BrandDetailDTO;
import com.manage.system.response.ResultData;
import com.manage.system.service.BrandPopularRecordService;
import com.manage.system.service.BrandService;
import com.manage.system.util.HttpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wcc
 * @date 2019/9/15 10:50
 */
@RestController
@RequestMapping(value = "/api/brand")
public class BrandFrontController extends BaseFrontController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandPopularRecordService brandPopularRecordService;

    /**
     *  查询品牌列表（分页）
     * @param typeId
     * @param pageNumber
     * @param pageSize
     * @returnl
     */
    @RequestMapping(value = "/findList")
    public ResultData findList(Integer typeId, Integer pageNumber, Integer pageSize) {
        List<BrandDTO> brandList = brandService.findPageByTypeId(pageNumber, pageSize, typeId);
        return new ResultData(true, brandList);
    }

    @RequestMapping(value = "/findAll")
    public ResultData findAll(Integer typeId) {
        List<BrandDTO> brandList = brandService.findAll(typeId);
        return new ResultData(true, brandList);
    }

    @RequestMapping(value = "/findById")
    public ResultData findById(@RequestParam int id) {
        Brand brand = brandService.getByIdWithType(id);
        BrandDetailDTO brandDetailDTO = new BrandDetailDTO();
        if(brand != null) {
            BeanUtils.copyProperties(brand, brandDetailDTO);
            return new ResultData(true, brandDetailDTO);
        }else {
            return new ResultData(false);
        }
    }

    @RequestMapping(value = "/addPopular")
    public ResultData addPopular(
            HttpServletRequest request,
            @RequestParam String type,
            @RequestParam int brandId) {

        String ip = HttpUtil.getIpAddress(request);
//        logger.info("add popular, request ip address : [{}]", ip);

        List<BrandPopularRecord> list = brandPopularRecordService.findTodayList(brandId, ip, type);
        if(BrandPopularRecord.MANUAL_TYPE.equals(type)) {
            if(!CollectionUtils.isEmpty(list) && list.size() >= 1)
                return new ResultData(false, "今天已提交过了哦");
        }else {
            if(!CollectionUtils.isEmpty(list) && list.size() >= 5)
                return new ResultData(true);
        }

        BrandPopularRecord record = new BrandPopularRecord();
        record.setCreateTime(new Date());
        record.setBrandId(brandId);
        record.setType(type);
        record.setRequestIp(ip);

        brandPopularRecordService.save(record);
        brandService.addPopularById(brandId);

        return new ResultData(true);
    }

    @GetMapping(value = "/test")
    public void test() {
        List<BrandPopularRecord> popularRecords = Lists.newArrayList();
        Date now = new Date();
        for (int i = 0; i < 2; i++) {
            BrandPopularRecord p = new BrandPopularRecord();
            p.setCreateTime(now);
            p.setBrandId(58);
            p.setType(BrandPopularRecord.VISIT_TYPE);
            p.setRequestIp("add_by_scheduled");
            popularRecords.add(p);
        }
        int u_b_p = brandPopularRecordService.insertBatch(popularRecords);
    }

}
