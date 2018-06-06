API - mutant - cloud
==================

Aplicación Mutant usando Google Cloud Engine en Java.

## Productos
- App Engine

## Lenguaje
- Java

## Instrucciones

1. Para postear un mutante ejecutar el servicio POST: https://x-men-206104.appspot.com/api/mutant/

    Body:
        `{
            "dna": ["TTGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
        }`

   Este servicio retornará 200-Ok si el dna enviado es un mutante y 403-Forbidden en caso contrario

1. Para ver las estadísticas de las verificaciones de los ADN enviados ejecutar el servicio GET: https://x-men-206104.appspot.com/api/mutant/stats, este retornará un JSON como el que se puede ver a continuación:

    JSON:
        `{
            "count_mutant_dna": 5,
            "count_human_dna": 10,
            "ratio": 0.5
        }`
    