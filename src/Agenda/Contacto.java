package Agenda;

/**
 * Clase contacto que se encarga de la creacion y guardar informacion de contacto
 * @author Asier Sergio
 * @version 1.0
 */
public class Contacto {
    /**
     * variable que guarda el nombre del contacto
     */
    private String nombre;
    /**
     * variable que guarda el apellido del contacto
     */
    private String apellido;
    /**
     * variable que guarda el correo del contacto
     */
    private String correo;

    public Contacto(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
    
}
