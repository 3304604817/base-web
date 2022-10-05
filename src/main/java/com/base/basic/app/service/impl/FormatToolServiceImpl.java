package com.base.basic.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.basic.app.service.FormatToolService;
import com.base.basic.domain.vo.v0.FormatToolVO;
import org.apache.commons.compress.utils.ByteUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

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
            for(int i = 0; i < 200; i++){
                System.out.println(String.format("%-5s", i) + "---" + (char)i);
            }

//            while (true){
//                if(DigestUtils.md5DigestAsHex(formatToolVO.getInputText().getBytes("utf-8")).equals(formatToolVO.getInputText())){
//                    formatToolVO.setOutputText(formatToolVO.getInputText());
//                    break;
//                }
//            }
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
}
