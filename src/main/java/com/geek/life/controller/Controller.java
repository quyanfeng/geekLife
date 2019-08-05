package com.geek.life.controller;

import com.geek.life.util.WeightRandom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 屈艳锋
 * @Date: 2019/7/3 19:30
 */
@Api(description = "作业")
@RestController
public class Controller {

    //缓存数据
    private static List<Map<String, Object>> sourceExeclList = new ArrayList<>();

    @PostMapping("/importModel")
    @ApiOperation("导入作业模板")
    public void importModel(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            ReadExcel readExcel = new ReadExcel();
            sourceExeclList = readExcel.getExcelInfo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/exportModel")
    @ApiOperation("导出作业模板")
    public void exportModel(
            HttpServletRequest request, HttpServletResponse response) {
        try {

            //表头
            String[] title = {"序号", "性别", "学历", "入职时间", "地区", "部门"
                    , "入职岗位", "离职时间", "现职位/离职岗位", "薪资层级（A≤4k B:4k-8k C:8-10k D:10-15K E:≥15k）", "出生年份", "职级", "入职年纪"
                    , "是否已婚", "子女数量", "绩效评分等级", "员工满意度", "离职原因", "职称情况", "初次工作时间", "是否是校招", "政治面貌"
            };

            //创建excel工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();

            //创建工作表sheet
            HSSFSheet sheet = workbook.createSheet();

            //创建表头
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = null;
            for (int i = 0; i < title.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
            }
            int i = 0;
            //创建内容
            for (Map<String, Object> map : sourceExeclList) {

                //由于之前表头已经占据一行了，所以现在从第二行开始插入
                HSSFRow nrow = sheet.createRow(i + 1);
                int m = 0;
                for (Object value : map.values()) {
                    HSSFCell ncell = nrow.createCell(m);
                    ncell.setCellValue((String) value);
                    m++;
                }

                //是否已婚
                // 30岁以下60%已婚,30-35岁70%已婚,35岁以上80%已婚
                List<Pair<String, Integer>> list = new ArrayList<Pair<String, Integer>>();
                int age = Integer.valueOf((String) map.get("12")).intValue();
                if (age <= 30) {
                    list.add(new ImmutablePair<>("已婚", 6));
                    list.add(new ImmutablePair<>("未婚", 4));
                } else if (age > 30 && age <= 35) {
                    list.add(new ImmutablePair<>("已婚", 7));
                    list.add(new ImmutablePair<>("未婚", 3));
                } else if (age > 35) {
                    list.add(new ImmutablePair<>("已婚", 8));
                    list.add(new ImmutablePair<>("未婚", 2));
                }
                WeightRandom weightRandom = new WeightRandom(list);

                String yihun = (String) weightRandom.random();

                HSSFCell ncell = nrow.createCell(m);
                ncell.setCellValue((String) yihun);
                m++;

                //孩子数量
                //已婚人士有1个孩子的57.89%，2个孩子的19.25%，0.3%有3个孩子，其他没有孩子
                list = new ArrayList<Pair<String, Integer>>();
                String chilidren = "0";
                if ("已婚".equals(yihun)) {
                    list.add(new ImmutablePair<>("0", 10000 - 5789 - 1925 - 30));
                    list.add(new ImmutablePair<>("1", 5789));
                    list.add(new ImmutablePair<>("2", 1925));
                    list.add(new ImmutablePair<>("3", 30));
                    weightRandom = new WeightRandom(list);
                    chilidren = (String) weightRandom.random();
                }
                HSSFCell ncel2 = nrow.createCell(m);
                ncel2.setCellValue(chilidren);
                m++;

                //绩效评分等级
                //0.4%A级卓越
                //25.5%B级优秀
                //67.6%C级良好
                //4.7%D级不佳
                //1.8%E级淘汰
                list = new ArrayList<Pair<String, Integer>>();
                list.add(new ImmutablePair<>("A", 4));
                list.add(new ImmutablePair<>("B", 255));
                list.add(new ImmutablePair<>("C", 676));
                list.add(new ImmutablePair<>("D", 47));
                list.add(new ImmutablePair<>("E", 18));
                weightRandom = new WeightRandom(list);
                String jixiao = (String) weightRandom.random();
                HSSFCell ncel3 = nrow.createCell(m);
                ncel3.setCellValue(jixiao);
                m++;


                //员工满意度
//                A：65.01%非常满意
//                B21.78一般满意
//                C9.52些许不满意
//                D3.69非常不满意
                list = new ArrayList<Pair<String, Integer>>();
                list.add(new ImmutablePair<>("A", 6501));
                list.add(new ImmutablePair<>("B", 2178));
                list.add(new ImmutablePair<>("C", 952));
                list.add(new ImmutablePair<>("D", 369));
                weightRandom = new WeightRandom(list);
                String manyidu = (String) weightRandom.random();
                HSSFCell ncel4 = nrow.createCell(m);
                ncel4.setCellValue(manyidu);
                m++;


                //离职原因
                list = new ArrayList<Pair<String, Integer>>();

                String  lizhishijian = (String) map.get("7");
                String lizhi="";
                if(StringUtils.isNotBlank(lizhishijian)){

                    list.add(new ImmutablePair<>("A1", 707));
                    list.add(new ImmutablePair<>("B1", 10134));
                    list.add(new ImmutablePair<>("B2", 5656));
                    list.add(new ImmutablePair<>("B3", 2200));
                    list.add(new ImmutablePair<>("B4", 3456));
                    list.add(new ImmutablePair<>("C1", 13983));
                    list.add(new ImmutablePair<>("C2", 1885));
                    list.add(new ImmutablePair<>("D1", 20660));
                    list.add(new ImmutablePair<>("D2", 2200));
                    list.add(new ImmutablePair<>("D3", 3221));
                    list.add(new ImmutablePair<>("E1", 28987));
                    list.add(new ImmutablePair<>("E2", 2671));
                    list.add(new ImmutablePair<>("E3", 1728));
                    list.add(new ImmutablePair<>("F1", 1100));
                    list.add(new ImmutablePair<>("F2", 1314));
                    list.add(new ImmutablePair<>("G1", 100));
                    weightRandom = new WeightRandom(list);
                     lizhi = (String) weightRandom.random();
                }
                HSSFCell ncel5 = nrow.createCell(m);
                ncel5.setCellValue(lizhi);
                m++;



                //职称情况
                list = new ArrayList<Pair<String, Integer>>();
                list.add(new ImmutablePair<>("A", 6501));
                list.add(new ImmutablePair<>("B", 2178));
                list.add(new ImmutablePair<>("C", 952));
                list.add(new ImmutablePair<>("D", 369));
                weightRandom = new WeightRandom(list);
                String zhicheng = (String) weightRandom.random();
                HSSFCell ncel6 = nrow.createCell(m);
                ncel6.setCellValue(zhicheng);
                m++;




                //初次工作时间
                //博士：28,29
                //硕士25岁，26岁，27岁
                //本科：21岁，22岁，23岁，24岁
                //大专：20岁，21岁
                //高中：18岁
                list = new ArrayList<Pair<String, Integer>>();
                String xueli=(String)map.get("2");
                if ("博士".equals(xueli)) {
                    list.add(new ImmutablePair<>("28", 6));
                    list.add(new ImmutablePair<>("29", 4));
                } else if ("硕士".equals(xueli)) {
                    list.add(new ImmutablePair<>("25", 7));
                    list.add(new ImmutablePair<>("26", 3));
                    list.add(new ImmutablePair<>("27", 3));
                } else if ("本科".equals(xueli)){
                    list.add(new ImmutablePair<>("21", 2));
                    list.add(new ImmutablePair<>("22", 3));
                    list.add(new ImmutablePair<>("23", 3));
                    list.add(new ImmutablePair<>("24", 2));
                }else if ("大专".equals(xueli)||"专科".equals(xueli)){
                    list.add(new ImmutablePair<>("21", 3));
                    list.add(new ImmutablePair<>("20", 2));
                }else if ("高中".equals(xueli)){
                    list.add(new ImmutablePair<>("18", 2));
                    list.add(new ImmutablePair<>("17", 2));
                }else if ("博士后".equals(xueli)) {
                    list.add(new ImmutablePair<>("31", 6));
                    list.add(new ImmutablePair<>("32", 4));
                }else if ("中专".equals(xueli)) {
                    list.add(new ImmutablePair<>("18", 6));
                    list.add(new ImmutablePair<>("17", 4));
                }else if ("职高".equals(xueli)) {
                    list.add(new ImmutablePair<>("18", 6));
                    list.add(new ImmutablePair<>("17", 4));
                }

                System.out.println("xueli"+xueli);
                weightRandom = new WeightRandom(list);
                String chuci = (String) weightRandom.random();
                HSSFCell ncel7 = nrow.createCell(m);
                ncel7.setCellValue(chuci);
                m++;




                //是否是校招
                //15年以后开始校招，其中15年入职的28
                //16年 50人
                //17年90个
                //18年70个
                list = new ArrayList<Pair<String, Integer>>();
                list.add(new ImmutablePair<>("A", 6501));
                list.add(new ImmutablePair<>("B", 2178));
                list.add(new ImmutablePair<>("C", 952));
                list.add(new ImmutablePair<>("D", 369));
                weightRandom = new WeightRandom(list);
                String xiaozhao = (String) weightRandom.random();
                HSSFCell ncel8 = nrow.createCell(m);
                ncel8.setCellValue(xiaozhao);
                m++;




                //政治面貌
                // 本科40%党员
                //硕士80%党员
                list = new ArrayList<Pair<String, Integer>>();
                if ("博士".equals(xueli)) {
                    list.add(new ImmutablePair<>("党员", 9));
                    list.add(new ImmutablePair<>("群众", 1));
                } else if ("硕士".equals(xueli)) {
                    list.add(new ImmutablePair<>("党员", 8));
                    list.add(new ImmutablePair<>("群众", 2));
                } else if ("本科".equals(xueli)){
                    list.add(new ImmutablePair<>("党员", 4));
                    list.add(new ImmutablePair<>("群众", 6));
                }else {
                    list.add(new ImmutablePair<>("群众", 1));
                }
                weightRandom = new WeightRandom(list);
                String mianmao = (String) weightRandom.random();
                HSSFCell ncel9 = nrow.createCell(m);
                ncel9.setCellValue(mianmao);
                m++;


                i++;
            }

            //返回结果
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename=work.xls");
            response.setContentType("application/vnd.ms-excel");
            workbook.write(output);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
