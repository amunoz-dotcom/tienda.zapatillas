package com.adrian.tienda.zapatillas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "tabla_zapatillas")
public class Zapatilla {


    @Size(min = 3, max = 40, message = "titulo debe tener entre 3 y 40 caracteres")
    @NotEmpty(message = "debes insertar un titulo")
    // una expresion regular es una forma de indicar
    // que queremos o no en un campo de entrada de usuario
    @Pattern(regexp = "[a-záéíóúñA-ZÁÉÍÓÚÑüÜ 0-9]+",
            message = "titulo solo puede tener letras numeros y espacios")

    private String modelo;
    private String marca;
    @Min(value = 1, message = "precio minimo 1 euro")
    @Max(value = 99999, message = "el precio maximo es 99999 euros")
    @NotNull(message = "debes indicar un precio")

    private double precio;

    private String color;
    private double talla;
    private String descripcion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //este campo va a ser solo para gestionar la subida de archivo, el input type='file'
    @Transient
    private MultipartFile fotoSubida;

    //el siguiente campo es para almacenar la imagen en base de datos
    @Lob // para tipos de columna, para guardar imagenes/archivos/arrays
    private  byte[] imagenPortada;


    public Zapatilla() {
    }

    public Zapatilla(String modelo, String marca, double precio, String color, double talla, String descripcion) {
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
        this.color = color;
        this.talla = talla;
        this.descripcion = descripcion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MultipartFile getFotoSubida() {
        return fotoSubida;
    }

    public void setFotoSubida(MultipartFile fotoSubida) {
        this.fotoSubida = fotoSubida;
    }

    public byte[] getImagenPortada() {
        return imagenPortada;
    }

    public void setImagenPortada(byte[] imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    @Override
    public String toString() {
        return "Zapatilla{" +
                "modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", color='" + color + '\'' +
                ", talla=" + talla +
                ", descripcion='" + descripcion + '\'' +
                ", id=" + id +
                '}';
    }
}