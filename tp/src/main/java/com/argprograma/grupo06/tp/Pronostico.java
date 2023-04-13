package tp;

public class Pronostico {
    final private int PUNTOS_X_ACERTAR = 1;
    
    private int idPronostico;
    private Equipo equipo;
    private Partido partido;
    private char resultado;
    
    // Constructors
    public Pronostico(int idPronostico, Equipo equipo, Partido partido, char resultado) {
        this.idPronostico = idPronostico;
        this.equipo = equipo;
        this.partido = partido;
        this.resultado = resultado;
    }
    
    // NOTA: el char no se puede inicializar como '' (vacío),
    // esto me obligaría a elegir inicializarlo con un caracter.
    // Entonces, decido no crear el "constructor por defecto",
    // el cual NO se crea automáticamente al definir explícitamente
    // otro constructor.
/*    public Pronostico() {
    this.idPronostico = 0;
    this.equipo = null;
    this.partido = null;
    this.resultado = 'E';  
    }
*/    
    
    // Setters and Getters
    
    public int getIdPronostico() {
        return idPronostico;
    }

    // Setters and Getters
    public void setIdPronostico(int idPronostico) {    
        this.idPronostico = idPronostico;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public char getResultado() {
        return resultado;
    }

    public void setResultado(char resultado) {
        this.resultado = resultado;
    }
    
    // toString()

    @Override
    public String toString() {
        return "Pronostico{" + "idPronostico=" + idPronostico + ", equipo=" + equipo + ", partido=" + partido + ", resultado=" + resultado + '}';
    }
    
    // Métodos
    
    public int puntos() {
        if (partido.resultado(this.equipo) == this.resultado) {
            return this.PUNTOS_X_ACERTAR;
        } else {
            return 0;
        }
    }
    
}
