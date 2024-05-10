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
    /**
     * Constructor de la clase contacto
     * 
     * @param nombre variable que guarda el nombre del contacto
     * @param apellido variable que guarda el apellido del contacto
     * @param correo variable que guarda el correo del contacto
     */
    public Contacto(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    /**
     * Getter de nombre
     * @return devuelve el atributo nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Getter de apellido
     * @return devuelve el atributo apellido
     */
    public String getApellido() {
        return apellido;
    }
    /**
     * Getter de correo
     * @return devuelve el atributo correo
     */
    public String getCorreo() {
        return correo;
    }
    
}
