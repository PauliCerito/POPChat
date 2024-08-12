package Modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consulta{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_Consulta;
    private String respuesta;
    private String pregunta;

    public Consulta(int id_Consulta, String respuesta, String pregunta) {
        this.id_Consulta = id_Consulta;
        this.respuesta = respuesta;
        this.pregunta = pregunta;
    }
    
    public Consulta(){
    }

    public int getId_Consulta() {
        return id_Consulta;
    }

    public void setId_Consulta(int id_Consulta) {
        this.id_Consulta = id_Consulta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
}
