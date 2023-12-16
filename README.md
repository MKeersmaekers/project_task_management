Task Management Service Documentatie
Thema

Het gekozen thema voor deze microservices-architectuur is een Task Management Service. Het doel is om gebruikers in staat te stellen taken te beheren, inclusief het maken, bijwerken, verwijderen en lezen van taken. 
Daarnaast is er een notificatieservice geïmplementeerd om gebruikers te herinneren aan naderende deadlines van taken.

Microservices en Componenten

    User Microservice:
        Verantwoordelijk voor het beheer van gebruikersinformatie.
        Endpoints:
            GET  /users: Geeft een lijst van alle gebruikers.
            GET  /users/{userId}: Geeft informatie over een specifieke gebruiker.

    Task Microservice:
        Beheert de taken van gebruikers.
        Endpoints:
            GET  /tasks: Geeft een lijst van alle taken.
            GET  /tasks/{id}: Geeft informatie over een specifieke taak.
            POST  /tasks: Maakt een nieuwe taak aan.
            PUT  /tasks/{taskId}: Werkt de informatie van een taak bij.
            DELETE  /tasks/{taskId}: Verwijdert een taak.

    Notification Microservice:
        Verzorgt notificaties voor naderende deadlines.
        Endpoints:
            GET  /notifications: Geeft een lijst van alle notificaties.
            POST  /notifications: Maakt een nieuwe notificatie aan.

Uitbreidingen

    Voeg hier eventuele uitbreidingen toe, zoals bijvoorbeeld integratie met externe services, authenticatie, autorisatie, etc.

GitHub Repository

Voor gedetailleerde informatie en de broncode verwijzen we naar ons GitHub README.md.
Aantoonbare werking van alle endpoints

Voeg screenshots toe van Postman requests in de GitHub README.md om de functionaliteit van alle endpoints te illustreren.
