import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IResponseApi } from '@core/models/response_apis/Response-api.interface';
import { API_CLIENTE_POR_DOCUMENTO } from '@core/models/urls_apis/config.api';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class ObtenerNitsService {

    /** Url de endpoint para buscar por documento */
    private URL: string = API_CLIENTE_POR_DOCUMENTO;

    constructor(private http: HttpClient) {}

    /** Busca un nit por su documento */
    buscar(documento: string): Observable<IResponseApi> {
        return this.http.post<IResponseApi>(this.URL, documento);
    }
}
