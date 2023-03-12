package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vehiculos {

    private List<Vehiculo> coleccionVehiculos;

    // CONSTRUCTOR
    public Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }

    // MÉTODO get
    public List<Vehiculo> get() {
        ArrayList<Vehiculo> copiaVehiculos = new ArrayList<>(coleccionVehiculos);
        return copiaVehiculos;
    }

    // MÉTODO getCantidad
    public int getCantidad() {
        return coleccionVehiculos.size();
    }

    // MÉTODO insertar
    public void insertar(Vehiculo turismo) throws OperationNotSupportedException {
        if (turismo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");
        }
        if ((coleccionVehiculos.contains(turismo))) {
            throw new OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula.");
        }
        coleccionVehiculos.add(turismo);
    }

    // MÉTODO buscar
    public Vehiculo buscar(Vehiculo turismo) {
        Vehiculo turismo2 = null;
        Iterator<Vehiculo> iterator = coleccionVehiculos.iterator();
        while (iterator.hasNext()) {
            turismo2 = iterator.next();
            if (turismo2.getMatricula().equals(turismo.getMatricula())) {
                return turismo2;
            }
        }
        if (turismo == null) {
            throw new NullPointerException("ERROR: No se puede buscar un turismo nulo.");
        }
        return null;
    }

    // MÉTODO borrar
    public void borrar(Vehiculo turismo) throws OperationNotSupportedException {
        if (turismo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un turismo nulo.");
        }
        if (coleccionVehiculos.contains(turismo)) {
            coleccionVehiculos.remove(turismo);
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ningún turismo con esa matrícula.");
        }
    }

}
