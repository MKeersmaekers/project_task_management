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
            
![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/64b80056-9b41-4e97-a7dd-5b16d3e470ab)


    
Hosted op okteto:

    https://api-gateway-mkeersmaekers.cloud.okteto.net
    https://notification-service-mkeersmaekers.cloud.okteto.net
    https://task-service-mkeersmaekers.cloud.okteto.net
    https://user-service-mkeersmaekers.cloud.okteto.net


Uitbreidingen

Alle basisvereisten/algemen eisen zijn aan voldaan (basics, documentatie, deployment, security en testing), ik heb geen uitbreidingen toegevoegd.

Aantoonbare werking van alle endpoints

/tasks & /tasks/{id}

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/24ed9614-bf40-4819-acf6-29c51b829981)

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/dd7b0afe-bd9f-4567-b76d-992509375510)

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/a6ed679c-795f-4c6c-b830-f92e99702462)

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/15f9a5c7-f801-4742-80c1-3aefef2162d6)

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/41df8749-165d-4cbd-82d5-e311061d7a4b)

/users

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/4643f9e1-d85c-4a66-8d4a-0c458146ae19)

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/740c61e1-3ccf-4dfc-990d-398ec7ae9b6a)

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/ee417226-fe00-46f3-9d57-16d0091aa501)


/notifications

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/e71b81a2-7f45-4665-bd6b-3205959ca888)

![image](https://github.com/MKeersmaekers/project_task_management/assets/91118306/0f926259-bd91-4c8b-acdb-2e6705ec7fee)

