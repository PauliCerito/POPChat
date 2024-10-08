package Servlets;

import Modelo.Consulta;
import Servicios.ConsultaService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    ConsultaService consultaServ = new ConsultaService();
    
    String prompt = request.getParameter("pregunta");
    
    String categoria = request.getParameter("categoria");

    if (prompt != null && !prompt.trim().isEmpty()) {
        request.setAttribute("pregun", prompt);

        consultaServ.mandarPregunta(prompt, categoria);  

        List<Consulta> consu = consultaServ.traerRespuesta(); 
        request.setAttribute("respuesta", consu);
    } else {
        request.setAttribute("pregun", null);
        request.setAttribute("respuesta", null);
    }
    
    request.getRequestDispatcher("paginaPrincipalPOPChat.jsp").forward(request, response);
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
