package model;

import java.util.LinkedList;

public class Titulo {
    private int cantVisualizaciones;
    private int cantVisualizacionesDia;
    private String tipoContenido;
    private LinkedList<String> generos;
    private LinkedList<String> paisesDisponible;
    private metadatos Metadatos;
    private Resenia LR;

    // Constructor que inicializa las listas y objetos
    public Titulo() {
        this.generos = new LinkedList<>();
        this.paisesDisponible = new LinkedList<>();
        this.Metadatos = new metadatos();
        this.LR = new Resenia();
    }


    public Resenia getListasyResenias() {
        return LR;
    }

    public void setListasyResenias(Resenia lR) {
        LR = lR;
    }

    public void setCantVisualizaciones(int cantVisualizaciones) {
        this.cantVisualizaciones = cantVisualizaciones;
    }

    public metadatos getMetadatos() {
        return Metadatos;
    }

    public void setMetadatos(metadatos metadatos) {
        Metadatos = metadatos;
    }

    public int getCantVisualizaciones() {
        return cantVisualizaciones;
    }

    public void setCantVisualizacionesDia(int cantVisualizacionesDia) {
        this.cantVisualizacionesDia = cantVisualizacionesDia;
    }

    public int getCantVisualizacionesDia() {
        return cantVisualizacionesDia;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void anadirGeneros(String genero) {
        this.generos.add(genero);
    }

    public LinkedList<String> getGeneros() {
        return generos;
    }

    public void anadirPaisesDisponible(String pais) {
        this.paisesDisponible.add(pais);
    }

    public void eliminarPaisesDisponibles(String pais) {
        this.paisesDisponible.remove(pais);
    }

    public LinkedList<String> getPaisesDisponible() {
        return paisesDisponible;
    }

    public void agregarAMiLista() {
        // Lógica para agregar a la lista de un usuario
    }

    public void reproducirContenido() {
        // Lógica para reproducir el contenido
    }

}
