package com.heima.servlet;

import com.heima.utils.UUIDUtils;
import com.heima.utils.UpLoadUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by Feng on 2017/1/8.
 */
@WebServlet(name = "ServletFileUpLoad", urlPatterns = "/ServletFileUpLoad")
@MultipartConfig
public class ServletFileUpLoad extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filedesc = request.getParameter("filedesc");
        Part part = request.getPart("fileload");
        long size = part.getSize();
        System.out.println("文件大小:" + size);
        String header = part.getHeader("Content-Disposition");
        int lax = header.lastIndexOf("filename=\"");
        String fileName = header.substring(lax + 10, header.length() - 1);
        System.out.println("文件名:" + fileName);
        String uuidFileName = UUIDUtils.getUUIDFileName(fileName);
        System.out.println("唯一文件名:" + uuidFileName);


        InputStream is = part.getInputStream();
        String path = this.getServletContext().getRealPath("/fileup" );
        //System.out.println("文件路径" + path);
        File file=new File(path+UpLoadUtils.getPath(uuidFileName));
        if (!file.exists()){
            file.mkdirs();
        }
        System.out.println("文件路径" + file);
        OutputStream os = new FileOutputStream(file+ uuidFileName);
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }
        os.close();
        is.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
