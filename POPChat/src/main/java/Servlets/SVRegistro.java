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

/**
 *
 * @author Usuario
 */
@WebServlet(name = "SVRegistro", urlPatterns = {"/Registro"})
public class SVRegistro extends HttpServlet {

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
        // Obtener los parámetros del formulario
        String apodo = request.getParameter("apodo");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Llamar al método de registro
        
        UsuarioService usuarioServicio = new UsuarioService(); 
        boolean registroExitoso = usuarioServicio.registrarUsuario(apodo, email, password, confirmPassword);
        
        
        if (registroExitoso) {
            // Mensaje de éxito para el usuario
            request.setAttribute("registroExitoso", "Registro exitoso. Ahora puedes iniciar sesión.");
        } else {
            // Mensaje de error si las contraseñas no coinciden o hay un problema
            request.setAttribute("registroError", "Error al registrar el usuario. Verifica tus datos.");
        }

        // Redirigir de nuevo a paginaPrincipalPOPChat.jsp para mostrar el mensaje en el modal
        request.getRequestDispatcher("paginaPrincipalPOPChat.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
