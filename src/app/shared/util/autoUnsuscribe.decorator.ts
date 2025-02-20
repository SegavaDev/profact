/**
 * Decorador @AutoUnsubscribe
 *
 * Este decorador se utiliza para limpiar automáticamente las suscripciones en componentes
 * al momento de destruirlos. Detecta todas las propiedades de la clase que implementen el método
 * `unsubscribe()` (típicamente objetos de tipo Subscription) y las cancela automáticamente.
 *
 * @returns Una función decoradora que extiende el método ngOnDestroy del componente.
 */
export function AutoUnsubscribe() {
    return function (constructor: Function) {
        // Guardar el método original ngOnDestroy, si existe
        const originalDestroy = constructor.prototype.ngOnDestroy;

        // Sobrescribir el método ngOnDestroy
        constructor.prototype.ngOnDestroy = function () {
            // Iterar sobre todas las propiedades del componente
            for (const prop in this) {
                if (Object.prototype.hasOwnProperty.call(this, prop)) {
                    const property = this[prop];

                    // Verificar si la propiedad tiene el método unsubscribe
                    if (property && typeof property.unsubscribe === 'function') {
                        //console.log("Suscripción cancelada");
                        property.unsubscribe(); // Cancelar la suscripción
                    }
                }
            }

            // Llamar al método original ngOnDestroy si estaba definido
            if (originalDestroy && typeof originalDestroy === 'function') {
                //console.log("Suscripción cancelada");
                originalDestroy.apply(this);
            }
        };
    };
}
