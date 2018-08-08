package dao;

import entity.Product;
import entity.Sale;
import entity.SaleMould;
import entity.Uswes;

import java.util.List;

public interface IvisitDAO {
    public Uswes isLogin(Uswes uswes) throws Exception;

    public List<Product> selectsale() throws Exception;

    public boolean addsale(Sale sale) throws Exception;

    // 分页数据查询
    public List<SaleMould> getOnePage(int pageIndex, int pageSize, String by) throws Exception;

    // 获取有多少数据
    public int getTotalusers() throws Exception;

}
