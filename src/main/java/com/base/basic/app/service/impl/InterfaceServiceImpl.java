package com.base.basic.app.service.impl;

import com.base.basic.app.service.InterfaceService;
import com.base.basic.domain.entity.v1.Interface;
import com.base.basic.domain.entity.v1.InterfaceLog;
import com.base.basic.domain.entity.v1.InterfaceParams;
import com.base.basic.infra.constant.InterfaceConstants;
import com.base.basic.infra.mapper.InterfaceLogMapper;
import com.base.basic.infra.mapper.InterfaceMapper;
import com.base.basic.infra.mapper.InterfaceParamsMapper;
import com.base.common.util.http.SoapUtil;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InterfaceServiceImpl implements InterfaceService {
    Logger logger = LoggerFactory.getLogger(InterfaceServiceImpl.class);

    @Autowired
    @SuppressWarnings("all")
    private InterfaceMapper interfaceMapper;
    @Autowired
    @SuppressWarnings("all")
    private InterfaceParamsMapper interfaceParamsMapper;
    @Autowired
    @SuppressWarnings("all")
    private InterfaceLogMapper interfaceLogMapper;

    @Override
    public String sendSoap(String interfaceCode, String content){
        /**
         * 查接口信息
         */
        Interface interfaceDTO = new Interface();
        interfaceDTO.setInterfaceCode(interfaceCode);
        interfaceDTO = interfaceMapper.selectOne(interfaceDTO);
        InterfaceParams interfaceParamsDTO = new InterfaceParams();
        interfaceParamsDTO.setInterfaceId(interfaceDTO.getId());
        List<InterfaceParams> interfaceParamsList = interfaceParamsMapper.select(interfaceParamsDTO);
        Map<String, List<InterfaceParams>> interfaceParamsMap = interfaceParamsList.stream().collect(Collectors.groupingBy(InterfaceParams::getParamKey));

        Response response = null;
        String responseBody = "";
        try {
            response = SoapUtil.send(interfaceDTO.getUrl(), interfaceParamsMap.get("Username").get(0).getParamValue(), interfaceParamsMap.get("Password").get(0).getParamValue(), content);
            responseBody = response.body().string();
        }catch (Exception e){
            logger.error(e.toString());
        }finally {
            InterfaceLog interfaceLog = new InterfaceLog(
                    interfaceDTO.getInterfaceCode(),
                    InterfaceConstants.LOCAL_HOST,
                    InterfaceConstants.method.POST,
                    content,
                    interfaceDTO.getUrl(),
                    Long.valueOf(response.code()),
                    responseBody
            );
            interfaceLogMapper.insertSelective(interfaceLog);
        }
        return responseBody;
    }
}
