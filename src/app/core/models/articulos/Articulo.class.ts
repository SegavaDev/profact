export class Articulo {
    private _artId: number;
    private _artCod: string;
    private _artNom: string;
    private _artLab: string;
    private _artSaldo: number;
    private _artCosto: number;
    private _artPrecVenta: number;

    constructor(
        artId: number,
        artCod: string,
        artNom: string,
        artLab: string,
        artSaldo: number,
        artCosto: number,
        artPrecVenta: number
    ) {
        this._artId = artId;
        this._artCod = artCod;
        this._artNom = artNom;
        this._artLab = artLab;
        this._artSaldo = artSaldo;
        this._artCosto = artCosto;
        this._artPrecVenta = artPrecVenta;
    }


    public get artId() {
        return this._artId;
    }

    public get artCod() {
        return this._artCod;
    }

    public get artNom() {
        return this._artNom;
    }

    public get artLab() {
        return this._artLab;
    }

    public get artSaldo() {
        return this._artSaldo;
    }

    public set artSaldo(value) {
        this._artSaldo = value;
    }

    public get artCosto() {
        return this._artCosto;
    }

    public set artCosto(value) {
        this._artCosto = value;
    }

    public get artPrecVenta() {
        return this._artPrecVenta;
    }

    public set artPrecVenta(value) {
        this._artPrecVenta = value;
    }
    
}
