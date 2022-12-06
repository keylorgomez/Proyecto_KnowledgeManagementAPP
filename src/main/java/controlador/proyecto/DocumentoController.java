package controlador.proyecto;

import java.io.*;
import java.util.StringTokenizer;

public class DocumentoController {

    public DocumentoController() {
    }

    public static File crearDocumento(String fechaModificacion, String fechaInicial, String ruta, String categoria, String tema, String autor, String titulo,
                                      String contenido1, String subTitulo, String contenido2){
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
            salida.println(contenido1+"\n");
            salida.println("\tsubTitulo"+subTitulo+"\n");
            salida.println(contenido2);
            salida.close();
            System.out.println("Se ha creado el archivo.");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        return documento;
    }

    public static int contarPalabras(File docInvestigacion) throws IOException {
        int palabrasTotales=0;
        File documento = new File(docInvestigacion.toURI());
        try {
            if (documento.exists()){
                BufferedReader archivoLeer = new BufferedReader(new FileReader(documento));
                String lineaLeida;

                while ((lineaLeida = archivoLeer.readLine()) != null){
                    System.out.println(lineaLeida);
                    // contamos las palabras
                    StringTokenizer st = new StringTokenizer(lineaLeida);
                    palabrasTotales= palabrasTotales + st.countTokens();
                }
                System.out.println("\nPalabras totales es: "+ palabrasTotales);
                archivoLeer.close();
            }else {
                System.out.println("no se encuetra archivo");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
        return palabrasTotales;
    }// fin de contar palabras

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

