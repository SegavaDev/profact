import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () =>
            import('./pages/factura/factura.component').then(
                (f) => f.FacturaComponent
            ),
        data: {
            title: 'Factura',
        },
    },
];
