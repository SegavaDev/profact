import { Component } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ArticuloVendido } from '@core/models/articulos/Art-vendidos.class';
import { IArticuloVendido } from '@core/models/articulos/Art-vendidos.interface';
import { Articulo } from '@core/models/articulos/Articulo.class';
import { Cliente } from '@core/models/clientes/Cliente.class';
import { Factura } from '@core/models/facturas/Factura.class';
import { Kardex } from '@core/models/facturas/Kardex.class';
import { TransaccionFactura } from '@core/models/facturas/Transaccion-factura.class';
import { BuscarArticulosService } from '@core/services/articulos/buscar-articulos.service';
import { ObtenerNitsService } from '@core/services/clientes/obtener-nits.service';
import { GenerarFacturaService } from '@core/services/facturas/generar-factura.service';
import { ObtenerConsecutivoFactService } from '@core/services/facturas/obtener-consecutivo-fact.service';
import { ArticulosLocalStorageService } from '@core/services/local_storage/articulos-local-storage.service';
import { GrillaFacturaComponent } from '@shared/components/grilla-factura/grilla-factura.component';
import { HeaderFacturaComponent } from '@shared/components/header-factura/header-factura.component';
import { AutoUnsubscribe } from '@shared/util/autoUnsuscribe.decorator';
import { getFechaString, getFechaVencimiento } from '@shared/util/fecha.util';
import { formsEnFactura } from '@shared/util/formsFactura.form';

@Component({
    selector: 'app-factura',
    imports: [
        ReactiveFormsModule,
        HeaderFacturaComponent,
        GrillaFacturaComponent,
    ],
    templateUrl: './factura.component.html',
    styleUrl: './factura.component.css',
})
@AutoUnsubscribe()
export class FacturaComponent {
    /** Margen de ganancias esperado en caso de cambio de costos */
    readonly MARGEN_GANANCIA: number = 0.1;
    /** Bloqueo de botones */
    blockGuardar: boolean = true;
    blockAgregar: boolean = true;
    /** Nit cliente */
    private nitClienteActual!: string;
    /** Saldo */
    saldo: number = 0;
    disponible: number = 0;
    /* Consecutivo */
    consecutivo!: string;
    /* Fecha */
    fecha!: string;
    /** Fecha de vencimiento */
    fechaVence!: string;
    /* Naturaleza del kardex unitario */
    naturaleza: boolean = false;
    /** Total costos y total ventas factura */
    totalCostosFact: number = 0;
    totalVentasFact: number = 0;
    /** Total costos y total venta por item y unidades vendidas */
    unidades: number = 1;
    totalCostos: number = 0;
    totalVentas: number = 0;
    /** Indicador para colorear rojo el disponible */
    cupoAlcanzado: boolean = true;
    saldoAlcanzado: boolean = true;
    /* Formularios transacción factura */
    formFactura!: FormGroup;
    /** Cliente */
    cliente!: Cliente;
    listClientes: Cliente[] = [];
    /** Artículo */
    articulo!: Articulo;
    listArticulos: Articulo[] = [];
    /** Kardex de factura */
    kardex!: Kardex;
    listKardex: Kardex[] = [];
    /* Lista desplegable de nits */
    listDespNits: boolean = false;
    /* Lista desplegable de artículos */
    listDespArticulos: boolean = false;
    /** Artículos vendidos */
    articulosVendidos: IArticuloVendido[] = [];
    /** Nombres de los formularios */
    nomForms = {
        cliente: 'cliente',
        articulo: 'articulo',
    };
    /** Keys formulario cliente */
    keysCliente = {
        documento: 'documento',
    };
    /** Keys formulario articulo */
    keysArticulo = {
        codigo: 'codigo',
        naturaleza: 'naturaleza',
        unidades: 'unidades',
        costos: 'costos',
        precioVenta: 'precioVenta',
    };
    listValoresArt = Object.values(this.keysArticulo);
    /** Naturalezas */
    opcNaturalezas = {
        positiva: '+',
        negativa: '-',
    };

