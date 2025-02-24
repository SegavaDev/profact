import { FormBuilder, FormGroup, Validators } from "@angular/forms";

/**
 * Función que retorna un formulario con formularios anidados
 */
export function formsEnFactura(): FormGroup {
    const form: FormBuilder = new FormBuilder();

    return form.group({
        cliente: form.group({
            documento: ["", [Validators.required]]
        }),
        articulo: form.group({
            codigo: ["",[Validators.required] ],
            naturaleza: ["", [Validators.required]],
            unidades: ["0", [Validators.required]],
            costos: ["", [Validators.required]],
            precioVenta: ["0", [Validators.required]]
        })
    })
}