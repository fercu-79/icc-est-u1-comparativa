public class Results {

    private String nombre;
    private long duracion;
    private String sample;

    public Results(String nombre, long duracion, String sample) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.sample = sample;
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public long getDuracion() {
        return duracion;
    }


    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }


    public String getSample() {
        return sample;
    }


    public void setSample(String sample) {
        this.sample = sample;
    }

    @Override
    public String toString() {
        double duracionSegundos = duracion / 1_000_000_000.0;
        return "Results [" + nombre + ", duration=" + duracion + "ns, " + duracionSegundos + "s, sample=" + sample + "]";
    }
    



}
