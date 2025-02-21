import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IResponseApi } from '@core/models/response_apis/Response-api.interface';
import { API_GENERAR_CONSECUTIVO } from '@core/models/urls_apis/config.api';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class ObtenerConsecutivoFactService {
    /* Url api generar consecutivo  */
    private URL = API_GENERAR_CONSECUTIVO;

    constructor(private http: HttpClient) {}

    obtener(): Observable<IResponseApi> {
        return this.http.get<IResponseApi>(this.URL);
    }
}
