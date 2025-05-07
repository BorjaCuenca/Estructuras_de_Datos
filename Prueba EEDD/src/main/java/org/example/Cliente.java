package org.example;

import java.util.Objects;

public class Cliente {
    private String nombre;
    private String apellido;
    private int id;
    private int edad;
    private String telefono;

    public Cliente(String nombre, String apellido, int id, int edad, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return getId() == cliente.getId() && getEdad() == cliente.getEdad() && Objects.equals(getNombre(), cliente.getNombre()) && Objects.equals(getApellido(), cliente.getApellido()) && Objects.equals(getTelefono(), cliente.getTelefono());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getApellido(), getId(), getEdad(), getTelefono());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
