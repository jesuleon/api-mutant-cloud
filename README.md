API - mutant - cloud
==================

Aplicación Mutant usando Google Cloud Engine en Java.

## Proyecto en desarrollo

Actualmente no se almacenan los POST de los mutantes, se intentó con una BD mongo (normal y embedded) pero dió errores a nivel de deployment, para el futuro se prevee almacenarlos en un datastore habilitando al servicio stats a que muestre las estadísticas correctas del número existente de humanos y mutantes

## Productos
- App Engine

## Lenguaje
- Java

## Instrucciones

1. Para postear un mutante ejecutar el servicio POST: https://mutant-205600.appspot.com/api/mutant/

    Body:
        `{
            "dna": ["TTGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
        }`

   Este servicio retornará 200-Ok si el dna enviado es un mutante y 403-Forbidden en caso contrario

1. Para ver las estadísticas de las verificaciones de los ADN enviados ejecutar el servicio GET: https://mutant-205600.appspot.com/api/mutant/stats, este retornará un JSON como el que se puede ver a continuación:

    JSON:
        `{
            "count_mutant_dna": 5,
            "count_human_dna": 10,
            "ratio": 0.5
        }`
    