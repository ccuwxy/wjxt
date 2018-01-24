package cn.wenjianxitong.www.temporaryfile.serlvet;

import cn.wenjianxitong.www.FileInfo;
import cn.wenjianxitong.www.exception.file.CanNotHandlerFileException;
import cn.wenjianxitong.www.exception.file.InvalidParamsException;
import cn.wenjianxitong.www.exception.file.MongoDbException;
import cn.wenjianxitong.www.temporaryfile.Gson.UploadFileRetGson;
import cn.wenjianxitong.www.temporaryfile.controller.Download;
import cn.wenjianxitong.www.temporaryfile.controller.Upload;
import cn.wenjianxitong.www.temporaryfile.model.Files;
import cn.wenjianxitong.www.utils.InputStrUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;

public class TemporaryFile extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type","text/html;charset=utf-8");
        String retStr = "";
        byte[] retFile = null;
        String fileName = "";
        try {
            InputStrUtils inputStrUtils = new InputStrUtils(request);
            String md5 = inputStrUtils.getRequiredParameter("file_token");
            Download download = new Download(md5);
            fileName = URLEncoder.encode(download.getFileName(), "utf-8"); //解决中文文件名下载后乱码的问题
            retFile = download.getFileByte();
        } catch (MongoDbException e) {
            retStr = e.getRetJson();
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        }
        try {
            if (retStr == "") {
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "");
                ServletOutputStream out = response.getOutputStream();
                out.write(retFile);
                out.flush();
                out.close();
            } else {
                response.getWriter().print(retStr);
            }
        } catch (Exception e) {
            response.getWriter().print(new CanNotHandlerFileException().getRetJson());
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //System.out.println("doUpload1");
        String retStr = "";
        try {
            ArrayList<FileInfo> fileInfos = Files.getFile(request);
            UploadFileRetGson uploadFileRetGson = new UploadFileRetGson();
            for (FileInfo fileInfo : fileInfos) {
                try {
                    Upload upload = new Upload(fileInfo);
                    uploadFileRetGson.addFileToken(upload.getsFileToken());
                    uploadFileRetGson.getCheck_code();
                } catch (ParseException ee) {
                    throw new CanNotHandlerFileException();
                }
            }
            retStr = new Gson().toJson(uploadFileRetGson);
        } catch (InvalidParamsException e) {
            retStr = e.getRetJson();
        } catch (CanNotHandlerFileException e) {
            retStr = e.getRetJson();
        }
        response.getWriter().print(retStr);
    }
}
