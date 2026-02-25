package com.ddi.loginxperiment.beans;

import cdibeans.UserBean;
import com.ddi.loginxperiment.dto.AutorizacionDTO;
import com.ddi.loginxperiment.dto.UsuarioDTO;
import com.ddi.loginxperiment.servicio.AutorizacionService;
import ejbs.UserEJB;
import ejbs.UserLocal;
import modelo.User;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("beanForm")
@SessionScoped
public class FormularioBean implements Serializable {

    @EJB
    AutorizacionService servicioAutorizacion;
    @EJB
    UserLocal userEJB;
    @Inject
    private UserBean userBean;

    private UsuarioDTO usuarioDTO;
    private User nuevoUsuario;
    private String rol;
    private boolean logeado;
    private boolean isAdmin;
    private String confirmarPassword;

    @PostConstruct
    public void init() {
        usuarioDTO = new UsuarioDTO();
        nuevoUsuario = new User();
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            User userCheck = userEJB.findByEmail(usuarioDTO.getNombre());

            if (userCheck == null) {
                context.addMessage("formLogin:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El email introducido no existe."));
                return null;
            }

            AutorizacionDTO auth = servicioAutorizacion.login(usuarioDTO.getNombre(), usuarioDTO.getContrasena());

            if (auth != null && auth.isLogeado()) {
                this.logeado = true;
                this.rol = auth.getRol();
                userBean.setUserActual(userCheck);

                if ("ADMIN".equalsIgnoreCase(rol)) {
                    isAdmin = true;
                    return "manager";
                } else {
                    return "home";
                }
            } else {
                context.addMessage("formLogin:password", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña es incorrecta."));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de sistema", "No se pudo conectar con el servicio."));
            System.err.println("Error login:" + e);
        }
        return null;
    }

    public String logout() {
        jakarta.faces.context.FacesContext.getCurrentInstance()
                .getExternalContext().invalidateSession();
        usuarioDTO = new UsuarioDTO();
        isAdmin = false;
        this.logeado = false;
        return "/index.xhtml?faces-redirect=true";
    }

    public String registrar() {
        try {
            if (!nuevoUsuario.getPassword().equals(confirmarPassword)) {
                FacesContext.getCurrentInstance().addMessage("formRegistro:confirmarPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden."));
                return null;
            }

            if (nuevoUsuario.getName() == null) {
                nuevoUsuario.setName(nuevoUsuario.getEmail().split("@")[0]);
            }

            userEJB.addUser(nuevoUsuario);
            nuevoUsuario = new User();
            usuarioDTO = new UsuarioDTO();
            return "index?faces-redirect=true";

        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public AutorizacionService getServicioAutorizacion() {
        return servicioAutorizacion;
    }

    public void setServicioAutorizacion(AutorizacionService servicioAutorizacion) {
        this.servicioAutorizacion = servicioAutorizacion;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public User getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(User nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

}
