package com.breadTravel.services;

import com.breadTravel.dao.CommentDaoImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CommentServ",urlPatterns = "/CommentServ")
public class CommtentServ extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str=req.getParameter("act");
        if (str.equals("queryComment")){
            queryComment(req,resp);
        }
        if (str.equals("addComment")){
            addComment(req,resp);
        }
    }

    private void addComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String commentJson=req.getParameter("commentJson");
        long worksId = -1;
        String sendUser= null;
        String receiveUser= null;
        String time= null;
        String CommentText= null;
        try {
            JSONObject jsonObject=new JSONObject(commentJson);
            worksId = jsonObject.getLong("worksId");
            sendUser = jsonObject.getString("sendUser");
            receiveUser = jsonObject.getString("receiveUser");
            time = jsonObject.getString("time");
            CommentText = jsonObject.getString("CommentText");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        boolean flag=new CommentDaoImpl().addComment(worksId,sendUser,receiveUser,time,CommentText);
        PrintWriter out=resp.getWriter();
        if (flag==true){
            System.out.println("pass");
            out.print("T");
        }else {
            System.out.println("error");
            out.print("F");
        }

    }

    private void queryComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String worksId=req.getParameter("worksId");
        System.out.println("queryComment    "+worksId);
        JSONArray result=new CommentDaoImpl().queryComment(Long.valueOf(worksId));
        PrintWriter out=resp.getWriter();
        out.write(result.toString());
    }
}
