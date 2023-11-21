package com.base.basic.app.service.impl;

import com.base.basic.app.service.EasyExcelService;
import com.base.basic.app.service.UserService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.exc.UserExcelModel;
import com.base.basic.domain.vo.v0.ColumnVO;
import com.base.basic.infra.mapper.DataBaseMapper;
import com.base.basic.infra.mapper.UserMapper;
import com.base.common.util.excel.helper.EasyExcelHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class EasyExcelServiceImpl implements EasyExcelService {

    @Autowired
    @SuppressWarnings("all")
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    @SuppressWarnings("all")
    private DataBaseMapper dataBaseMapper;

    @Override
    public String exportTable(HttpServletResponse response, String tableName){
        // 获取需要导出的数据
        List<ColumnVO> columnVOList = dataBaseMapper.columnList(tableName);

//        List<UserExcelModel> userExcelModels = new ArrayList<>(32);
//        columnVOList.stream().forEach(user -> {
//            UserExcelModel userExcelModel = new UserExcelModel();
//            BeanUtils.copyProperties(user, userExcelModel);
//            userExcelModels.add(userExcelModel);
//        });
//
//        // 导出
//        EasyExcelHelper.getInstance().easyDynamicExport(response, "数据库表", "表"+tableName, null, userExcelModels);
        return "导出成功";
    }

    @Override
    public String importData(MultipartFile file){
        EasyExcelHelper.getInstance().easyImport(0, UserExcelModel.class, userService, file);
        return "导入成功";
    }
}
