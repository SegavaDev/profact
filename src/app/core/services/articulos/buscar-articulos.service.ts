import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IResponseApi } from '@core/models/response_apis/Response-api.interface';
import { API_ARTICULO_POR_CODIGO } from '@core/models/urls_apis/config.api';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class BuscarArticulosService {
    /** Url api buscar articulos */
    private URL: string = API_ARTICULO_POR_CODIGO;

    constructor(private http: HttpClient) {}

    buscarArticulos(codigo: string): Observable<IResponseApi> {
        return this.http.post<IResponseApi>(this.URL, codigo);
    }
}
