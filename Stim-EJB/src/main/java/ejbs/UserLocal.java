/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejbs;

import jakarta.ejb.Local;
import java.util.Collection;
import java.util.Optional;
import modelo.User;

/**
 *
 * @author MULTI
 */
@Local
public interface UserLocal {

    public void addUser(User user);

    public void editUser(User user);

    public void deleteUser(User user);

    public Collection<User> findAll();

    public Optional<User> findById(Long userId);

    public User findByEmail(String email);
}
