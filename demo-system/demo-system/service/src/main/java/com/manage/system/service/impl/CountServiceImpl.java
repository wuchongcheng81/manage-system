package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.manage.system.base.AbstractService;
import com.manage.system.dao.BrandVisitRecordMapper;
import com.manage.system.dao.CountMapper;
import com.manage.system.dto.CountDetailDTO;
import com.manage.system.dto.CountListDetailDTO;
import com.manage.system.service.BrandVisitRecordService;
import com.manage.system.service.CountService;
import com.manage.system.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wcc
 * @date 2019/11/17 13:14
 */
@Slf4j
@Component
@Transactional
public class CountServiceImpl extends AbstractService<CountDetailDTO, Integer, CountMapper> implements CountService {

    @Autowired
    private BrandVisitRecordService brandVisitRecordService;

    @Override
    public CountDetailDTO getCount(Integer brandId) {
        CountDetailDTO countDetailDTO = new CountDetailDTO();
        countDetailDTO.setCountBrowse(brandVisitRecordService.countBrowse(brandId));
        countDetailDTO.setCountIp(mapper.countIp(brandId));
        countDetailDTO.setCountPopular(mapper.countPopular(brandId));

        countDetailDTO.setCountYesBrowse(brandVisitRecordService.countYesterDayBrowse(brandId));
        countDetailDTO.setCountYesIp(mapper.countYesterDayIp(brandId));
        countDetailDTO.setCountYesPopular(mapper.countYesterDayPopular(brandId));
        return countDetailDTO;
    }

    @Override
    public CountListDetailDTO getCountList(int beforeDay, Integer brandId) {
        String beforeDayStr = DateUtils.getPastDate(beforeDay);

        CountListDetailDTO countListDetailDTO = new CountListDetailDTO();

        List<String> dayList = getBeforeDays(beforeDay);
        List<CountDetailDTO> browseDetailList = brandVisitRecordService.countBrowseByBeforeDay(beforeDayStr, brandId);
        List<CountDetailDTO> ipDetailList = mapper.countIpByBeforeDay(beforeDayStr, brandId);
        List<CountDetailDTO> popularDetailList = mapper.countPopularByBeforeDay(beforeDayStr, brandId);

        List<Integer> browseList = Lists.newArrayList();
        List<Integer> ipList = Lists.newArrayList();
        List<Integer> popularList = Lists.newArrayList();
        dayList.forEach(d -> {
            if(!CollectionUtils.isEmpty(browseDetailList))
                for (CountDetailDTO b : browseDetailList) {
                    if(d.equals(b.getCdate())) {
                        browseList.add(b.getCountNum());
                        browseDetailList.remove(b);
                    }else if(dayList.size() > browseDetailList.size()) {
                        browseList.add(0);
                    }
                    break;
                }
            if(!CollectionUtils.isEmpty(ipDetailList))
                for (CountDetailDTO i : ipDetailList) {
                    if(d.equals(i.getCdate())) {
                        ipList.add(i.getCountNum());
                        ipDetailList.remove(i);
                    }else if(dayList.size() > ipDetailList.size()) {
                        ipList.add(0);
                    }
                    break;
                }
            if(!CollectionUtils.isEmpty(popularDetailList))
                for (CountDetailDTO p : popularDetailList) {
                    if(d.equals(p.getCdate())) {
                        popularList.add(p.getCountNum());
                        popularDetailList.remove(p);
                    }else if(dayList.size() > popularDetailList.size()) {
                        popularList.add(0);
                    }
                    break;
                }
        });
        countListDetailDTO.setBrowseList(browseList);
        countListDetailDTO.setIpList(ipList);
        countListDetailDTO.setPopularList(popularList);
        countListDetailDTO.setBeforeDays(dayList);

        return countListDetailDTO;
    }

    @Override
    public IPage<CountDetailDTO> findPage(CountDetailDTO entity) {
        return null;
    }


    private List<String> getBeforeDays(int beforeDay) {
        List<String> list = Lists.newArrayList();
        for(int i = beforeDay; i >= 0; i--) {
            list.add(DateUtils.getPastDate(i));
        }
        return list;
    }
}
