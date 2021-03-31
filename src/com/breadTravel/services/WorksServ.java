package com.breadTravel.services;

import com.amap.api.maps.model.LatLng;
import com.breadTravel.dao.UserDaoImpl;
import com.breadTravel.dao.WorksDaoImp;
import com.breadTravel.entity.WorksContent;
import com.breadTravel.entity.WorksPreview;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(name = "WorksServ", urlPatterns = "/WorksServ")
public class WorksServ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("act");
        if (str.equals("recommendPreview")) {
            recommendPreview(req, resp);
        }
        if (str.equals("attentionPreview")) {
            attentionPreview(req, resp);
        }
        if (str.equals("praisePreview")) {
            praisePreview(req, resp);
        }
        if (str.equals("myWorksPreview")) {
            getMyWorksPreview(req, resp);
        }
        if (str.equals("worksContent")) {
            worksContent(req, resp);
        }
        if (str.equals("initRelation")) {
            initRelation(req, resp);
        }
        if (str.equals("upLoadWorksPreview")) {
            upLoadWorksPreview(req, resp);
        }
        if (str.equals("upLoadWorksContent")){
            upLoadWorksContent(req,resp);
        }
        if (str.equals("upLoadFile")) {
            upLoadFile(req, resp);
        }
        if (str.equals("deleteWorks")){
            deleteWorks(req,resp);
        }
        if (str.equals("praise")){
            praise(req,resp);
        }
        if (str.equals("attention")){
            attention(req,resp);
        }
        if (str.equals("cityPreview")){
            cityPreview(req,resp);
        }

    }


    private void cityPreview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String city = req.getParameter("city");
        System.out.println("cityPreview    " + city);
        JSONArray jsonArray = new WorksDaoImp().getCityPreview(city);
        System.out.println(jsonArray.toString());
        PrintWriter out = resp.getWriter();
        out.write(jsonArray.toString());
    }

    private void attention(HttpServletRequest req, HttpServletResponse resp) {
        String userName=req.getParameter("userName");
        String worksUser=req.getParameter("worksUser");
        String attention=req.getParameter("attention");
        if (attention.equals("del")){
            System.out.println("del_attention      "+userName);
        }else {
            System.out.println("add_attention      "+userName);
        }
        new WorksDaoImp().attention(userName,worksUser,attention);
    }

    private void praise(HttpServletRequest req, HttpServletResponse resp) {
        String worksId = req.getParameter("worksId");
        String userName=req.getParameter("userName");
        String praise=req.getParameter("praise");
        String praiseNum=req.getParameter("praiseNum");
        if (praise.equals("del")){
            System.out.println("del_Praise      "+worksId);
        }else {
            System.out.println("add_Praise      "+worksId);
        }
        new WorksDaoImp().praise(userName,Long.valueOf(worksId),praise,Long.valueOf(praiseNum));

    }

    private void deleteWorks(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String worksId=req.getParameter("worksId");
        System.out.println("deleteWorks     "+worksId);
        PrintWriter out = resp.getWriter();
        boolean flag=new WorksDaoImp().deleteWorks(Long.valueOf(worksId));
        if (flag==true){
            System.out.println("pass");
            out.print("T");
        }else {
            System.out.println("error");
            out.print("F");
        }

    }

    private void upLoadWorksContent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String worksContent = req.getParameter("worksContent");
        System.out.println("upLoadWorksContent      " + worksContent);
        WorksContent worksContent1=new WorksContent();
        JSONObject jsonObject=new JSONObject(worksContent);
        worksContent1.setWorksId(jsonObject.getLong("worksId"));
        worksContent1.setDate(jsonObject.getString("date"));
        worksContent1.setTime(jsonObject.getString("time"));
        worksContent1.setLocation(jsonObject.getString("location"));
        worksContent1.setPhoto(jsonObject.getString("photo"));
        worksContent1.setContentText(jsonObject.getString("contentText"));
        PrintWriter out = resp.getWriter();
        boolean flag=new WorksDaoImp().addWorksContent(worksContent1);
        if (flag==true){
            System.out.println("pass");
            out.print("T");
        }else {
            System.out.println("error");
            out.print("F");
        }
    }

    private void upLoadFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        // 创建文件项目工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置文件上传路径
        String savePath = this.getServletContext().getRealPath("/upload");
        // 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
        String temp = System.getProperty("java.io.tmpdir");
        // 设置缓冲区大小为 5M
        factory.setSizeThreshold(1024 * 1024 * 5);
        // 设置临时文件夹为temp
        factory.setRepository(new File(temp));
        // 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        String value = "", name = "";
        // 解析结果放在List中
        try {
            //为上传表单，则调用解析器解析上传数据
            List<FileItem> list = servletFileUpload.parseRequest(req);  //FileItem
            System.out.println(req.getContentLength()+"!!!!!!!!!!!!!");
            //遍历list，得到用于封装第一个上传输入项数据fileItem对象
            for (FileItem item : list) {

                if (item.isFormField()) {
                    //得到的是普通输入项
                    name = item.getFieldName();  //得到输入项的名称
                    value = item.getString();
                    savePath = savePath + "\\" + value;
                } else {
                    //得到上传输入项
                    String filename = item.getName();  //得到上传文件名  C:\Documents and Settings\ThinkPad\桌面\1.txt
                    filename = filename.substring(filename.lastIndexOf("\\") + 1);
                    InputStream in = item.getInputStream();   //得到上传数据
                    int len = 0;
                    byte buffer[] = new byte[1024];
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(savePath + "\\" + filename);  //向upload目录中写入文件
                    while ((len = in.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    System.out.println("upLoadFile   " + value + ":" + filename);
                    in.close();
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.write("F");
        }
        out.flush();
        out.close();
    }

    private void upLoadWorksPreview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String worksPreview = req.getParameter("worksPreview");
        System.out.println("upLoadWorksPreview      " + worksPreview);
        WorksPreview worksPreview1 = new WorksPreview();
        JSONObject jsonObject = new JSONObject(worksPreview);
        worksPreview1.setUserName(jsonObject.getString("userName"));
        worksPreview1.setTitle(jsonObject.getString("title"));
        worksPreview1.setDate(jsonObject.getString("date"));
        worksPreview1.setDay(jsonObject.getLong("day"));
        worksPreview1.setRegion(jsonObject.getString("region"));
        worksPreview1.setCoverImg(jsonObject.getString("coverImg"));
        PrintWriter out = resp.getWriter();
        long worksId = new WorksDaoImp().addWorksPreview(worksPreview1);
        if (worksId!=0) {
            System.out.println("pass");
            out.print(String.valueOf(worksId));
        } else {
            System.out.println("error");
            out.print("F");
        }
    }

    private void initRelation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String worksId = req.getParameter("worksId");
        String skimNum = req.getParameter("skimNum");
        String userName=req.getParameter("userName");
        String worksUser=req.getParameter("worksUser");
        System.out.println("initRelation     " + userName);
        PrintWriter out=resp.getWriter();
        String result=new WorksDaoImp().addBrowse(Long.valueOf(worksId), Long.valueOf(skimNum),userName,worksUser);
        if (result!=null){
            System.out.println(result);
            out.write(result);
        }else {
            out.print("F");
        }
    }

    private void worksContent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String worksId = req.getParameter("worksId");
        System.out.println("worksId     " + worksId);
        JSONArray jsonArray = new WorksDaoImp().getWorksContent(Long.valueOf(worksId));
        System.out.println(jsonArray.toString());
        PrintWriter out = resp.getWriter();
        out.write(jsonArray.toString());
    }

    private void getMyWorksPreview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userName = req.getParameter("userName");
        System.out.println("myWorksPreview    " + userName);
        JSONArray jsonArray = new WorksDaoImp().getMyWorksPreview(userName);
        System.out.println(jsonArray.toString());
        PrintWriter out = resp.getWriter();
        out.write(jsonArray.toString());
    }

    private void praisePreview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userName = req.getParameter("userName");
        System.out.println("praisePreview    " + userName);
        JSONArray jsonArray = new WorksDaoImp().getPraisePreview(userName);
        System.out.println(jsonArray.toString());
        PrintWriter out = resp.getWriter();
        out.write(jsonArray.toString());
    }

    private void attentionPreview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userName = req.getParameter("userName");
        System.out.println("attentionPreview    " + userName);
        JSONArray jsonArray = new WorksDaoImp().getAttentionPreview(userName);
        System.out.println(jsonArray.toString());
        PrintWriter out = resp.getWriter();
        out.write(jsonArray.toString());
    }

    private void recommendPreview(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userName = req.getParameter("userName");
        System.out.println("recommendPreview    " + userName);
        JSONArray jsonArray = new WorksDaoImp().getRecommendPreview();
        System.out.println(jsonArray.toString());
        PrintWriter out = resp.getWriter();
        out.write(jsonArray.toString());
    }


}
