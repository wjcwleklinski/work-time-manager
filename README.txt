Usage examples:

Create employee
curl -i -X POST -d '{"firstName":"Jan","lastName":"Kowalski","phoneNumber":"123456123"}' -H "Content-Type:application/json" http://localhost:8080/employees

curl -i -X POST -d '{"firstName":"Adam","lastName":"Nowak","phoneNumber":"111222333"}' -H "Content-Type:application/json" http://localhost:8080/employees


Modify existing employee
curl -i -X PATCH -d '{"firstName":"Marek"}' -H "Content-Type:application/json" http://localhost:8080/employees/1


Delete employee
curl -i -X DELETE http://localhost:8080/employees/2


Create project
curl -i -X POST -d '{"name":"Project 1","description":"Description 1"}' -H "Content-Type:application/json" http://localhost:8080/projects

curl -i -X POST -d '{"name":"Project 2","description":"Description 2"}' -H "Content-Type:application/json" http://localhost:8080/projects


Modify project
curl -i -X PUT -d '{"name":"Project 1","description":"Important project"}' -H "Content-Type:application/json" http://localhost:8080/projects/1


Delete Project
curl -i -X DELETE http://localhost:8080/projects/2


Assign employee to project
curl -i -X POST -H "Content-Type:text/uri-list" -d 'http://localhost:8080/employees/1' http://localhost:8080/projects/1/employees


Edit work hours of employee in project
curl -i -X PUT -H "Content-Type:application/json" -d '{"hours":10}' http://localhost:8080/work-time-records/1_1


Delete employee from project
curl -i -X DELETE http://localhost:8080/projects/1/employees/1


Reports
http://localhost:8080/report





