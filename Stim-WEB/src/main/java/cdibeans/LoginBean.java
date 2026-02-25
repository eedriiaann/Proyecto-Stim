/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdibeans;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author MULTI
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    public LoginBean() {
    }

    public String goLogin() {
        return "index";
    }

    public String goRegister() {
        return "register";
    }

    public String goHome() {
        return "home";
    }

    public String goProfile() {
        return "profile";
    }

    public String goJuego() {
        return "game";
    }

    public String goManager() {
        return "manager";
    }

}
