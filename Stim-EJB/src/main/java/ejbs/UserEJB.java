/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejbs;

import daos.DaoInterfaceGame;
import daos.DaoInterfaceUser;
import jakarta.activation.DataSource;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Game;
import modelo.User;

/**
 *
 * @author MULTI
 */
@Stateless
public class UserEJB implements UserLocal {

    @EJB
    private DaoInterfaceUser userDao;

    @Override
    public void addUser(User user) {
        try {
            userDao.insert(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void editUser(User user) {
        try {
            userDao.update(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            userDao.delete(user.getId());
        } catch (SQLException ex) {
            Logger.getLogger(UserEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Collection<User> findAll() {
        List<User> lista = new ArrayList<>();
        try {
            lista = (List<User>) userDao.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(GameEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public Optional<User> findById(Long userId) {
        try {
            return userDao.findById(userId);
        } catch (SQLException ex) {
            Logger.getLogger(UserEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        try {
            return userDao.findByEmail(email);
        } catch (SQLException ex) {
            Logger.getLogger(UserEJB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
