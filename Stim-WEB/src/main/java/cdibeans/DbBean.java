/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdibeans;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author MULTI
 */
@Named(value = "dbBean")
@SessionScoped
public class DbBean implements Serializable {
//Este bean tendria que conectarse un EJB con la tabla de precios pero no me ha dado tiempo a implementarlo, pero sirve la idea esta ahi

    boolean priceHistory = false;

    public DbBean() {
    }

    public boolean priceHistory() {
        if (priceHistory) {
            priceHistory = false;
        } else {
            priceHistory = true;
        }
        return priceHistory;
    }

    public boolean isPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(boolean priceHistory) {
        this.priceHistory = priceHistory;
    }

}
