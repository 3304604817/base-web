package com.base.basic.app.service.impl;

import com.base.basic.app.service.InterfaceService;
import com.base.basic.domain.entity.v1.*;
import com.base.basic.infra.constant.BaseConstants;
import com.base.basic.infra.constant.InterfaceConstants;
import com.base.basic.infra.mapper.InterfaceLogMapper;
import com.base.basic.infra.mapper.InterfaceMapper;
import com.base.basic.infra.mapper.InterfaceParamsMapper;
import com.base.common.exception.BaseException;
import com.base.common.util.http.RestfulResponse;
import com.base.common.util.http.RestfulUtil;
import com.base.common.util.http.SoapUtil;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo<Interface> pageList(PageParmaters pageParmaters, Interface searchBody){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(()->interfaceMapper.list(searchBody));
    }

    @Override
    public Interface interfaceDetail(Long interfaceId){
        Interface interfaceData = interfaceMapper.selectByPrimaryKey(interfaceId);
        interfaceData.setInterfaceParams(
                interfaceParamsMapper.list(new InterfaceParams(interfaceData.getId()))
        );
        return interfaceData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Interface save(Interface body){
        if(Objects.nonNull(body.getId())){
            interfaceMapper.updateByIdSelective(body);
        }else {
            interfaceMapper.insertSelective(body);
        }
        return body;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean enable(Long id){
        Interface dbInterface = new Interface();
        dbInterface.setId(id);
        dbInterface.setEnabledFlag(Boolean.TRUE);
        interfaceMapper.updateOptional(dbInterface,
                Menu.FIELD_ENABLED_FLAG);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disabled(Long id){
        Interface dbInterface = new Interface();
        dbInterface.setId(id);
        dbInterface.setEnabledFlag(Boolean.FALSE);
        interfaceMapper.updateOptional(dbInterface,
                Menu.FIELD_ENABLED_FLAG);
        return Boolean.TRUE;
    }

    @Override
    public String sendSoap(String interfaceCode, String content){
        return this.processSoap(interfaceCode, content, null, null, null);
    }

    @Override
    public String sendSoap(String interfaceCode, String content, Long readTimeout, Long writeTimeout, Long connectTimeout){
        return this.processSoap(interfaceCode, content, readTimeout, writeTimeout, connectTimeout);
    }

    @Override
    public RestfulResponse sendRestful(String interfaceCode,
                              String method,
                              Map<String, ?> uriVariables,
                              Map<String, String> headerVariables,
                              String content){
        Interface interfaceDTO = new Interface();
        interfaceDTO.setInterfaceCode(interfaceCode);
        interfaceDTO = interfaceMapper.selectOne(interfaceDTO);

        RestfulResponse response = new RestfulResponse();
        try {
            switch (method) {
                case RestfulUtil.GET_METHOD:
                    response = RestfulUtil.httpGet(interfaceDTO.getUrl(), uriVariables, headerVariables, RestfulUtil.UTF8_CHARSET);
                    break;
                case RestfulUtil.POST_METHOD:
                    response = RestfulUtil.httpPost(interfaceDTO.getUrl(), uriVariables, headerVariables, content, RestfulUtil.UTF8_CHARSET);
                    break;
                case RestfulUtil.PUT_METHOD:
                    response = RestfulUtil.httpPut(interfaceDTO.getUrl(), uriVariables, headerVariables, content, RestfulUtil.UTF8_CHARSET);
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
                    method,
                    content,
                    interfaceDTO.getUrl(),
                    Long.valueOf(Objects.isNull(response.getCode()) ? -1 : response.getCode()),
                    response.getResponseBody()
            );
            interfaceLogMapper.insertSelective(interfaceLog);
        }
        return response;
    }

    private String processSoap(String interfaceCode, String content, Long readTimeout, Long writeTimeout, Long connectTimeout){
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
            if(Objects.isNull(readTimeout) || Objects.isNull(writeTimeout) || Objects.isNull(connectTimeout)){
                response = SoapUtil.send(interfaceDTO.getUrl(), interfaceParamsMap.get("Username").get(0).getParamValue(), interfaceParamsMap.get("Password").get(0).getParamValue(), content);
            }else {
                response = SoapUtil.send(interfaceDTO.getUrl(), interfaceParamsMap.get("Username").get(0).getParamValue(), interfaceParamsMap.get("Password").get(0).getParamValue(), content, readTimeout, writeTimeout, connectTimeout);
            }
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
}
