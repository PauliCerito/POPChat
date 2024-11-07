package Servlets;

import Modelo.Consulta;
import Servicios.ConsultaService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "SVPagPrincipal", urlPatterns = {"/SVPagPrincipal"})
public class SVPagPrincipal extends HttpServlet {
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    ConsultaService consultaServicio = new ConsultaService();
    HttpSession session = request.getSession();

    
    String categoria = (String) session.getAttribute("categoriaSeleccionada");

    // Si la categoría no está en la sesión (primera pregunta), obtenerla del formulario y guardarla en la sesión
    if (categoria == null || categoria.isEmpty()) {
        categoria = request.getParameter("categoria");
        session.setAttribute("categoriaSeleccionada", categoria);
    }
    
    String pregunta = request.getParameter("pregunta");

   

    // Recuperar el historial de la sesión, o crear uno nuevo si no existe
    List<Consulta> historial = (List<Consulta>) session.getAttribute("historial");
    if (historial == null) {
        historial = new ArrayList<>();
    }

    if (pregunta != null && !pregunta.trim().isEmpty()) {
        // Obtener la respuesta de la IA
        Consulta consulta = consultaServicio.mandarPregunta(pregunta, categoria);

        // Agregar la nueva consulta (pregunta y respuesta) al historial
        historial.add(consulta);

        // Actualizar el historial en la sesión
        session.setAttribute("historial", historial);
    } else {
        // Si no se envió una pregunta válida, mostrar mensaje de error
        request.setAttribute("respuesta", "No se envió ninguna pregunta.");
    }

    // Redirigir a la página principal para mostrar los mensajes acumulados en el chat
    request.getRequestDispatcher("paginaPrincipalPOPChat.jsp").forward(request, response);
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
