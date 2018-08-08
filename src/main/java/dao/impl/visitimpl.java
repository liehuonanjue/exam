package dao.impl;

import com.sun.org.apache.xml.internal.security.Init;
import dao.BaseDAO;
import dao.IvisitDAO;
import entity.Product;
import entity.Sale;
import entity.SaleMould;
import entity.Uswes;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class visitimpl extends BaseDAO implements IvisitDAO {
    @Test
    public void test() throws Exception {
 /*       Uswes uswes = new Uswes();
        uswes.setUserName("123");
        uswes.setPassword("12");
        System.out.println(isLogin(uswes).getRealName());*/
        List<SaleMould> gere = getOnePage(1, 5, "price");
        for (SaleMould ger : gere) {
            System.out.println(ger.getId() + " " + ger.getTotalPrice() + " " + ger.getQuantity() + " " + ger.getSaleDate() + " " + ger.getUser());
        }
/*
        Sale sale = new Sale();
        sale.setUserID(2);
        sale.setPrice(20);
        sale.setProductID(2);
        sale.setQuanntity(222);
        sale.setTotalPrice(50);
        System.out.println(addsale(sale));*/
//        System.out.println(getTotalusers());
    }

    @Override//登陆
    public Uswes isLogin(Uswes uswes) throws Exception {
        Uswes list = new Uswes();
        String sql = "SELECT * FROM users WHERE userName=? AND PASSWORD=?";
        Object[] paras = {uswes.getUserName(), uswes.getPassword()};
        ResultSet rs = executeQuery(sql, paras);
        if (rs != null) {
            while (rs.next()) {
                list.setRealName(rs.getString("realName"));
                list.setId(rs.getInt("id"));
                list.setUserName(rs.getString("userName"));
                list.setPassword(rs.getString("password"));
            }
        }
        return list;
    }

    @Override
    public List<Product> selectsale() throws Exception {
        List<Product> list = new ArrayList<Product>();
        String sql = "SELECT * FROM product ";
        ResultSet rs = executeQuery(sql);
        while (rs.next()) {
            Product product = new Product();
            product.setProductName(rs.getString("productName"));
            product.setId(rs.getInt("id"));
            product.setQuanntiy(rs.getInt("quantity"));
            list.add(product);
        }
        return list;
    }

    @Override
    public boolean addsale(Sale sale) throws Exception {
        boolean flag = false;// 判断是否添加成功

        String sql = "INSERT INTO sale(`price`,quantity,totalPrice,saleDate,userId,producId)\n" +
                " VALUES(?,?,?,NOW(),?,?)";
        Object[] paras = {sale.getPrice(), sale.getQuanntity(), sale.getTotalPrice(), sale.getUserID(), sale.getProductID()};
        int count = executeUpdate(sql, paras);
        if (count > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SaleMould> getOnePage(int pageIndex, int pageSize, String by) throws Exception {
        List<SaleMould> list = new ArrayList<SaleMould>();
        String sql = "SELECT sale.*,product.productName,users.realName \n" +
                "FROM sale,product,users WHERE users.id=sale.userId AND product.id=sale.producId  ORDER BY " + by + "  LIMIT ?,? ";
        Object[] paras = {(pageIndex - 1) * pageSize, pageSize};
        ResultSet rs = executeQuery(sql, paras);
        while (rs.next()) {
            SaleMould sale = new SaleMould();
            sale.setId(rs.getInt("id"));
            sale.setPrice(rs.getInt("price"));
            sale.setQuantity(rs.getInt("quantity"));
            sale.setTotalPrice(rs.getInt("totalPrice"));
            sale.setSaleDate(rs.getDate("saleDate"));
            sale.setUser(rs.getString("realName"));
            sale.setProduct(rs.getString("productName"));
            list.add(sale);
        }
        return list;
    }

    @Override
    public int getTotalusers() throws Exception {
        int totalNum = 0; // 返回值
        String sql = "SELECT COUNT(1) AS COUNT FROM sale";
        ResultSet rs = executeQuery(sql);
        if (rs.next()) { // 判断有多少数据
            totalNum = rs.getInt("count");
        }
        return totalNum;
    }

/*    public String isLoginn(Uswes uswes) throws Exception {
        String setRealName = "";
        String sql = "SELECT realName FROM users WHERE userName=? AND PASSWORD=?";
        Object[] paras = {uswes.getUserName(), uswes.getPassword()};
        ResultSet rs = executeQuery(sql, paras);
        if (rs != null) {
            while (rs.next()) {
                setRealName = rs.getString("realName");
            }
        }
        return setRealName;
    }*/
}
