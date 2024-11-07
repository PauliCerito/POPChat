package Servlets;

import Modelo.Usuario;
import Servicios.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "SVIniciarSesion", urlPatterns = {"/IniciarSesion"})
public class SVIniciarSesion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UsuarioService usuarioServicio = new UsuarioService();
        
        Usuario usuario = usuarioServicio.iniciarSesion(email, password);
        HttpSession session = request.getSession();

        if (usuario != null) {
            // Si el inicio de sesión es exitoso, se almacena el usuario en la sesión
            session.setAttribute("usuario", usuario);
        } else {
            // Si el inicio de sesión falla, se establece un mensaje de error
            request.setAttribute("loginError", "Credenciales inválidas. Por favor intenta de nuevo.");
        }

        // Redirigir de nuevo a paginaPrincipalPOPChat.jsp para mostrar el mensaje en el modal
        request.getRequestDispatcher("paginaPrincipalPOPChat.jsp").forward(request, response);   
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
