import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_GENERAR_CONSECUTIVO } from '@core/models/urls_apis/config.api';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class ObtenerConsecutivoFactService {
    /* Url api generar consecutivo  */
    private URL = API_GENERAR_CONSECUTIVO;

    constructor(private http: HttpClient) {}

    obtener(): Observable<string> {
        return this.http.get<string>(this.URL);
    }

}
