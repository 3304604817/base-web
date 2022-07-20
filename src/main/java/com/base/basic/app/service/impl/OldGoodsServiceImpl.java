package com.base.basic.app.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.base.basic.app.service.OldGoodsService;
import com.base.basic.domain.entity.v0.IamUser;
import com.base.basic.domain.entity.v1.OldGoods;
import com.base.basic.domain.entity.v1.OldGoodsDetail;
import com.base.basic.domain.exc.OldGoodsExcelModel;
import com.base.basic.domain.exc.UserExcelModel;
import com.base.basic.domain.repository.OldGoodsRepository;
import com.base.basic.infra.mapper.OldGoodsDetailMapper;
import com.base.basic.infra.mapper.OldGoodsMapper;
import com.base.common.util.excel.EasyExcelListener;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 二手商品表应用服务默认实现
 */
@Service
public class OldGoodsServiceImpl implements OldGoodsService {

    @Autowired
    private OldGoodsMapper oldGoodsMapper;
    @Autowired
    private OldGoodsDetailMapper oldGoodsDetailMapper;
    @Autowired
    private OldGoodsRepository oldGoodsRepository;

    /**
     * 数据字典分页查询
     * @param pageParmaters 分页信息
     * @return
     */
    @Override
    public PageInfo<OldGoods> pageList(PageParmaters pageParmaters, OldGoods searchBody){
        PageInfo<OldGoods> pages = oldGoodsRepository.pageList(pageParmaters, searchBody);
        return pages;
    }

    @Override
    public OldGoods detail(Long id){
        OldGoods oldGoods = oldGoodsMapper.selectByPrimaryKey(id);
        OldGoodsDetail oldGoodsDetail = new OldGoodsDetail();
        oldGoodsDetail.setGoodsId(oldGoods.getId());
        oldGoods.setOldGoodsDetail(
                oldGoodsDetailMapper.selectOne(oldGoodsDetail)
        );
        return oldGoods;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OldGoods save(OldGoods oldGoods){
        /**
         * 更新/创建头
         */
        if(Objects.nonNull(oldGoods.getId())){
            // 更新
            oldGoodsMapper.updateByIdSelective(oldGoods);
        }else{
            // 创建
            oldGoods.setSku(UUID.randomUUID().toString());
            oldGoodsMapper.insertSelective(oldGoods);
        }

        /**
         * 更新/创建详情
         */
        OldGoodsDetail oldGoodsDetail = oldGoods.getOldGoodsDetail();
        if(Objects.nonNull(oldGoodsDetail.getId())){
            // 更新
            oldGoodsDetailMapper.updateByIdSelective(oldGoodsDetail);
        }else{
            // 创建
            oldGoodsDetail.setGoodsId(oldGoods.getId());
            oldGoodsDetailMapper.insertSelective(oldGoodsDetail);
        }
        return oldGoods;
    }

    @Override
    public String exportData(HttpServletResponse response){
        try {
            // 获取需要导出的数据
            List<OldGoods> oldGoods = oldGoodsMapper.list(new OldGoods());

            List<OldGoodsExcelModel> oldGoodsExcelModels = new ArrayList<>(32);
            oldGoods.stream().forEach(oldGood -> {
                OldGoodsExcelModel oldGoodsExcelModel = new OldGoodsExcelModel();
                BeanUtils.copyProperties(oldGood, oldGoodsExcelModel);
                oldGoodsExcelModels.add(oldGoodsExcelModel);
            });

            // 设置响应体内容
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("商品信息头", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            /**
             * OldGoodsExcelModel Excel 导出模版
             * oldGoodsExcelModels 导出的数据
             */
            EasyExcel.write(response.getOutputStream(), OldGoodsExcelModel.class).sheet("商品信息头").doWrite(oldGoodsExcelModels);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "导出成功";
    }

    @Override
    public String importData(MultipartFile file){
        try {
            // 获取后缀名
            String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(!suffixName.equals(".xls") && !suffixName.equals(".xlsx")){
                return "文件格式异常";
            }

            // 读取 Excel 第一个 sheet 页
            EasyExcel.read(file.getInputStream(), OldGoodsExcelModel.class, new EasyExcelListener<>(oldGoodsRepository))
                    .excelType(suffixName.equals(".xls") ? ExcelTypeEnum.XLS : ExcelTypeEnum.XLSX)
                    .sheet(0).doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "导入成功";
    }
}
