package com.manage.system.frontend.controller;

import com.manage.system.bean.InformationVisitRecord;
import com.manage.system.dto.InformationFrontDTO;
import com.manage.system.response.ResultData;
import com.manage.system.service.InformationService;
import com.manage.system.service.InformationVisitRecordService;
import com.manage.system.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wcc
 * @date 2019/9/15 9:49
 */
@RestController
@RequestMapping(value = "/api/information")
public class InformationFrontController {

    @Autowired
    private InformationService informationService;
    @Autowired
    private InformationVisitRecordService informationVisitRecordService;

    /**
     * 查询资讯列表（分页）
     * @param code
     * @param typeId
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findList")
    public ResultData findList(String code, Integer typeId, Integer brandId, Integer pageNumber, Integer pageSize) {
        List<InformationFrontDTO> list = informationService.findListByColumnCode(code, typeId, brandId, pageNumber, pageSize);
        return new ResultData(true, list);
    }

    @RequestMapping(value = "/findById")
    public ResultData findById(@RequestParam int informationId) {
        return new ResultData(true, informationService.findById(informationId));
    }

    @RequestMapping(value = "/addClickNum")
    public ResultData addClickNum(HttpServletRequest request, @RequestParam int informationId) {
        String ip = HttpUtil.getIpAddr(request);

        List<InformationVisitRecord> list = informationVisitRecordService.findCurrentDay(ip, informationId);
        if(CollectionUtils.isEmpty(list)) {
            InformationVisitRecord record = new InformationVisitRecord();
            record.setRequestIp(ip);
            record.setCreateTime(new Date());
            record.setInformationId(informationId);
            informationVisitRecordService.save(record);
            informationService.addClickNum(informationId);
        }
        return new ResultData(true);
    }

    @RequestMapping(value = "/recDay")
    public ResultData recDay(Integer typeId,
                             Integer brandId, Integer searchCount) {
        Calendar c = Calendar.getInstance();
        Date today = new Date();
        c.setTime(today);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 7);
        Date last7 = c.getTime();
        List<InformationFrontDTO> list = informationService.findLastWeek(last7, typeId, brandId, searchCount);
        return new ResultData(true, list);
    }

}
