package Servicios;

import Modelo.Consulta;
import Modelo.Usuario;
import Persistencia.ConsultaJpaController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.persistence.EntityManager;
import org.json.JSONObject;

public class ConsultaService {

    private static final String BASE_URL = "http://localhost:11434/api/generate";
    private ConsultaJpaController jpaControl = new ConsultaJpaController();
    
   
        public Consulta mandarPregunta(String prompt, String categoria) {
        private Consulta consulta = new Consulta();
        consulta.setPregunta(prompt);
        consulta.setCategoria(categoria);
        String profesion = "";
        switch (categoria.toLowerCase()) {
            case "cocinero":
                profesion = "cocinero profesional";
                break;
            case "plomero":
                profesion = "plomero profesional";
                break;
            case "electricista":
                profesion = "electricista profesional";
                break;
            default:
                profesion = "profesional";
                break;
        }

        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{ \"model\": \"orca-mini:3b\", \"prompt\": \"Ahora eres un " 
                                  + profesion + ". Responde en español la siguiente pregunta: " + prompt + "\", \"stream\": false }";

            // Enviar datos
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer respuesta
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                // Extraer solo la respuesta de la API
                JSONObject jsonResponse = new JSONObject(response.toString());
                String respuesta = jsonResponse.getString("response");
                consulta.setRespuesta(respuesta);
                
            }
        } catch (IOException e) {
            consulta.setRespuesta("Error al obtener la respuesta.");
        }

        // Guardar la consulta en la base de datos
        jpaControl.create(consulta);

        // Retornar la consulta completa, incluyendo la respuesta
        return consulta;
    }


    // Método para traer todas las respuestas almacenadas en la base de datos
    public List<Consulta> traerRespuesta() {
        return jpaControl.findConsultaEntities();
    }

    // Método para obtener el historial de consultas de un usuario específico
    public List<Consulta> obtenerHistorialPorUsuario(Usuario usuario) {
        EntityManager em = jpaControl.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Consulta c WHERE c.usuario = :usuario", Consulta.class)
                     .setParameter("usuario", usuario)
                     .getResultList();
        } finally {
            em.close();
        }
    }
    
}
