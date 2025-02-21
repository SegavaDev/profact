export class Kardex {
    private _krdxArtId: number;
    private _krdxArtCod: string;
    private _krdxArtNom: string;
    private _krdxArtLab: string;
    private _krdxArtNat: boolean;
    private _krdxUnds: number;
    private _krdxTotalCostos: number;

    constructor(
        krdxFactId: number,
        krdxArtId: number,
        krdxArtCod: string,
        krdxArtNom: string,
        krdxArtLab: string,
        krdxArtNat: boolean,
        krdxUnds: number,
        krdxTotalCostos: number
    ) {

        this._krdxArtId = krdxArtId;
        this._krdxArtCod = krdxArtCod;
        this._krdxArtNom = krdxArtNom;
        this._krdxArtLab = krdxArtLab;
        this._krdxArtNat = krdxArtNat;
        this._krdxUnds = krdxUnds;
        this._krdxTotalCostos = krdxTotalCostos;
    }

    public get krdxArtId() {
        return this._krdxArtId;
    }

    public get krdxArtCod() {
        return this._krdxArtCod;
    }

    public get krdxArtNom() {
        return this._krdxArtNom;
    }

    public get krdxArtLab() {
        return this._krdxArtLab;
    }

    public get krdxArtNat() {
        return this._krdxArtNat;
    }

    public get krdxUnds() {
        return this._krdxUnds;
    }

    public get krdxTotalCostos() {
        return this._krdxTotalCostos;
    }
}
