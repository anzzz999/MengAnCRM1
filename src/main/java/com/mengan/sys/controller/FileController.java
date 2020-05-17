package com.mengan.sys.controller;


import com.mengan.bus.domain.Contract;
import com.mengan.bus.service.ContractService;
import com.mengan.sys.domain.DataGridView;
import com.mengan.sys.utils.AppFileUtils;
import com.mengan.sys.utils.RandomUtils;
import com.mengan.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("file")
public class FileController {

    @Autowired
    ContractService contractService;
    /**
     * 添加
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("uploadFile")
    @ResponseBody
    public DataGridView uploadFile(MultipartFile mf) throws IllegalStateException, IOException {
        // 文件上传的父目录
        String parentPath = AppFileUtils.PATH;
        // 得到当前日期作为文件夹名称
        String dirName = RandomUtils.getCurrentDateForString();
        // 构造文件夹对象
        File dirFile = new File(parentPath, dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();// 创建文件夹
        }
        // 得到文件原名
        String oldName = mf.getOriginalFilename();
        // 根据文件原名得到新名
        String newName = RandomUtils.createFileNameUseTime(oldName);
        File dest = new File(dirFile, newName);
/*        File dest = new File(dirFile, oldName);*/
        mf.transferTo(dest);

        Map<String,Object> map=new HashMap<>();
        map.put("src", dirName+"/"+newName);
/*        map.put("src", dirName+"/"+oldName);*/
        return new DataGridView(map);

    }


    /**
     * 下载合同
     * @param path
     * @param response
     * @return
     */
    @RequestMapping("downloadFile")
    public ResponseEntity<Object> downloadFile(String path,String cname, HttpServletResponse response) {
        String[] split = path.split("\\.");
        cname=cname+"的合同."+split[1];
        /*System.out.println(cname+"++++++++++++");*/
        return AppFileUtils.downloadFile(response, path, cname);
    }

    /*public ResponseEntity<Object> downloadFile(Integer id, HttpServletResponse response) {
        Contract contract = contractService.queryContractById(id);
        String path=contract.getUrl();
        String oldName=contract.getRemark();
        String url =AppFileUtils.PATH+path;
        *//*ResponseEntity<Object> objectResponseEntity = AppFileUtils.downloadFile(response,path,oldName);*//*
        return AppFileUtils.downloadFile(response, path, oldName);
    }*/

}
