
# 📞 Call center Oeste Cable Color 📺
# TP Integrador
**📗Universidad Nacional de La Matanza (UNLaM)📗**  

**Materia: Programación básica 1**  

**Grupo 14 - PB1 – TN**

## Descripción

Aplicación para el **call center** de la empresa ficticia, **Oeste Cable Color**. Tiene el objetivo de identificar potenciales clientes del nuevo servicio de televisión por cable e internet.

El sistema permite la gestión de **contactos** y la asistencia a los **operadores** durante el proceso de llamadas, siguiendo ciertas reglas de negocio.

## Objetivos del Sistema

- Gestionar un listado de contactos con información detallada.
- Permitir a los operadores realizar llamadas a potenciales contactos.
- Registrar los resultados de cada llamada.
- Implementar mejoras adicionales para gestionar usuarios, operadores y sus estadísticas.

---

## Consigna base

Nos contratan para desarrollar el software de un callcenter. El objetivo de la aplicación es poder incorporar el listado de “Contactos”, de los cuales nos interesa conocer:

- **Nombre y Apellido**: Valor alfanumérico.
- **Celular**: Compuesto por código de país, código de área y número.
- **E-Mail**: Debe contener el símbolo '@' y el caracter '.'.
- **Dirección**: Valor alfanumérico.
- **Código Postal**: Valor numérico.
- **Localidad**: Valor alfanumérico.
- **Provincia**: Enumerador con las 23 provincias argentinas.
- **Es Cliente**:(inicialmente "No").
- **Desea ser llamado nuevamente**:(inicialmente "Sí").

Cuando el operador del callcenter ingresa a la aplicación, debe seleccionar “Realizar nueva llamada”. En ese momento el sistema debe “buscarAlCandidato” y mostrar por pantalla los datos de este. Para determinar qué contacto llamar, el sistema debe seleccionar *aleatoriamente* un contacto que cumpla con las siguientes condiciones:

1. El contacto **NO debe ser cliente aún**.
2. El contacto **desea ser llamado** o al menos no informó lo contrario.
3. El código postal del contacto debe existir en las **zonas de cobertura existente**.

Una vez que el operador realiza la llamada debe registrar la misma:

- a. Si la llamada fue **exitosa** (en ese caso el contacto pasa a ser cliente, y no se lo debe volver a llamar).
- b. Si el contacto **no desea ser llamado nuevamente** (la llamada pudo no haber sido exitosa, pero se podría hacer un nuevo intento en el futuro).
- c. **Observaciones**: Texto libre donde el operador deja registro de lo conversado.

---

## Propuesta de Mejora

Como mejora adicional, se incorpora las clases **`Operador`** y **`Fecha`** , con el objetivo de:

- Asociar los contactos a la atención de un operador.
- Gestionar credenciales de acceso para cada operador al sistema.
- Registrar cuántos contactos fueron convertidos en clientes por cada operador.
- Registrar la fecha en la que cada operador lleva a cabo sus actividades.

### Clase `Operador`

| Atributo     | Tipo    | Descripción                                 |
|--------------|---------|---------------------------------------------|
| id           | long    | Identificador único                         |
| nombre       | String  | Nombre del operador                         |
| apellido     | String  | Apellido del operador                       |
| dni          | String  | Documento de identidad                      |
| contrasenia  | String  | Contraseña para ingresar al sistema         |
| clientes     | int     | Cantidad de contactos convertidos en clientes |

Se incluyen métodos como `loguearOperador`, `actualizarContrasenia`, validaciones para los datos del mismo y un `toString()` personalizado.

### Cambios adicionales

- La clase **`Empresa`** contiene ahora un arreglo de operadores y métodos de autenticación (`loguearOperador`, `desloguearOperador`).
- La clase **`Contacto`** incorpora un nuevo atributo `atendidoPor`, que referencia al operador que lo contactó. Adicionalmente, al atibuto `registrosLlamadas` se le añade en cada interación la fecha en la que se realizo la llamada.

---

## ⚙️ Instalación local y requisitos
Antes de empezar a utilizar esta aplicación, asegurate de tener instalado lo siguiente:

- ✅ Java 11 o superior
- **IDE Recomendado**: ✅ Eclipse

---

## 👥 Autores
- @Agustina Caceres Perez
- @Matias Llanos
- @Sebastian Gauto
- @Franco Saltarelli
- @Lautaro Retta