const FECHA = new Date();
const DIA = FECHA.getDate();
const MES = FECHA.getMonth() + 1;
const ANIO = FECHA.getFullYear();

/** Fecha día en curso */
const HOY_DDMMYY = `${DIA.toString().padStart(2, '0')}/${MES.toString().padStart(2, '0')}/${ANIO}`;
const HOY_YYMMDD = `${ANIO}-${MES.toString().padStart(2, '0')}-${DIA.toString().padStart(2, '0')}`;
/** Separadores de formato */
const SEPARADORES = {
    guion: '-',
    slash: '/',
};
/** FORMATOS de fecha */
const FORMATOS = {
    ddmmyyyy: 'dd/mm/yyyy',
    yyyymmdd: 'yyyy-mm-dd',
};

/**
 * Función para recuperar una fecha dada o la que esté en curso en formato string
 * ! La fecha debe de estar en formato string
 * @param fecha la fecha que se desea convertir a otro formato
 * @param formato el formato en el que se desea obtener la fecha, el formato por defecto es dd/mm/yyyy
 * opciones yyyy-mm-dd y dd/mm/yyyy
 * @returns fecha en formato string
 */
export function getFechaString(
    fecha?: string,
    formato?: string
): string {
    if(fecha && formato) {
        const SEPARADOR = formato.includes(SEPARADORES.guion)
        ? SEPARADORES.guion
        : SEPARADORES.slash;

        const fechaDiv = fecha.split(SEPARADOR);
        const indxAnio = fechaDiv.findIndex(a => fechaDiv.length === 3 && a.length === 4);
        const dia: number | string = indxAnio !== -1 && indxAnio === 2 ? fechaDiv[0] : DIA;
        const mes: number | string = indxAnio !== -1 && indxAnio === 2 ? fechaDiv[1] : MES;
        const anio: number | string = indxAnio !== -1 ? fechaDiv[indxAnio] : ANIO;

        switch (formato) {
            case FORMATOS.ddmmyyyy:
                return `${dia.toString().padStart(2, '0')}${SEPARADOR}${mes
                    .toString()
                    .padStart(2, '0')}${SEPARADOR}${anio}`;

            case FORMATOS.yyyymmdd:
                return `${anio}${SEPARADOR}${mes
                    .toString()
                    .padStart(2, '0')}${SEPARADOR}${dia
                    .toString()
                    .padStart(2, '0')}`;

            default:
                return `${dia.toString().padStart(2, '0')}${SEPARADOR}${mes
                    .toString()
                    .padStart(2, '0')}${SEPARADOR}${anio}`;
        }
    }
    else if(formato) {
        return getFechaString(HOY_DDMMYY, formato);
    }
    else {
        return HOY_DDMMYY;
    }
}

export function dateAString(fecha: Date, formato?: string) {
    const nuevaFecha = `${fecha.getDate().toString().padStart(2, '0')}/${(fecha.getMonth() + 1).toString().padStart(2, '0')}/${fecha.getFullYear()}`;
    return formato ? getFechaString(nuevaFecha) : nuevaFecha;
}

/**
 * Función para calcular la fecha de vencimiento en base al plazo y la fecha del día en curso
 * @param plazo fecha dada al cliente como plazo en días
 */
export function getFechaVencimiento(plazo: number) {
    const fecha = new Date(HOY_YYMMDD);
    fecha.setDate(fecha.getDate() + plazo + 1);
    return dateAString(fecha);
}
