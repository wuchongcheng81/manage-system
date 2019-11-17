package com.manage.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import com.manage.system.base.AbstractService;
import com.manage.system.dao.BrandShareRecordMapper;
import com.manage.system.dao.CountMapper;
import com.manage.system.dto.CountDetailDTO;
import com.manage.system.dto.CountListDetailDTO;
import com.manage.system.service.BrandShareRecordService;
import com.manage.system.service.CountService;
import com.manage.system.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
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

    @Resource
    private BrandShareRecordService recordService;

    @Override
    public CountDetailDTO getCount(Integer brandId) {
        CountDetailDTO countDetailDTO = new CountDetailDTO();
        countDetailDTO.setCountBrowse(mapper.countBrowse(brandId));
        countDetailDTO.setCountIp(mapper.countIp(brandId));
        countDetailDTO.setCountPopular(mapper.countPopular(brandId));
        countDetailDTO.setCountShare(recordService.countShare(brandId));

        countDetailDTO.setCountYesBrowse(mapper.countYesterDayBrowse(brandId));
        countDetailDTO.setCountYesIp(mapper.countYesterDayIp(brandId));
        countDetailDTO.setCountYesPopular(mapper.countYesterDayPopular(brandId));
        countDetailDTO.setCountYesShare(recordService.countYesterDayShare(brandId));
        return countDetailDTO;
    }

    @Override
    public CountListDetailDTO getCountList(int beforeDay, Integer brandId) {
        String beforeDayStr = DateUtils.getPastDate(beforeDay);

        CountListDetailDTO countListDetailDTO = new CountListDetailDTO();

        List<String> dayList = getBeforeDays(beforeDay);
        List<CountDetailDTO> browseDetailList = mapper.countBrowseByBeforeDay(beforeDayStr, brandId);
        List<CountDetailDTO> ipDetailList = mapper.countIpByBeforeDay(beforeDayStr, brandId);
        List<CountDetailDTO> popularDetailList = mapper.countPopularByBeforeDay(beforeDayStr, brandId);
        List<CountDetailDTO> shareDetailList = recordService.countShareByBeforeDay(beforeDayStr, brandId);

        List<Integer> browseList = Lists.newArrayList();
        List<Integer> ipList = Lists.newArrayList();
        List<Integer> popularList = Lists.newArrayList();
        List<Integer> shareList = Lists.newArrayList();
        dayList.forEach(d -> {
            if(!CollectionUtils.isEmpty(browseDetailList))
                browseDetailList.forEach( b-> {
                    if(d.equals(b.getCdate())) {
                        browseList.add(b.getCountNum());
                    }else if(dayList.size() > browseDetailList.size()) {
                        browseList.add(0);
                    }
                });
            if(!CollectionUtils.isEmpty(ipDetailList))
                ipDetailList.forEach(i -> {
                    if(d.equals(i.getCdate())) {
                        ipList.add(i.getCountNum());
                    }else if(dayList.size() > ipDetailList.size()) {
                        ipList.add(0);
                    }
                });
            if(!CollectionUtils.isEmpty(popularDetailList))
                popularDetailList.forEach(p -> {
                    if(d.equals(p.getCdate())) {
                        popularList.add(p.getCountNum());
                    }else if(dayList.size() > popularDetailList.size()) {
                        popularList.add(0);
                    }
                });
            if(!CollectionUtils.isEmpty(shareDetailList))
                shareDetailList.forEach(s -> {
                    if(d.equals(s.getCdate())) {
                        shareList.add(s.getCountNum());
                    }else if(dayList.size() > shareDetailList.size()) {
                        shareList.add(0);
                    }
                });
        });
        countListDetailDTO.setBrowseList(browseList);
        countListDetailDTO.setIpList(ipList);
        countListDetailDTO.setPopularList(popularList);
        countListDetailDTO.setShareList(shareList);
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
