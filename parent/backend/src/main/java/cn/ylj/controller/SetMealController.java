package cn.ylj.controller;

import cn.ylj.constant.MessageConstant;
import cn.ylj.entity.Setmeal;
import cn.ylj.model.Result;
import cn.ylj.utils.AliOssUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author : yanglujian
 * create at:  2021/1/18  1:20 下午
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] ids) {
        return null;
    }

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile, HttpServletRequest req) {
//        this.saveToLocal(imgFile, req);
        try{
//            QiniuUtils.upload2Qiniu(imgFile.getBytes(), imgFile.getOriginalFilename());
            AliOssUtils.upload(imgFile.getBytes(), imgFile.getOriginalFilename());
        } catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS);
    }

    /**
     * 保存到本地文件夹
     * @param imgFile
     * @param req
     * @throws IOException
     */
    private void saveToLocal(MultipartFile imgFile, HttpServletRequest req) throws IOException {
        String filename = imgFile.getOriginalFilename();
        String rootPath = req.getSession().getServletContext().getRealPath("/");
        File imgDir = new File(rootPath, "img_upload");
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }
        imgFile.transferTo(new File(imgDir, filename));
    }

}