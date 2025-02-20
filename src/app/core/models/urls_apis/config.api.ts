/* Puerto del servidor local */
const PUERTO_LOCAL = 4000;

/* Url del dominio y el puerto del servidor local */
const DOMINIO_PUERTO_LOCAL : string = `http://localhost:${PUERTO_LOCAL}`;

/* Api para generar los consecutivos de las facturas */
export const API_GENERAR_CONSECUTIVO : string = `${DOMINIO_PUERTO_LOCAL}/api/v1/factura/generar-consecutivo`;

/* Api para generar/persistir una factura */
export const API_GENERAR_FACTURA = `${DOMINIO_PUERTO_LOCAL}/api/v1/factura/generar-factura`;

/* Api buscar un artículo por su código */
export const API_ARTICULO_POR_CODIGO = `${DOMINIO_PUERTO_LOCAL}/api/v1/articulos/codigo`;

/* Api para buscar un nit/cliente por su documento */
export const API_CLIENTE_POR_DOCUMENTO = `${DOMINIO_PUERTO_LOCAL}/api/v1/nits/documento`;