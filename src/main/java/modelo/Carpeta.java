package modelo;

public class Carpeta {
    public int idCarpeta;

    public String investigacion;

    public String media;

    public int IDProyecto;

    public Carpeta(int idCarpeta, String investigacion, String media, int idProyecto) {
        this.idCarpeta = idCarpeta;
        this.investigacion = investigacion;
        this.media = media;
    }

    public Carpeta(String investigacion, String media, int idProyecto) {
        this.investigacion = investigacion;
        this.media = media;
        this.IDProyecto = idProyecto;
    }

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
        return IDProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.IDProyecto = idProyecto;
    }
}
