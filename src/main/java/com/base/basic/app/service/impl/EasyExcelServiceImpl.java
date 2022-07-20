package com.base.basic.app.service.impl;

import com.base.basic.app.service.EasyExcelService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.exc.UserExcelModel;
import com.base.basic.domain.repository.UserRepository;
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
    private UserRepository userRepository;

    @Override
    public String exportData(HttpServletResponse response){
        // 获取需要导出的数据
        List<IamUser> userList = userRepository.list(new IamUser());

        List<UserExcelModel> userExcelModels = new ArrayList<>(32);
        userList.stream().forEach(user -> {
            UserExcelModel userExcelModel = new UserExcelModel();
            BeanUtils.copyProperties(user, userExcelModel);
            userExcelModels.add(userExcelModel);
        });

        // 导出
        EasyExcelHelper.getInstance().easyExport(response, "用户信息表", "用户信息", UserExcelModel.class, userExcelModels);
        return "导出成功";
    }

    @Override
    public String importData(MultipartFile file){
        EasyExcelHelper.getInstance().easyImport(0, UserExcelModel.class, userRepository, file);
        return "导入成功";
    }
}
