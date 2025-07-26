# Desarrollo Trabajo Final TPi SpringBoot

## Consideraciones

- El trabajo se subió directamente a la rama **MAIN**, ya que me olvidé de crear el repositorio previamente.
  
- Un problema que tuve con los **Mappers autogenerados** es que, cada vez que ingreso por primera vez a **VS Code**, debo guardar cada uno de los mappers con **Ctrl + S** para evitar errores.  
  No pude encontrar la causa exacta de este problema, pero lo dejo documentado por si alguien más se encuentra con el mismo error.  
  
- Por lo demás, el proyecto está funcionando correctamente y todo lo demás está bien configurado.

## Funcionalidades implementadas

- Se creó búsqueda de **Usuarios con carrito Abierto y Cerrado** (hay que tener cuidado ya que es **CASE SENSITIVE**). No generé una solución para esta sensibilidad ya que no era parte de la consigna.

- Se creó búsqueda de **Carrito por ID de usuario**, y la funcionalidad para **cerrar un carrito por ID de usuario** (con control de error si el usuario no existe o no tiene carrito abierto).

- Se creó funcionalidad para **obtener todas las facturas** (podría servir como un reporte de negocio; además se puede filtrar por fecha), obtener facturas de un usuario en particular, y obtener una factura específica.

---