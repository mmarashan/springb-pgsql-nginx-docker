## Sensors reading API based on Spring Boot with PostgreSQL and NGINX in Docker Compose

For running:
   
    docker-compose up

For add sensor reading:
    
    curl -s -X POST http://localhost:8080/api/save -H 'Content-Type: application/json' -d '[{"objectId": 0, "sensorId": 0, "time": 1590868461, "value": -40.0}]'

For checking history:

    curl -s -X GET 'http://localhost:8080/api/history?id=0&from=1590868460&to=1590868463'