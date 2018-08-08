package servlet;

import com.alibaba.fastjson.JSON;
import dao.BaseDAO;
import entity.Product;
import entity.Sale;
import entity.SaleMould;
import entity.Uswes;
import org.junit.jupiter.api.Test;
import service.Ivisitservlce;
import service.impl.visitservlceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "visitServlet", urlPatterns = "/visitServlet")
public class visitServlet extends HttpServlet {
    Ivisitservlce ivisitservlce = new visitservlceimpl();


    @Test
    public void test() throws Exception {
        List<SaleMould> list = ivisitservlce.getOnePage(1, 5, "price");
        String data = JSON.toJSONString(list);
        System.out.println(data);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String oper = request.getParameter("oper");
        if (oper.equals("login")) {
            try {
                login(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oper.equals("selectsale")) {
            try {
                System.out.println("进图");
                selectsale(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oper.equals("addsale")) {
            try {
                System.out.println("添加");
                addpr(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oper.equals("getTotalusers")) {
            try {
                System.out.println("数量");
                getTotalusers(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oper.equals("listselectpage")) {
            try {
                System.out.println("分页");
                listselectpage(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (oper.equals("removeAttribute")) {
            try {
                System.out.println("注銷");
                request.getSession().removeAttribute("upwdname");
                response.sendRedirect("login.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void listselectpage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int ageIndexTemp = Integer.parseInt(request.getParameter("page")); // 当前页数
        String by = request.getParameter("by"); // 当前页数
        List<SaleMould> list = ivisitservlce.getOnePage(ageIndexTemp, 5, by);
        String data = JSON.toJSONString(list);
        System.out.println(by);
        System.out.println(data);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(data);
    }

    //数量
    private void getTotalusers(HttpServletRequest request, HttpServletResponse response) {
        try {
            int product = ivisitservlce.getTotalusers();
            String data = JSON.toJSONString(product);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加记录
    private void addpr(HttpServletRequest request, HttpServletResponse response) {
        Sale sale = new Sale();
        sale.setQuanntity(Integer.parseInt(request.getParameter("num")));// 数量
        sale.setProductID(Integer.parseInt(request.getParameter("name")));// 商品id
        sale.setUserID(Integer.parseInt(request.getParameter("realName")));// 销售员id
        sale.setPrice(Double.parseDouble(request.getParameter("price")));// 单价
        sale.setTotalPrice(sale.getPrice() * 2);// 总价格
        try {
            // 获取密码和用户名
            BaseDAO baseDAO = new BaseDAO();
            try {
                baseDAO.getConnection().setAutoCommit(false);
                boolean fag = ivisitservlce.addsale(sale);
                if (fag) {
                    response.getWriter().write("true");
                    baseDAO.con.commit();
                } else {
                    baseDAO.con.rollback();
                    response.getWriter().write("false");
                }
            } catch (Exception e) {
                baseDAO.con.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询商品
    private void selectsale(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Product> product = ivisitservlce.selectsale();
            String data = JSON.toJSONString(product);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //登陆
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Uswes uswes = new Uswes();
        uswes.setUserName(request.getParameter("username"));// 用户名
        uswes.setPassword(request.getParameter("password"));// 密码
        try {
            // 获取密码和用户名
            Uswes gere = ivisitservlce.isLogin(uswes);
            System.out.println(gere.getRealName());
            if (null != gere.getRealName()) {
                request.getSession().setAttribute("upwdname", gere);
                response.getWriter().write("true");
            } else {
                response.getWriter().write("false");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
