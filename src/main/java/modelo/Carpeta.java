package modelo;

public class Carpeta {
    public int idCarpeta;

    public String investigacion;

    public String media;

    public Carpeta(int idCarpeta, String investigacion, String media) {
        this.idCarpeta = idCarpeta;
        this.investigacion = investigacion;
        this.media = media;
    }

    public Carpeta(String investigacion, String media) {
        this.investigacion = investigacion;
        this.media = media;
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
}
