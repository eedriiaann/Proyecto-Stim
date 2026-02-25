/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdibeans;

import ejbs.UserLocal;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import modelo.User;

/**
 *
 * @author edria
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private UserLocal userEJB;

    private User userActual;

    public UserBean() {
    }

    public void updateProfile() {
        if (userActual != null) {
            userEJB.editUser(userActual);

            userActual = userEJB.findByEmail(userActual.getEmail());

        }
    }

    public List<User> findAll() {
        return (List<User>) userEJB.findAll();
    }

    public User getUserActual() {
        return userActual;
    }

    public void setUserActual(User userActual) {
        this.userActual = userActual;
    }

}
