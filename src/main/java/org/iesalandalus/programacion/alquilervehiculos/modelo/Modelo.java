package org.iesalandalus.programacion.alquilervehiculos.modelo;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Vehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public class Modelo {

    private Clientes clientes;
    private Alquileres alquileres;
    private Vehiculos turismos;

    // CONSTRUCTOR
    public Modelo() {

    }

    // MÉTODO comenzar
    public void comenzar() throws Exception {
        clientes = new Clientes();
        alquileres = new Alquileres();
        turismos = new Vehiculos();
    }

    // MÉTODO terminar
    public void terminar() {
        System.out.println("El modelo ha finalizado.");
    }

    // MÉTODO insertar cliente
    public void insertar(Cliente cliente) throws Exception {
        clientes.insertar(cliente);
    }

    // MÉTODO insertar turismo
    public void insertar(Vehiculo turismo) throws Exception {
        turismos.insertar(turismo);
    }

    // MÉTODO insertar alquiler
    public void insertar(Alquiler alquiler) throws Exception {
        if (alquiler == null) {
            throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
        }
        alquileres.insertar(alquiler);
    }

    // MÉTODO buscar cliente
    public Cliente buscar(Cliente cliente) {
        return clientes.buscar(cliente);
    }

    // MÉTODO buscar turismo
    public Vehiculo buscar(Vehiculo turismo) {
        return turismos.buscar(turismo);
    }

    // MÉTODO buscar alquiler
    public Alquiler buscar(Alquiler alquiler) {
        return alquileres.buscar(alquiler);
    }

    // MÉTODO modificar
    public void modificar(Cliente cliente, String nombre, String Telefono) throws Exception {
        clientes.modificar(cliente, nombre, Telefono);
    }

    // MÉTODO devolver
    public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws Exception {
        if (alquileres.buscar(alquiler) == null) {
            throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
        } else {
            alquileres.devolver(alquiler, fechaDevolucion);
        }
    }

    // MÉTODO borrar cliente
    public void borrar(Cliente cliente) throws Exception {
        clientes.borrar(cliente);
    }

    // MÉTODO borrar turismo
    public void borrar(Vehiculo turismo) throws Exception {
        turismos.borrar(turismo);
    }

    // MÉTODO borrar alquiler
    public void borrar(Alquiler alquiler) throws Exception {
        alquileres.borrar(alquiler);
    }

    // MÉTODO getClientes
    public List<Cliente> getClientes() {
        return clientes.get();
    }

    // MÉTODO getTurismos
    public List<Vehiculo> getTurismos() {
        return turismos.get();
    }

    // MÉTODO getAlquileres
    public List<Alquiler> getAlquileres() {
        return alquileres.get();
    }

    // MÉTODO getAlquileres cliente
    public List<Alquiler> getAlquileres(Cliente cliente) {
        return alquileres.get(cliente);
    }

    // MÉTODO getAlquileres turismo
    public List<Alquiler> getAlquileres(Vehiculo turismo) {
        return alquileres.get(turismo);
    }

}
