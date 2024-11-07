package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_Consulta;
    private String respuesta;
    private String pregunta;
    private String categoria;

    // Constructor con parámetros
    public Consulta(String respuesta, String pregunta, String categoria) {
        this.respuesta = respuesta;
        this.pregunta = pregunta;
        this.categoria = categoria;
    }
    
    // Constructor vacío
    public Consulta() {
    }

    // Getter y Setter para categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    // Getter y Setter para id_Consulta
    public int getId_Consulta() {
        return id_Consulta;
    }

    public void setId_Consulta(int id_Consulta) {
        this.id_Consulta = id_Consulta;
    }

    // Getter y Setter para respuesta
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    // Getter y Setter para pregunta
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    
}
