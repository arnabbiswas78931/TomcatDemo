package com.demo;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


/**
 * Created by lcinshu on 17-3-31.
 */
public class TomcatDemo extends javax.servlet.http.HttpServlet {

    private String message;
    @Override
    public void init() throws ServletException {
        message = "Hello world,this message is from servlet!";
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        Connection conn = null;
        Statement stat = null;
        ResultSet resultSet = null;
        String driver = "com.mysql.jdbc.Driver";
        String dataurl = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String passwd = "licheng123";


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String title = "Servlet Mysql Test - By ChengLi";

        String docType = "<!DocTYPE html>\n";

        out.println(docType+
                "<html>\n"+
                "<head><title>"+title+"</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n"+
                "<h1 align=\"center\">"+title+"</h1>\n");

        try{
            Class.forName(driver);

            conn = DriverManager.getConnection(dataurl,user,passwd);

            stat = conn.createStatement();

            String sql;
            sql = "select id,name,countary from lcinshu";

            resultSet = stat.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String countary = resultSet.getString("countary");

                out.print("<li>");
                out.print("id:"+id);
                out.print("  name:"+name);
                out.print("  countary:"+countary);
                out.print("</li>");
            }
            out.println("</body></html>");
            resultSet.close();
            stat.close();
            conn.close();
        }catch (SQLException SQLE){
            System.out.println("数据库链接错误");
            SQLE.printStackTrace();

        }catch (Exception e){
            System.out.println("系统出错");
        }
        finally {
            try{
                if(stat != null){
                    stat.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            try {
                if(conn!=null){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
