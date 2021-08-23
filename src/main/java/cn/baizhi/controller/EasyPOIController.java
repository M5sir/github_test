package cn.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.baizhi.entity.User;
import cn.baizhi.service.UserService;
import cn.baizhi.util.DeleteFile;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/easyPOI")
public class EasyPOIController {

    @Autowired
    private UserService us;
    //excel表导出
    @RequestMapping("/query")
    public void excelEasyPOI() throws IOException {
        //List<User> users = new ArrayList<User>();
        List<User> users1 = us.selectAll();
        for (User user : users1) {
            String path = DeleteFile.testDownLoad(user.getHeadimg());
            user.setHeadimg(path);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户注册明细", "用户表"), User.class, users1);
        workbook.write(new FileOutputStream(new File("D:/easypoi.xls")));
        workbook.close();
    }
}

