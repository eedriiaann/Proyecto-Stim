package com.ddi.loginxperiment.servicio;

import com.ddi.loginxperiment.dto.AutorizacionDTO;
import com.ddi.loginxperiment.dto.UsuarioDTO;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
public class AutorizacionService {

    private static final String LINK = "http://localhost:8080/Stim-WEB/api/auth/login";

    public AutorizacionDTO login(String nombre, String contrasena) {

        Client cliente = ClientBuilder.newClient();

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setNombre(nombre);
        usuarioDTO.setContrasena(contrasena);

        Response respuesta = null;

        try {
            respuesta = cliente.target(LINK)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(usuarioDTO, MediaType.APPLICATION_JSON));

            if (respuesta.getStatus() == 200) {
                return respuesta.readEntity(AutorizacionDTO.class);
            } else {
                return new AutorizacionDTO(false, nombre, null);
            }
        } finally {
            if (respuesta != null) {
                respuesta.close();
            }
            cliente.close();
        }
    }

}