    constructor(
        private getConsFactService: ObtenerConsecutivoFactService,
        private postNitDocService: ObtenerNitsService,
        private postArticuloService: BuscarArticulosService,
        private generarFactura: GenerarFacturaService,
        private localStorageServices: ArticulosLocalStorageService
    ) {
        this.formFactura = formsEnFactura();
        this.resetFacturaData();
        this.bloquearVariosCamposIn(this.listValoresArt);
    }

    ngOnInit() {
        this.localStorageServices.limpiarLocalStorage();
        this.actualizarComprados();
        this.fecha = getFechaString();
        this.fnConsecutivoService();
    }

    actualizarComprados() {
        this.localStorageServices.articulos$.subscribe({
            next: (data) => {
                this.articulosVendidos = data;
            },
        });
    }

    compararConLocal(articulo: Articulo): Articulo {
        this.articulosVendidos.forEach((a) => {
            if (a.artId === articulo.artId) {
                articulo.artSaldo -= a.unds;
            }
        });
        return articulo;
    }

    desplegarResultados(event: Event) {
        const inputId = (event.target as HTMLElement).children[1].id;

        if (!inputId) {
            return;
        }
        if (inputId === this.keysCliente.documento && !this.listDespArticulos) {
            this.listDespNits = !this.listDespNits;
            return;
        }
        if (inputId === this.keysArticulo.codigo && !this.listDespNits) {
            this.listDespArticulos = !this.listDespArticulos;
            return;
        }
    }

    /** Restaura los valores de las entidades para poderse usar con nuevos datos */
    resetFacturaData() {
        this.cliente = new Cliente('', '', 0, 0, 0);
        this.articulo = new Articulo(0, '', '', '', 0, 0, 0);
        this.kardex = new Kardex(0, '', '', '', false, 0, 0, 0);
        this.blockGuardar = true;
        this.listArticulos.length = 0;
        this.listClientes.length = 0;
        this.listKardex.length = 0;
        this.disponible = 0;
        this.totalCostosFact = 0;
        this.totalVentasFact = 0;
        this.formFactura.reset();
        this.localStorageServices.limpiarLocalStorage();
        this.articulosVendidos.length = 0;
        this.fnConsecutivoService();
    }

    /** Obtiene un formulario por el nombre */
    getForm(nombre: string): FormGroup {
        return this.formFactura.get(nombre) as FormGroup;
    }

    /** Busca el valor de un campo de entrada de un formulario
     * @param form nombre del formulario a buscar
     */
    buscarValorEnForm(form: string, key: string) {
        return this.getForm(form).controls[key];
    }

    /** Realiza una petición al endpoint que devuelve un consecutivo sin activar */
    fnConsecutivoService() {
        this.getConsFactService.obtener().subscribe({
            next: (response) => {
                this.consecutivo = response.data;
            },
            error: (err) => {
                console.log('resp err: ', err);
            },
        });
    }

    /** Realizar una petición al endpoint que buscar un documento de cliente y regresa sus datos */
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

    /** Realiza una petición al endpoint que busca por un código de articulo y lo devuelve */
    fnArticuloService(codigo: string) {
        this.postArticuloService.buscarArticulos(codigo).subscribe({
            next: (response) => {
                this.listArticulos = response.data;
            },
            error: (err) => {
                console.log('resp err: ', err);
            },
        });
    }

    /** Bajo un evento input, captura el documento ingresado y
     * lo usa para consumir un servicio que devuelve los documentos de cliente
     * */
    getNits(event: Event) {
        this.listDespNits = true;
        const inputId = (event.target as HTMLElement).id;
        const documento = this.buscarValorEnForm(
            this.nomForms.cliente,
            inputId
        ).value;
        this.fnNitService(documento);
    }

