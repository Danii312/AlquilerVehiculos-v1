package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Alquileres {

    private List<Alquiler> coleccionAlquileres;

    // CONSTRUCTOR
    public Alquileres() {
        coleccionAlquileres = new ArrayList<>();
    }

    // MÉTODO get
    public List<Alquiler> get() {
        ArrayList<Alquiler> copiaAlquileres = new ArrayList<>(coleccionAlquileres);
        return copiaAlquileres;
    }

    // MÉTODO get cliente
    public List<Alquiler> get(Cliente cliente) {
        ArrayList<Alquiler> alquilerCliente = new ArrayList<>();
        Iterator<Alquiler> alquilerIterator = coleccionAlquileres.iterator();
        while (alquilerIterator.hasNext()) {
            Alquiler clienteAlquiler = alquilerIterator.next();
            if (clienteAlquiler.getCliente().equals(cliente)) {
                alquilerCliente.add(clienteAlquiler);
            }
        }
        return alquilerCliente;
    }

    // MÉTODO get turismo
    public List<Alquiler> get(Vehiculo turismo) {
        ArrayList<Alquiler> alquilerTurismo = new ArrayList<>();
        Iterator<Alquiler> alquilerIterator = coleccionAlquileres.iterator();
        while (alquilerIterator.hasNext()) {
            Alquiler turismoAlquiler = alquilerIterator.next();
            if (turismoAlquiler.getVehiculo().equals(turismo)) {
                alquilerTurismo.add(turismoAlquiler);
            }
        }
        return alquilerTurismo;
    }

    // MÉTODO getCantidad
    public int getCantidad() {
        return coleccionAlquileres.size();
    }

    // MÉTODO insertar
    public void insertar(Alquiler alquiler) throws Exception {
        if (alquiler == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
        }
        if (comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler())) {
            coleccionAlquileres.add(new Alquiler(alquiler));
        }
    }

    // MÉTODO comprobarAlquiler
    private boolean comprobarAlquiler(Cliente cliente, Vehiculo turismo, LocalDate fechaAlquiler) throws Exception {
        boolean estadoAlquiler = true;
        Iterator<Alquiler> alquilerIterador = coleccionAlquileres.iterator();
        while (alquilerIterador.hasNext()) {
            Alquiler alquiler2 = alquilerIterador.next();
            if (alquiler2.getFechaDevolucion() == null) {
                if (alquiler2.getCliente().equals(cliente)) {
                    estadoAlquiler = false;
                    throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
                } else if (alquiler2.getVehiculo().equals(turismo)) {
                    estadoAlquiler = false;
                    throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
                }
            } else {
                if (fechaAlquiler.isEqual(alquiler2.getFechaDevolucion())) {
                    if (alquiler2.getCliente().equals(cliente)) {
                        estadoAlquiler = false;
                        throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
                    } else if (alquiler2.getVehiculo().equals(turismo) && !alquiler2.getFechaAlquiler().isAfter(fechaAlquiler)) {
                        estadoAlquiler = false;
                        throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
                    }
                }
            }
        }
        return estadoAlquiler;
    }

    // MÉTODO devolver
    public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
        boolean estadoAlquiler = false;
        if (alquiler == null || fechaDevolucion == null) {
            throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
        }
        for (Alquiler alquiler2 : coleccionAlquileres) {
            if (alquiler2.equals(alquiler)) {
                alquiler2.devolver(fechaDevolucion);
            }
            estadoAlquiler = true;
        }
        if (estadoAlquiler == false) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
        }
    }

    // MÉTODO borrar
    public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
        if (alquiler == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
        }
        if (coleccionAlquileres.contains(alquiler)) {
            coleccionAlquileres.remove(alquiler);
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
        }
    }

    // MÉTODO buscar
    public Alquiler buscar(Alquiler alquiler) {
        Alquiler alquiler2 = null;
        Iterator<Alquiler> iterator = coleccionAlquileres.iterator();
        while (iterator.hasNext()) {
            alquiler2 = iterator.next();
            if (alquiler2.getCliente().getDni().equals(alquiler.getCliente().getDni()) || alquiler2.getVehiculo().getMatricula().equals(alquiler.getVehiculo().getMatricula())) {
                return alquiler2;
            }
        }
        if (alquiler == null) {
            throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
        }
        return null;
    }

}
