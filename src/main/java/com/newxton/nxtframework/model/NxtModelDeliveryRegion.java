package com.newxton.nxtframework.model;

import com.alibaba.fastjson.JSONObject;
import com.newxton.nxtframework.entity.NxtDeliveryRegion;
import com.newxton.nxtframework.service.NxtDeliveryRegionService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author soyojo.earth@gmail.com
 * @address Shenzhen, China
 * @copyright NxtFramework
 */
@Component
public class NxtModelDeliveryRegion {

    @Resource
    private NxtDeliveryRegionService nxtDeliveryRegionService;

    public Map<String, Object> save(@RequestBody JSONObject jsonParam) {

        Long id = jsonParam.getLong("id");
        String regionName = jsonParam.getString("regionName");
        Long regionPid = jsonParam.getLong("regionPid");
        Long sortId = jsonParam.getLong("sortId");

        Map<String, Object> result = new HashMap<>();
        result.put("status", 0);
        result.put("message", "");

        NxtDeliveryRegion nxtDeliveryRegion;

        if (id == null){
            nxtDeliveryRegion = new NxtDeliveryRegion();
        }
        else {
            nxtDeliveryRegion = nxtDeliveryRegionService.queryById(id);
        }

        if (nxtDeliveryRegion == null){
            result.put("status", 49);
            result.put("message", "找不到对应的内容");
            return result;
        }

        nxtDeliveryRegion.setRegionName(regionName);
        nxtDeliveryRegion.setRegionPid(regionPid);
        nxtDeliveryRegion.setSortId(sortId);

        if (id == null){
            nxtDeliveryRegionService.insert(nxtDeliveryRegion);
        }
        else {
            nxtDeliveryRegionService.update(nxtDeliveryRegion);
        }

        result.put("detail",nxtDeliveryRegion);

        return result;

    }
}