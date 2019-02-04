package rna_codigodebarras;

import java.io.File;

public class Imagen {
     public Imagen(String nombre){
        id = _id++;
        this.archivo = new File(nombre);
        this.nombre = nombre.substring(nombre.indexOf('.') - 15, nombre.indexOf('.'));
        
    }
    
    public File getArchivo(){
        return archivo;
    }

    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }
    
    
    private static int _id = 0;
    
    private final File archivo;
    private final int id;
    private final String nombre;
  

    
}
