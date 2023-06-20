package com.base.basic.app.service.impl;

import com.base.basic.app.service.InterfaceService;
import com.base.basic.domain.entity.v1.Interface;
import com.base.basic.domain.entity.v1.InterfaceLog;
import com.base.basic.domain.entity.v1.InterfaceParams;
import com.base.basic.infra.constant.InterfaceConstants;
import com.base.basic.infra.mapper.InterfaceLogMapper;
import com.base.basic.infra.mapper.InterfaceMapper;
import com.base.basic.infra.mapper.InterfaceParamsMapper;
import com.base.common.util.http.RestfulUtil;
import com.base.common.util.http.SoapUtil;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
            logger.error("接口调用异常 {}", e);
        }finally {
            InterfaceLog interfaceLog = new InterfaceLog(
                    interfaceDTO.getInterfaceCode(),
                    InterfaceConstants.LOCAL_HOST,
                    InterfaceConstants.method.POST,
                    content,
                    interfaceDTO.getUrl(),
                    Long.valueOf(Objects.isNull(response) || Objects.isNull(response.code()) ? -1 : response.code()),
                    responseBody
            );
            interfaceLogMapper.insertSelective(interfaceLog);
        }
        return responseBody;
    }

    @Override
    public String sendRestful(String interfaceCode,
                              String method,
                              Map<String, ?> uriVariables,
                              Map<String, String> headerVariables,
                              String content){
        Interface interfaceDTO = new Interface();
        interfaceDTO.setInterfaceCode(interfaceCode);
        interfaceDTO = interfaceMapper.selectOne(interfaceDTO);

        String response = "";
        try {
            switch (method) {
                case RestfulUtil.GET_METHOD:
                    response = RestfulUtil.httpGet(interfaceDTO.getUrl(), uriVariables, headerVariables, RestfulUtil.UTF8_CHARSET).toString();
                    break;
                case RestfulUtil.POST_METHOD:
                    response = RestfulUtil.httpPost(interfaceDTO.getUrl(), uriVariables, headerVariables, content, RestfulUtil.UTF8_CHARSET).toString();
                    break;
                case RestfulUtil.PUT_METHOD:
                    response = RestfulUtil.httpPut(interfaceDTO.getUrl(), uriVariables, headerVariables, content, RestfulUtil.UTF8_CHARSET).toString();
                    break;
                default:
                    logger.error("请求方式异常");
            }
        }catch (Exception e){
            logger.error("接口调用异常 {}", e);
        }finally {
            InterfaceLog interfaceLog = new InterfaceLog(
                    interfaceDTO.getInterfaceCode(),
                    InterfaceConstants.LOCAL_HOST,
                    InterfaceConstants.method.POST,
                    content,
                    interfaceDTO.getUrl(),
                    -1L,
                    response
            );
            interfaceLogMapper.insertSelective(interfaceLog);
        }
        return response;
    }
}
