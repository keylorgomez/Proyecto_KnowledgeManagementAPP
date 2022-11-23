package controlador.proyecto;

import java.io.*;

public class DocumentoController {
    private String rutaNombre;

    public DocumentoController() {
    }

    public static void crearDocumento(String fechaModificacion, String fechaInicial, String ruta, String categoria, String tema, String autor, String titulo,
                                      String subTitulo, String contenido ){
        String  rutaNombre = ruta+"\\"+titulo+".rtf";
        File documento = new File(rutaNombre);
        try {
            PrintWriter salida = new PrintWriter(documento);
            salida.println("Última modificación: "+fechaModificacion);
            salida.println("Inicio de Investigación :"+fechaInicial);
            salida.println("Categoria: "+ categoria);
            salida.println("Tema:"+ tema);
            salida.println("Autor: "+autor+"\n");
            salida.println("\t\tTítulo: "+titulo+"\n");
            salida.println("\tsubTitulo"+subTitulo+"\n");
            salida.println(contenido+"\n");

            salida.close();
            System.out.println("Se ha creado el archivo.");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void agregarInformacion(String nombreDocumento, String subtitulo, String contenido){
        File documento =  new File(nombreDocumento);
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(documento, true));
            salida.println(contenido);
            salida.close();
            System.out.println("Se ha anexado información al archivo.");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}// fin de la clase

