package com.manage.system.backend.schedule;

import com.google.common.collect.Lists;
import com.manage.system.bean.BrandPopularRecord;
import com.manage.system.dto.BrandDTO;
import com.manage.system.service.BrandPopularRecordService;
import com.manage.system.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wcc
 * @date 2019/10/28 21:32
 */
@Component
@EnableScheduling
public class BrandPopularSchedule {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BrandService brandService;
    @Autowired
    private BrandPopularRecordService brandPopularRecordService;

    @Scheduled(cron = "0 0 0 * * ?")
    private void test() {
        logger.info("------------开始执行增加热度定时器------------");
        List<BrandDTO> brandList = brandService.findAll(null);
        logger.info("size : " + brandList.size());
        brandList.forEach(b -> {
            int u_b = brandService.addPopularByIdAndPopular(13, b.getId());
            List<BrandPopularRecord> popularRecords = Lists.newArrayList();
            Date now = new Date();
            for (int i = 0; i < 13; i++) {
                BrandPopularRecord p = new BrandPopularRecord();
                p.setCreateTime(now);
                p.setBrandId(b.getId());
                p.setType(BrandPopularRecord.VISIT_TYPE);
                p.setRequestIp("add_by_scheduled");
                popularRecords.add(p);
            }
            int u_b_p = brandPopularRecordService.insertBatch(popularRecords);
        });
        logger.info("------------结束执行增加热度定时器------------");
    }
}
