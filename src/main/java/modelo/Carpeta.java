package modelo;

import java.util.Objects;

public class Carpeta {
    String rutaCarpeta ;
    String nombreCarpeta;

    public Carpeta() {
    }
    public Carpeta(String rutaCarpeta, String nombreCarpeta) {
        this.rutaCarpeta = rutaCarpeta;
        this.nombreCarpeta = nombreCarpeta;
    }

    public String getRutaCarpeta() {
        return rutaCarpeta;
    }

    public void setRutaCarpeta(String rutaCarpeta) {
        this.rutaCarpeta = rutaCarpeta;
    }

    public String getNombreCarpeta() {
        return nombreCarpeta;
    }

    public void setNombreCarpeta(String nombreCarpeta) {
        this.nombreCarpeta = nombreCarpeta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carpeta carpeta = (Carpeta) o;
        return Objects.equals(rutaCarpeta, carpeta.rutaCarpeta) && Objects.equals(nombreCarpeta, carpeta.nombreCarpeta);
    }

    @Override
    public String toString() {
        return "Carpeta{" +
                "rutaCarpeta='" + rutaCarpeta + '\'' +
                ", nombreCarpeta='" + nombreCarpeta + '\'' +
                '}';
    }
}// fin de clase
