import { Factura } from "./Factura.class";
import { Kardex } from "./Kardex.class";

export class TransaccionFactura {
    private facturaDTO: Factura;
    private listKardexFacturaDTO: Kardex[];

    constructor(
        facturaDTO: Factura,
        listKardexFacturaDTO: Kardex[]
    ) {
        this.facturaDTO = facturaDTO;
        this.listKardexFacturaDTO = listKardexFacturaDTO;
    }

    public get facturaDTo() {
        return this.facturaDTO;
    }

    public get listKardexFacturaDTo() {
        return this.listKardexFacturaDTO;
    }
}