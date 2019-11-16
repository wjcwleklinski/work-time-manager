Zadanie konkursowe Backend

Wykorzystane technologie:
- Spring Boot
- Spring Data REST
- Hibernate
- JasperReports
- MySql
- Maven

Instrukcja użycia:
1. Przygotowanie bazy danych
Logowanie do mysql servera user i password domyślne (root:root)

mysql -u root -p

Wykonanie skryptu

mysql> source schema.sql



2. Uruchomienie aplikacji

mvn spring-boot:run



3. Uruchomienie testów

mvn test



4. Przykład wykorzystania

Tworzenie przykładowych pracowników
curl -i -X POST -d '{"firstName":"Jan","lastName":"Kowalski","phoneNumber":"123456123"}' -H "Content-Type:application/json" http://localhost:8080/employees

curl -i -X POST -d '{"firstName":"Adam","lastName":"Nowak","phoneNumber":"111222333"}' -H "Content-Type:application/json" http://localhost:8080/employees


Modyfikacja istniejących pracowników
curl -i -X PATCH -d '{"firstName":"Marek"}' -H "Content-Type:application/json" http://localhost:8080/employees/1


Usuwanie pracowników
curl -i -X DELETE http://localhost:8080/employees/2


Tworzenie projektów
curl -i -X POST -d '{"name":"Project 1","description":"Description 1"}' -H "Content-Type:application/json" http://localhost:8080/projects

curl -i -X POST -d '{"name":"Project 2","description":"Description 2"}' -H "Content-Type:application/json" http://localhost:8080/projects


Modyfikacja projektów
curl -i -X PUT -d '{"name":"Project 1","description":"Important project"}' -H "Content-Type:application/json" http://localhost:8080/projects/1


Usuwanie projektów
curl -i -X DELETE http://localhost:8080/projects/2


Przypisanie pracownika do projektu
curl -i -X POST -H "Content-Type:text/uri-list" -d 'http://localhost:8080/employees/1' http://localhost:8080/projects/1/employees


Przypisanie pracownikowi godzin przepracowanych w projekcie
curl -i -X PUT -H "Content-Type:application/json" -d '{"hours":10}' http://localhost:8080/work-time-records/1_1


Usuwanie pracownika z projektu
curl -i -X DELETE http://localhost:8080/projects/1/employees/1


Raport i statystyki można wyświetlać w przeglądarce:
http://localhost:8080/report





