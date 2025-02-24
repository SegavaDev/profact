import { Injectable } from '@angular/core';
import { ArticuloVendido } from '@core/models/articulos/Art-vendidos.class';
import { IArticuloVendido } from '@core/models/articulos/Art-vendidos.interface';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class ArticulosLocalStorageService {
    private keyStorage = 'articulos';
    private articulosVendidos: IArticuloVendido[] = [];
    private _articuloSubject = new BehaviorSubject<IArticuloVendido[]>(
        this.articulosVendidos
    );
    public articulos$ = this._articuloSubject.asObservable();

    constructor() {
        this.cargarArticulos();
        window.addEventListener('storage', (s) => this.hayCambios(s));
    }

    hayCambios(e: StorageEvent) {
        if (e.key === this.keyStorage) {
            this.cargarArticulos();
        }
    }

    private cargarArticulos() {
        let dataStorage = localStorage.getItem(this.keyStorage);

        if (dataStorage !== null) {
            let dataParse = JSON.parse(dataStorage);
            this.articulosVendidos = this.mapearArticulosStorage(dataParse);
            this._articuloSubject.next(this.articulosVendidos);
        }
    }
    private mapearArticulosStorage(listaArt: any[]): IArticuloVendido[] {
        return listaArt.map(
            (art) =>
                new ArticuloVendido(
                    art.artId ?? 0,
                    art.artCosto ?? 0
                )
        );
    }

    private guardarArticulos() {
        localStorage.setItem(
            this.keyStorage,
            JSON.stringify(this.articulosVendidos)
        );
        this._articuloSubject.next(this.articulosVendidos);
    }

    actualizarArticulos(articulos: IArticuloVendido[]) {
        this.articulosVendidos = articulos;
        this.guardarArticulos();
    }

    verVendidos(): Observable<IArticuloVendido[]> {
        return this.articulos$;
    }

    buscarArticulo(articulo: IArticuloVendido): IArticuloVendido | number {
        const index = this.articulosVendidos.findIndex(
            (a) => a.artId === articulo.artId
        );

        if(index !== -1) {
            return this.articulosVendidos[index];
        }
        else {
            return index;
        }

    }

    limpiarLocalStorage() {
        localStorage.removeItem(this.keyStorage);
        this.articulosVendidos.length = 0;
        this._articuloSubject.next(this.articulosVendidos);
    }
}
