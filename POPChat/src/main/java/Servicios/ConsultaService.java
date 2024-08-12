package Servicios;

import Modelo.Consulta;
import Persistencia.ConsultaJpaController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Usuario
 */
public class ConsultaService {

    private static final String BASE_URL = "http://localhost:11434/api/generate";
    private ConsultaJpaController jpaControl = new ConsultaJpaController();
    
    //Método para enviarle la pregunta del usuario a Ollama
     public void mandarPregunta(String prompt) {
        Consulta consulta = new Consulta();
        consulta.setPregunta(prompt);

        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{ \"model\": \"orca-mini:3b\", \"prompt\": \"" + prompt + "\", \"stream\": false }";

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
            //y guardar solo la respuesta.
            JSONObject jsonResponse = new JSONObject(response.toString());
            String respuesta = jsonResponse.getString("response");
                consulta.setRespuesta(respuesta);
            }
        } catch (IOException e) {
            consulta.setRespuesta("Error al obtener la respuesta.");
        }

        jpaControl.create(consulta);
    }

     //Método para traer la respuesta. Devuelve una lista de con las respuestas
    public List<Consulta> traerRespuesta(){
        return jpaControl.findConsultaEntities();
    }
    
    
}