    /** Selecciona de una lista de clientes aquel que realiza la transacción */
    selectCliente(nit: Cliente) {
        if (this.nitClienteActual !== nit.nitDoc) {
            this.listKardex.length = 0;
            this.totalCostosFact = 0;
            this.totalVentasFact = 0;
            this.resetInterfaz();
        }
        this.listDespNits = false;
        this.cliente = nit;
        this.nitClienteActual = this.cliente.nitDoc;
        this.fechaVence = getFechaVencimiento(this.cliente.nitPlazo);
        this.buscarValorEnForm(
            this.nomForms.cliente,
            this.keysCliente.documento
        ).setValue(this.nitClienteActual);

        this.disponible = this.cliente.nitCupo - this.cliente.nitCartera - this.totalVentas;

        if (this.disponible > 0) {
            this.cupoAlcanzado = this.noHayDisponibilidad();
            this.bloquearUnCampoInput(this.keysArticulo.codigo, false);
        } else {
            this.cupoAlcanzado = this.noHayDisponibilidad();
            this.bloquearVariosCamposIn(this.listValoresArt);
        }
    }

    /** Bajo un evento input, captura el código ingresado y
     * lo usa para consumir un servicio que devuelve los artículos
     * para las transacciones
     * */
    getArticulos(event: Event) {
        this.listDespArticulos = true;
        const inputId = (event.target as HTMLElement).id;
        const codigo = this.buscarValorEnForm(
            this.nomForms.articulo,
            inputId
        ).value;
        this.fnArticuloService(codigo);
    }

    /** Selecciona un artículo de una lista de artículos */
    selectArticulo(articulo: Articulo) {
        this.listDespArticulos = false;
        this.articulo = articulo;
        this.buscarValorEnForm(
            this.nomForms.articulo,
            this.keysArticulo.costos
        ).setValue(articulo.artCosto);
        this.buscarValorEnForm(
            this.nomForms.articulo,
            this.keysArticulo.precioVenta
        ).setValue(!this.naturaleza ? articulo.artPrecVenta : 0);
        this.buscarValorEnForm(
            this.nomForms.articulo,
            this.keysArticulo.codigo
        ).setValue(this.articulo.artCod);

        articulo = this.compararConLocal(articulo);
        this.saldo = articulo.artSaldo;
        this.totalVentas = articulo.artPrecVenta * this.unidades;
        this.totalCostos = articulo.artCosto * this.unidades;
        this.cupoAlcanzado = this.noHayDisponibilidad();

        if (this.saldo <= 0 || this.cupoAlcanzado) {
            this.bloquearVariosCamposIn(
                this.listValoresArt.filter(
                    (e) =>
                        e !== this.keysArticulo.codigo &&
                        e !== this.keysArticulo.unidades
                )
            );
            this.blockAgregar = true;
            this.saldoAlcanzado = this.saldo <= 0 ? true : false;
        } else {
            this.bloquearVariosCamposIn(this.listValoresArt, false);
            this.blockAgregar = false;
            this.saldoAlcanzado = false;
        }
        this.buscarValorEnForm(
            this.nomForms.articulo,
            this.keysArticulo.unidades
        ).setValue(this.unidades);
        this.saldo -= this.unidades;
    }

    /** Deshabilita un campo de entrada de formulario */
    bloquearUnCampoInput(key: string, bloquear: boolean = true) {
        const formArt = this.getForm(this.nomForms.articulo);
        if (bloquear) {
            formArt.controls[key].disable();
        } else {
            formArt.controls[key].enable();
        }
    }

    bloquearVariosCamposIn(campos: string[], bloquear: boolean = true) {
        this.listValoresArt.forEach((k) => {
            let index = campos.indexOf(k);
            if (index !== -1 && campos[index] === k) {
                this.bloquearUnCampoInput(k, bloquear);
            }
        });
    }

    /** Obtiene / actualiza la naturaleza en curso */
    obtenerNaturaleza(event: Event) {
        const input = (event.target as HTMLElement).id;
        this.naturaleza =
            this.buscarValorEnForm(this.nomForms.articulo, input).value ===
            this.opcNaturalezas.negativa
                ? false
                : true;
        if (this.naturaleza) {
            this.unidades = 0;
            this.buscarValorEnForm(
                this.nomForms.articulo,
                this.keysArticulo.unidades
            ).setValue(this.unidades);
            this.saldo = this.articulo.artSaldo;
        }
    }

    /** Calcula el total de la factura */
    calcularTotalFactura(): number {
        let total = 0;
        this.listKardex.forEach(k => total = k.getKrdxTotalVnta + total);
        return total;
    }

