package org.example;

import java.util.*;

public class ListaClientes {

    private List<Cliente> listaClientes;

    public ListaClientes() {
        listaClientes = new ArrayList<>();;
    }

    public void agregarCliente (Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void eliminarCliente(Cliente cliente) {
        listaClientes.remove(cliente);
    }

    public void eliminarClientes() {
        listaClientes.clear();
    }

    public Cliente buscarCliente(String nombre) {
        boolean encontrado = false;
        Cliente cliente = null;
        for (int i=0; i< listaClientes.size() && !encontrado; i++){
            if (listaClientes.get(i).getNombre().equals(nombre)){
                cliente=listaClientes.get(i);
            }
        }
        return cliente;
    }

    public String toString() {
        return listaClientes.toString();
    }

}
