import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Kardex } from '@core/models/facturas/Kardex.class';

@Component({
  selector: 'app-grilla-factura',
  imports: [],
  templateUrl: './grilla-factura.component.html',
  styleUrl: './grilla-factura.component.css'
})
export class GrillaFacturaComponent {

    /** lista con las clases que tienen los kardex */
    @Input() data!: Kardex[];
    @Output() codEliminar: EventEmitter<string> = new EventEmitter<string>();
    cabeceras: string[] = [
        "Id artículo",
        "Cod. artículo",
        "Artículo",
        "Laboratorio",
        "Naturaleza",
        "Unidades",
        "Total Costos",
        "Total venta"
    ]

    constructor() {}

    eliminarItem(codigo: string) {
        this.codEliminar.emit(codigo);
    }
}