    /** Calcula los valores de un articulo teniendo en
     * cuenta parámetros preestablecidos
     */
    calcularTotalesArticulo() {
        const costos = this.buscarValorEnForm(
            this.nomForms.articulo,
            this.keysArticulo.costos
        );
        const unidadesInp = this.buscarValorEnForm(
            this.nomForms.articulo,
            this.keysArticulo.unidades
        );
        const precioVta = this.buscarValorEnForm(
            this.nomForms.articulo,
            this.keysArticulo.precioVenta
        );

        const unidadesPosibles = Math.trunc(
            (this.cliente.nitCupo - this.cliente.nitCartera) /
                this.articulo.artPrecVenta
        );
        /** Valida saldo disponible */
        if (this.saldo <= 0 && !this.naturaleza) {
            this.saldoAlcanzado = true;
            this.bloquearVariosCamposIn(
                this.listValoresArt.filter(
                    (v) => v !== this.keysArticulo.codigo
                )
            );
            this.blockAgregar = true;
            return;
        }
        this.saldoAlcanzado = false;
        /** Valida cupo disponible */
        if (
            (this.disponible <= 0 && !this.naturaleza) ||
            (unidadesPosibles < unidadesInp.value && !this.naturaleza)
        ) {
            this.cupoAlcanzado = true;
            this.bloquearVariosCamposIn(
                this.listValoresArt.filter(
                    (v) =>
                        v !== this.keysArticulo.codigo &&
                        v !== this.keysArticulo.unidades &&
                        v !== this.keysArticulo.precioVenta
                )
            );
            this.blockAgregar = true;
            unidadesInp.setValue(unidadesPosibles);
            return;
        }
        this.bloquearVariosCamposIn(this.listValoresArt, false);
        this.cupoAlcanzado = false;
        this.unidades = unidadesInp.value;
        this.saldo = this.naturaleza
            ? this.minMaxUnidades(false)
            : this.minMaxUnidades();
        /**
         * Valida que el precio de venta supere o iguale al de costos
         * en este caso, se aplica una ganancia esperada por defecto
         */
        if (precioVta.value < costos.value) {
            precioVta.setValue(
                costos.value * this.MARGEN_GANANCIA + costos.value
            );
        }

        this.totalCostos = costos.value * this.unidades;
        this.totalVentas = precioVta.value * this.unidades;
        this.blockAgregar = false;
        this.blockGuardar = this.listKardex.length === 0 ? true : false;
    }

    /** Elimina un registro de compra */
    eliminarCodKrdx(codigo: string) {
        this.listKardex = this.listKardex.filter((k) => {
            if (k.getKrdxArtCod !== codigo) {
                return k;
            } else {
                this.cliente.nitCartera -= k.krdxTotalVnta;
                this.disponible =
                    this.cliente.nitCupo - this.cliente.nitCartera - this.totalVentas;
                this.articulosVendidos = this.articulosVendidos.filter((a) => {
                    if (a.artId !== k.getKrdxArtId) {
                        return a;
                    }
                    this.articulo.artSaldo += a.unds;
                    this.saldo = this.articulo.artSaldo;
                    return;
                });
                this.localStorageServices.actualizarArticulos(
                    this.articulosVendidos
                );
                return;
            }
        });
        this.totalVentasFact = this.calcularTotalFactura();
        this.cupoAlcanzado = this.cliente.nitCartera >= this.cliente.nitCupo;
        this.blockAgregar = this.cupoAlcanzado ? true : false;

        if (!this.cupoAlcanzado && this.saldo !== 0) {
            this.bloquearVariosCamposIn(this.listValoresArt, false);
        }
    }

    /**
     * Obtiene el numero que se tomará por referencia para contar
     * los limites que se tiene en cuanto a unidades vs saldo.
     * Se obtiene el valor máximo por defecto (true).
     * @param maximo true para retornar el máximo de unidades permitidas
     */
    minMaxUnidades(maximo: boolean = true) {
        let valor = 0;
        if (maximo) {
            const max =
                this.articulo.artSaldo -
                this.buscarValorEnForm(
                    this.nomForms.articulo,
                    this.keysArticulo.unidades
                ).value;
            valor = max > 0 ? max : 0;
        } else {
            const min =
                this.articulo.artSaldo +
                this.buscarValorEnForm(
                    this.nomForms.articulo,
                    this.keysArticulo.unidades
                ).value;
            valor = min >= this.articulo.artSaldo ? min : 0;
        }

        return valor;
    }

