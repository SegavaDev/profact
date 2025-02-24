export class Factura {
    factNum: string;
    factNitDoc: string;
    factFecha: string;
    factFechaVenc: string;
    factTotalVent: number;
    factTotalCost: number;

    constructor(
        factNum: string,
        factNitDoc: string,
        factFecha: string,
        factFechaVenc: string,
        factTotalVent: number,
        factTotalCost: number
    ) {
        this.factNum = factNum;
        this.factNitDoc = factNitDoc;
        this.factFecha = factFecha;
        this.factFechaVenc = factFechaVenc;
        this.factTotalVent = factTotalVent;
        this.factTotalCost = factTotalCost;
    }

    public get getFactNum() {
        return this.factNum;
    }

    public get getfactNitDoc() {
        return this.factNitDoc;
    }

    public get getFactFecha() {
        return this.factFecha;
    }

    public get getFactFechaVenc() {
        return this.factFechaVenc;
    }

    public get getFactTotalVent() {
        return this.factTotalVent;
    }

    public get getFactTotalCost() {
        return this.factTotalCost;
    }

}
