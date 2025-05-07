package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        ListaClientes lista = new ListaClientes();
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        cliente1.setEdad(20);
        cliente1.setNombre("Luis");
        cliente1.setId(333);
        cliente1.setTelefono("95233455");
        lista.agregarCliente(cliente1);
        cliente2.setEdad(50);
        cliente2.setNombre("MAria");
        cliente2.setId(444);
        cliente2.setTelefono("95233111");
        lista.agregarCliente(cliente2);
        System.out.println(lista.toString());

    }
}