## UserApi

Prosta aplikacja łącząca się do REST API GitHuba, pobierająca dane użytkownika o zadanym loginie, mapujące dane na wewnętrzny obiekt oraz wykonująca obliczenie biznesowe.

### Budowanie programu

Proces budowy rozpoczynamy wpisując komende w folderze głównym: <br/>
`mvn clean install` <br/>
w przypadku braku narzędzia maven można skorzystać z wersji przesyłanej wraz z kodem: <br/>
`./mvnw clean install` <br/>

### Testowanie programu

Proces testowania rozpoczynamy wpisując komende w folderze głównym: <br/>
`mvn clean test` <br/>

### Startowanie Aplikacji

Po zakończeniu procesu budowy pojawi się folder target, a w nim plik
UserApi-0.0.1-SNAPSHOT.jar <br/>
Aplikacje można wystartować poleceniem: <br/>
`java -jar UserApi-0.0.1-SNAPSHOT.jar`<br/>
Aplikacja do działania nie potrzebuje zewnętrznego serwera aplikacyjnego (serwer wbudowany jest w
projekt).<br/>
Aplikacja domyślnie uruchamia się na porcie 8080, aby zmienic port należy wpisać wartość portu w
pliku application.properties

### Użytkowanie aplikacji

Aby skorzystać z aplikacji nalerzy wysłać rządanie GET pod adres localhost:8080/user/{username}
