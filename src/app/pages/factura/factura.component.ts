import { Component } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Articulo } from '@core/models/articulos/Articulo.class';
import { Cliente } from '@core/models/clientes/Cliente.class';
import { Kardex } from '@core/models/facturas/Kardex.class';
import { BuscarArticulosService } from '@core/services/articulos/buscar-articulos.service';
import { ObtenerNitsService } from '@core/services/clientes/obtener-nits.service';
import { ObtenerConsecutivoFactService } from '@core/services/facturas/obtener-consecutivo-fact.service';
import { HeaderFacturaComponent } from '@shared/components/header-factura/header-factura.component';
import { AutoUnsubscribe } from '@shared/util/autoUnsuscribe.decorator';
import { getFechaString, getFechaVencimiento } from '@shared/util/fecha.util';
import { formsEnFactura } from '@shared/util/formsFactura.form';

@Component({
    selector: 'app-factura',
    imports: [ReactiveFormsModule, HeaderFacturaComponent],
    templateUrl: './factura.component.html',
    styleUrl: './factura.component.css',
})
@AutoUnsubscribe()
export class FacturaComponent {
    /* Consecutivo */
    consecutivo!: string;
    /* Fecha */
    fecha!: string;
    /** Fecha de vencimiento */
    fechaVence!: string;
    /* Naturaleza del kardex unitario */
    naturaleza: boolean = false;
    /** Total costo y total venta item */
    totalCosto!: number;
    totalVenta!: number;
    /* Formularios transacción factura */
    formFactura!: FormGroup;
    /** Cliente */
    cliente!: Cliente;
    listClientes!: Cliente[];
    /** Artículo */
    articulo!: Articulo;
    listArticulos!: Articulo[];
    /** Kardex de factura */
    kardex!: Kardex;
    listKardex!: Kardex[];
    /* Lista desplegable de nits */
    listDespNits: boolean = false;
    /* Lista desplegable de artículos */
    listDespArticulos: boolean = false;
    /** Nombres de los formularios */
    nomForms = ['cliente', 'articulo'];

    constructor(
        private getConsFactService: ObtenerConsecutivoFactService,
        private postNitDocService: ObtenerNitsService,
        private postArticuloService: BuscarArticulosService
    ) {
        this.formFactura = formsEnFactura();
    }

    ngOnInit() {
        this.fecha = getFechaString();
        this.fnConsecutivoService();
    }

    getForm(nombre: string): FormGroup {
        return this.formFactura.get(nombre) as FormGroup;
    }

    fnConsecutivoService() {
        this.getConsFactService.obtener().subscribe({
            next: (response) => {
                this.consecutivo = response.data;
            },
            error: (err) => {
                console.log('resp err: ', err);
            }
        });
    }

    fnNitService(documento: string) {
        this.postNitDocService.buscar(documento).subscribe({
            next: (response) => {
                this.listClientes = response.data;
            },
            error: (err) => {
                console.log(err);
            },
        });
    }

    fnArticuloService(codigo: string) {
        this.postArticuloService.buscarArticulos(codigo).subscribe({
            next: response => {
                this.listArticulos = response.data;
            },
            error: (err) => {
                console.log('resp err: ', err);
            }
        })
    }

    getNits(event: Event) {
        this.listDespNits = true;
        const inputId = (event.target as HTMLElement).id;
        const documento = this.getForm(this.nomForms[0]).controls[inputId]
            .value;
        this.fnNitService(documento);
    }

    selectCliente(nit: Cliente) {
        this.listDespNits = false;
        this.cliente = nit;
        this.fechaVence = getFechaVencimiento(this.cliente.nitPlazo);
        this.getForm(this.nomForms[0]).controls['documento'].setValue(
            this.cliente.nitDoc
        );
    }

    getArticulos(event: Event) {
        console.log("get");

        this.listDespArticulos = true;
        const inputId = (event.target as HTMLElement).id;
        const codigo = this.getForm(this.nomForms[1]).controls[inputId].value;
        this.fnArticuloService(codigo);
    }

    selectArticulo(articulo: Articulo) {
        this.listDespArticulos = false;
        this.articulo = articulo;
        this.getForm(this.nomForms[1]).controls['costos'].setValue(articulo.artCosto);
        this.getForm(this.nomForms[1]).controls['precioVenta'].setValue(articulo.artPrecVenta);
        this.getForm(this.nomForms[1]).controls['codigo'].setValue(this.articulo.artCod);
    }

    validarNaturaleza(event: Event) {
        const input = event.currentTarget as HTMLElement;
        console.log(input);
    }

    calcularTotalesArticulo() {
        const costos: number = this.getForm(this.nomForms[1]).controls['costos'].value;
        const precioVta: number = this.getForm(this.nomForms[1]).controls['precioVenta'].value;
        const unidades: number = this.getForm(this.nomForms[1]).controls['unidades'].value;

        this.totalCosto = unidades * costos;
        this.totalVenta = unidades * precioVta;
    }
}
