package studyarea.notice.servlet;

import domain.Data;
import studyarea.notice.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SelectNoticeServlet,查询公告通知的接口
 */
public class SelectNoticeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setCharacterEncoding("utf8");
        //设置响应的数据类型
        resp.setContentType("application/json;charset = utf-8");
        //获取请求方式
        String method = req.getMethod();
        //获取请求参数
        String u_id = req.getParameter("u_id");
        String character = req.getParameter("character");
        String n_id = req.getParameter("n_id");
        String orderBy = req.getParameter("orderBy");
        String n_author = req.getParameter("author");
        String isMore = req.getParameter("isMore");


        //服务器日志：打印输出接收的参数---用户id、用户身份、公告id、排序方式、发布者id
        System.out.println("SelectNoticeServlet 用户id："+u_id);
        System.out.println("SelectNoticeServlet 用户身份："+character);
        System.out.println("SelectNoticeServlet 公告id："+n_id);
        System.out.println("SelectNoticeServlet 排序方式："+orderBy);
        System.out.println("SelectNoticeServlet 发布者："+n_author);
        System.out.println("SelectNoticeServlet isMore："+isMore);

        //创建一个Data
        Data data = new Data();
        //创建NoticeService对象
        NoticeService noticeService = new NoticeService();
        //调用doSelect方法
        data = noticeService.doSelect(data,orderBy,n_author,isMore);
        //响应值
        req.setAttribute("notice",data.getList());
        //服务器日志：返回给客户端的notice javabean
        System.out.println("SelectNoticeServlet 返回给客户端的notice："+data);
        if ("1".equals(isMore)){
            //转发
            req.getRequestDispatcher("/moreNotice.jsp").forward(req, resp);
        }else {

            //转发
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
