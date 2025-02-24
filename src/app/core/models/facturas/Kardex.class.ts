export class Kardex {
    private krdxArtId: number;
    private krdxArtCod: string;
    private krdxArtNom: string;
    private krdxArtLab: string;
    private krdxArtNat: boolean;
    public krdxUnds: number;
    public krdxTotalCostos: number;
    public krdxTotalVnta: number;

    constructor(
        krdxArtId: number,
        krdxArtCod: string,
        krdxArtNom: string,
        krdxArtLab: string,
        krdxArtNat: boolean,
        krdxUnds: number,
        krdxTotalCostos: number,
        krdxTotalVnta: number
    ) {
        this.krdxArtId = krdxArtId;
        this.krdxArtCod = krdxArtCod;
        this.krdxArtNom = krdxArtNom;
        this.krdxArtLab = krdxArtLab;
        this.krdxArtNat = krdxArtNat;
        this.krdxUnds = krdxUnds;
        this.krdxTotalCostos = krdxTotalCostos;
        this.krdxTotalVnta = krdxTotalVnta;
    }

    public get getKrdxArtId() {
        return this.krdxArtId;
    }

    public get getKrdxArtCod() {
        return this.krdxArtCod;
    }

    public get getKrdxArtNom() {
        return this.krdxArtNom;
    }

    public get getKrdxArtLab() {
        return this.krdxArtLab;
    }

    public get getKrdxArtNat() {
        return this.krdxArtNat;
    }

    public get getKrdxUnds() {
        return this.krdxUnds;
    }

    public set setKrdxUnds(valor: number) {
        this.krdxUnds = valor;
    }

    public get getKrdxTotalCostos() {
        return this.krdxTotalCostos;
    }

    public set setKrdxTotalVnta(valor: number) {
        this.setKrdxTotalVnta = valor;
    }

    public get getKrdxTotalVnta() {
        return this.krdxTotalVnta;
    }
}
