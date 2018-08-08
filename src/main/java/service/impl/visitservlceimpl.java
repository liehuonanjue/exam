package service.impl;

import dao.IvisitDAO;
import dao.impl.visitimpl;
import entity.Product;
import entity.Sale;
import entity.SaleMould;
import entity.Uswes;
import org.junit.jupiter.api.Test;
import service.Ivisitservlce;

import java.util.List;

public class visitservlceimpl implements Ivisitservlce {
    IvisitDAO iv = new visitimpl();

    @Test
    public void test() throws Exception {
        List<Product> gere = selectsale();
        for (Product ger : gere) {
            System.out.print(ger.getProductName());
        }
    }

    @Override
    public Uswes isLogin(Uswes uswes) throws Exception {
        return iv.isLogin(uswes);
    }

    @Override
    public List<Product> selectsale() throws Exception {
        return iv.selectsale();
    }

    @Override
    public boolean addsale(Sale sale) throws Exception {
        return iv.addsale(sale);
    }

    @Override
    public List<SaleMould> getOnePage(int pageIndex, int pageSize, String by) throws Exception {
        return iv.getOnePage(pageIndex, pageSize, by);
    }


    @Override
    public int getTotalusers() throws Exception {
        return iv.getTotalusers();
    }
}