    /**
     * Si retorna true es porque ya no hay cupo disponible
     */
    noHayDisponibilidad() {
        return (
            Math.trunc(
                (this.cliente.nitCupo - this.cliente.nitCartera) /
                    this.articulo.artPrecVenta
            ) <= 0 || this.disponible <= 0
        );
    }

    /** Agrega un registro a la grilla / tabla */
    agregarKrdx() {
        if (this.disponible <= 0 || this.cliente.nitCartera + this.totalVentas > this.cliente.nitCupo) {
            this.blockAgregar = true;
            this.cupoAlcanzado = this.noHayDisponibilidad();
            return;
        }
        const index = this.listKardex.findIndex(
            (a) => a.getKrdxArtCod === this.articulo.artCod
        );

        if (index === -1) {
            this.listKardex.push(
                new Kardex(
                    this.articulo.artId,
                    this.articulo.artCod,
                    this.articulo.artNom,
                    this.articulo.artLab,
                    this.naturaleza,
                    this.unidades,
                    this.totalCostos,
                    this.totalVentas
                )
            );
            this.articulosVendidos.push(
                new ArticuloVendido(this.articulo.artId, this.unidades)
            );
        } else {
            this.listKardex[index].krdxUnds += this.unidades;
            this.listKardex[index].krdxTotalCostos += this.totalCostos;
            this.listKardex[index].krdxTotalVnta += this.totalVentas;
            this.articulosVendidos[index].unds =
                this.listKardex[index].krdxUnds;
        }

        this.localStorageServices.actualizarArticulos(this.articulosVendidos);
        this.totalCostosFact += this.totalCostos;
        this.totalVentasFact = this.calcularTotalFactura();
        this.cliente.nitCartera += this.totalVentas;

        this.disponible = this.cliente.nitCupo - this.cliente.nitCartera;

        this.blockGuardar = false;
        this.blockAgregar = true;
        this.resetInterfaz();
    }

    /** Resetea la interfaz del artículo dejándola vacía  */
    resetInterfaz() {
        this.totalCostos = 0;
        this.totalVentas = 0;
        this.unidades = 0;
        this.saldo = 0;
        this.listArticulos.length = 0;

        this.formFactura.controls[this.nomForms.articulo].reset();
        this.buscarValorEnForm(
            this.nomForms.articulo,
            this.keysArticulo.naturaleza
        ).setValue(this.opcNaturalezas.negativa);

        this.articulo = new Articulo(0, '', '', '', 0, 0, 0);
        this.kardex = new Kardex(0, '', '', '', false, 0, 0, 0);
        this.bloquearVariosCamposIn(
            this.listValoresArt.filter((e) => e !== this.keysArticulo.codigo)
        );
    }

    /** Realiza el envio de la factura al servidor para hacer persistencia*/
    enviarFactura() {
        if (!confirm('Por favor confirme la transacción')) return;
        /** Crea la factura */
        const factura = new Factura(
            this.consecutivo,
            this.cliente.nitDoc,
            getFechaString(this.fecha, 'yyyy-mm-dd'),
            getFechaString(this.fechaVence, 'yyyy-mm-dd'),
            this.totalVentasFact,
            this.totalCostosFact
        );
        /** Crea la transacción */
        const transacción = new TransaccionFactura(factura, this.listKardex);
        /** Consume el servicio para enviar a guardar en base de datos */
        this.generarFactura.generarFactura(transacción).subscribe({
            next: (response) => {
                response.data ? alert(response.mensaje) : console.log(response);
                this.resetInterfaz();
                this.resetFacturaData();
            },
            error: (err) => {
                err.data ? console.log(err) : alert(err.error.mensaje);
            },
        });
    }
}
