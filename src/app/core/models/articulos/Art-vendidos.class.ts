import { IArticuloVendido } from "./Art-vendidos.interface";

export class ArticuloVendido implements IArticuloVendido {
    artId: number;
    unds: number;

    constructor(
        artId: number,
        unds: number
    ) {
        this.artId = artId,
        this.unds = unds
    }

    public get getArtId() {
        return this.artId;
    }

    public set setArtId(value: number) {
        this.artId = value;
    }

    public get getUnds() {
        return this.unds;
    }

    public set setUnds(value: number) {
        this.unds = value;
    }
}