import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TransaccionFactura } from '@core/models/facturas/Transaccion-factura.class';
import { IResponseApi } from '@core/models/response_apis/Response-api.interface';
import { API_GENERAR_FACTURA } from '@core/models/urls_apis/config.api';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class GenerarFacturaService {
    /** Url de Api para generar factura */
    private URL = API_GENERAR_FACTURA;

    constructor(private http: HttpClient) {}

    generarFactura(transacción: TransaccionFactura): Observable<IResponseApi> {
        return this.http.post<IResponseApi>(this.URL, transacción);
    }
}
