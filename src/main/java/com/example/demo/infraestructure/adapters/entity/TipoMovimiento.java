package com.example.demo.infraestructure.adapters.entity;


public enum TipoMovimiento {
    INGRESO,     // alta de stock (compra, ajuste positivo)
    SALIDA,      // baja de stock (venta, merma, vencimiento)
    TRANSFERENCIA // entre sucursales (origen -> destino)
}
