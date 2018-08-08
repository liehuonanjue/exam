package service;

import entity.Product;
import entity.Sale;
import entity.SaleMould;
import entity.Uswes;

import java.util.List;

public interface Ivisitservlce {
    public Uswes isLogin(Uswes uswes) throws Exception;

    public List<Product> selectsale() throws Exception;

    public boolean addsale(Sale sale) throws Exception;

    public List<SaleMould> getOnePage(int pageIndex, int pageSize, String by) throws Exception;

    public int getTotalusers() throws Exception;
}
