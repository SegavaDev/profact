export class Cliente {
    private _nitDoc: string;
    private _nitNom: string;
    private _nitCupo: number;
    private _nitPlazo: number;
    private _nitCartera: number;

    constructor(
        nitDoc: string,
        nitNom: string,
        nitCupo: number,
        nitPlazo: number,
        nitCartera: number
    ) {
        this._nitDoc = nitDoc;
        this._nitNom = nitNom;
        this._nitCupo = nitCupo;
        this._nitPlazo = nitPlazo;
        this._nitCartera = nitCartera;
    }

    public get nitDoc() {
        return this._nitDoc;
    }

    public get nitNom() {
        return this._nitNom;
    }

    public get nitCupo() {
        return this._nitCupo;
    }

    public get nitPlazo() {
        return this._nitPlazo;
    }

    public get nitCartera() {
        return this._nitCartera;
    }
    public set nitCartera(value) {
        this._nitCartera = value;
    }

}
