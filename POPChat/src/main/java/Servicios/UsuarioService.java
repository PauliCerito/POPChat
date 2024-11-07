package Servicios;

import Modelo.Usuario;
import Persistencia.UsuarioJpaController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Usuario
 */
public class UsuarioService {
     private UsuarioJpaController usuarioJpa = new UsuarioJpaController();

    public boolean registrarUsuario(String apodo, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) return false;

        String hashedPassword = hashPassword(password);
        Usuario usuario = new Usuario(apodo, email, hashedPassword);
        usuarioJpa.create(usuario);
        return true;
    }

    public Usuario iniciarSesion(String email, String password) {
        Usuario usuario = usuarioJpa.findUsuarioByEmail(email);
        if (usuario != null && usuario.getPassword().equals(hashPassword(password))) {
            return usuario;
        }
        return null;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

