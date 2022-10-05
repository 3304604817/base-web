package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.FormatToolService;
import com.base.basic.domain.vo.v0.FormatToolVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class FormatToolServiceImpl implements FormatToolService {
    Logger logger = LoggerFactory.getLogger(FormatToolServiceImpl.class);

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

    @Override
    public FormatToolVO md5Encryp(FormatToolVO formatToolVO){
        try {
            String md5 = DigestUtils.md5DigestAsHex(formatToolVO.getInputText().getBytes("utf-8"));
            formatToolVO.setOutputText(md5);
        }catch (Exception e){
            e.printStackTrace();
            formatToolVO.setErrorMsg("加密失败");
        }
        return formatToolVO;
    }

    @Override
    public FormatToolVO md5Decrypt(FormatToolVO formatToolVO){
        try {
            /**
             * ASC|| 码范围在：33-126之间
             * length:Hash碰撞字符长度
             * index:第几位的字符
             */
            logger.info("--------生成全排列组合组合");
            List<String> decodes = new CopyOnWriteArrayList<>();
            for(int length = 1; length <= 3; length++){
                this.recFullCombin(length, 33, 126, "", decodes);
            }

            for(String decode:decodes){
                if(DigestUtils.md5DigestAsHex(formatToolVO.getInputText().getBytes("utf-8")).equals(decode)){
                    formatToolVO.setOutputText(decode);
                    break;
                }
            }

            if(Objects.isNull(formatToolVO.getOutputText())){
                formatToolVO.setOutputText("解密失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            formatToolVO.setErrorMsg("解密异常");
        }
        return formatToolVO;
    }

    @Override
    public FormatToolVO uuidGenerate(FormatToolVO formatToolVO){
        String uuid = UUID.randomUUID().toString();
        formatToolVO.setOutputText(uuid);
        return formatToolVO;
    }

    @Override
    public FormatToolVO urlEncoder(FormatToolVO formatToolVO) throws UnsupportedEncodingException {
        formatToolVO.setOutputText(
                URLEncoder.encode(formatToolVO.getInputText(), "UTF-8")
        );
        return formatToolVO;
    }

    @Override
    public FormatToolVO urlDecoder(FormatToolVO formatToolVO) throws UnsupportedEncodingException {
        formatToolVO.setOutputText(
                URLDecoder.decode(formatToolVO.getInputText(), "UTF-8")
        );
        return formatToolVO;
    }

    public void recFullCombin(int length, int charStart, int charEnd, String decode, List<String> decodes){
        if(decode.length() < length){
            for(int i = charStart; i <= charEnd; i++){
                this.recFullCombin(length, charStart, charEnd, decode + (char)i, decodes);
            }
        } else if(decode.length() == length){
            decodes.add(decode);
        } else {
            return;
        }
    }
}
