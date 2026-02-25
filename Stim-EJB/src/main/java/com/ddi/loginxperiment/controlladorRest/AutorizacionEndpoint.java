package com.ddi.loginxperiment.controlladorRest;

import com.ddi.loginxperiment.dto.AutorizacionDTO;
import com.ddi.loginxperiment.dto.UsuarioDTO;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.User;

@Path("/auth")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutorizacionEndpoint {

    @PersistenceContext(unitName = "gamesPU")
    private EntityManager em;

    @POST
    @Path("/login")
    public Response login(UsuarioDTO usuarioDTO) {
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email AND u.password = :pass", User.class);
            query.setParameter("email", usuarioDTO.getNombre());
            query.setParameter("pass", usuarioDTO.getContrasena());

            User user = query.getSingleResult();

            String rolSimulado = user.getEmail().contains("admin") ? "ADMIN" : "USER";

            return Response.ok(new AutorizacionDTO(true, user.getName(), rolSimulado)).build();

        } catch (jakarta.persistence.NoResultException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new AutorizacionDTO(false, usuarioDTO.getNombre(), null)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
    }
}
