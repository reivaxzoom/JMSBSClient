package elte.client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.FastMoney;

/**
 *Represents the set of items to be requested to any store 
 *
 * @author Xavier
 */
public class ShoppingCart extends ArrayList<Item> implements Serializable {
private static final long serialVersionUID = 1L;
    Locale spain = new Locale("ca", "ES");
    CurrencyUnit eur = Monetary.getCurrency(spain);

    Map<String,String> reqDataObj;

    @Override
    public boolean add(Item e) {
        if (contains(e)) {
            Item st = get(indexOf(e));
            st.setAmount(st.getAmount() + 1);
            remove(e);
            return super.add(st);
        } else {
            return super.add(e);
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Item) {
            Item it = (Item) o;
            if (contains(it) && it.getAmount() > 1) {
                Item st = get(indexOf(o));
                st.setAmount(st.getAmount() - 1);
                return remove(o);
            }
        }
        return false;
    }

    public FastMoney getTotal() {
        FastMoney subTotal;
        FastMoney total = FastMoney.of(BigDecimal.ZERO, eur);
        for (Item it : this) {
            subTotal = FastMoney.of(it.getUnityPrice(), eur).multiply(it.getAmount());
            total = total.add(subTotal);
        }
        return total;
    }

    
    
    public Map<String, String> getReqDataObj() {
        return reqDataObj;
    }

    public void setReqDataObj(Map<String, String> reqDataObj) {
        this.reqDataObj = reqDataObj;
    }
    
//    public void addSampleItems() {
//        add(new ClientRequest("Yoghurt", 1, Money.of(5.94, eur).getNumber()));
//        add(new ClientRequest("Mango", 2, Money.of(0.75, eur).getNumber()));
//        add(new ClientRequest("Pineapple", 8, Money.of(0.69, eur).getNumber()));
//        add(new ClientRequest("Oranges", 6, Money.of(5.01, eur).getNumber()));
//    }
}
