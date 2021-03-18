package com.huohuzhihui.system.util;

import com.alibaba.fastjson.JSON;
import com.huohuzhihui.system.domain.CloudStorage;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
public class QiNiuFileOptUtil {

    public static DefaultPutRet upload(MultipartFile file, CloudStorage cloudStorage){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huadong());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        String accessKey = cloudStorage.getStorageKey();
        String secretKey = cloudStorage.getStorageSecret();
        String bucket = cloudStorage.getStorageBucket();

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = System.currentTimeMillis()+".JPG";

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            log.info("七牛授权"+ JSON.toJSONString(auth));
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(file.getInputStream(),key,upToken,null, null);
            log.info("七牛上传结果"+ JSON.toJSONString(response));
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return putRet;
        } catch (QiniuException ex) {
            log.error("七牛上传失败",ex);
        }catch (Exception e){
            log.error("七牛上传失败",e);
        }

        return null;
    }
}
