package modelo;

public class Carpeta {
    /**
     * Atributos de la clase Carpeta
     */
    private int idCarpeta;

    private String investigacion;

    private String media;

    private int idProyecto;

    public Carpeta(int idCarpeta, String investigacion, String media, int idProyecto) {
        this.idCarpeta = idCarpeta;
        this.investigacion = investigacion;
        this.media = media;
        this.idProyecto = idProyecto;
    }

    public Carpeta(String investigacion, String media, int idProyecto) {
        this.investigacion = investigacion;
        this.media = media;
        this.idProyecto = idProyecto;
    }

    /**
     * Constructor Por defecto
     */
    public Carpeta() {
    }


    public int getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(int idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public String getInvestigacion() {
        return investigacion;
    }

    public void setInvestigacion(String investigacion) {
        this.investigacion = investigacion;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
