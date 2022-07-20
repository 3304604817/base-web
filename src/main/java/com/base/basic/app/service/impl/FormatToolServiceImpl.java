package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.FormatToolService;
import com.base.basic.domain.vo.v0.FormatToolVO;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class FormatToolServiceImpl implements FormatToolService {

    @Override
    public FormatToolVO jsonFormat(FormatToolVO formatToolVO){
        try {
            JSONObject jsonObject =  JSON.parseObject(formatToolVO.getInputText());
            formatToolVO.setOutputText(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
            formatToolVO.setErrorMsg("格式化异常");
        }
        return formatToolVO;
    }

    @Override
    public FormatToolVO base64Encryp(FormatToolVO formatToolVO){
        try {
            byte[] inputBytes = formatToolVO.getInputText().getBytes();
            formatToolVO.setOutputText(Base64.getEncoder().encodeToString(inputBytes));
        }catch (Exception e){
            e.printStackTrace();
            formatToolVO.setErrorMsg("加密失败");
        }
        return formatToolVO;
    }

    @Override
    public FormatToolVO base64Decrypt(FormatToolVO formatToolVO){
        try {
            String inputText = formatToolVO.getInputText();
            formatToolVO.setOutputText(new String(Base64.getDecoder().decode(inputText)));
        }catch (Exception e){
            e.printStackTrace();
            formatToolVO.setErrorMsg("解密失败");
        }
        return formatToolVO;
    }
}
