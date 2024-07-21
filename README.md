# UMC_교환하자_BE
UMC_교환하자_BE

project : Gradle-groovy
version : 3.3.2
JAVA 17



## Dependencies ##
* Spring Web : WEB RESTful을 포함한 WEB 빌드 웹은 스프링 MVC를 사용하여 기본 내장 컨테이너로 Apache Tomcat을 사용하기 위함
* Spring Boot DevTools : 개발 중에 유용한 기능들을 제공 / ex) 코드 변경 시 자동 재시작, 캐시 비활성화 등을 통해 개발 속도를 높여줍
* Spring Data JPA : 데이터베이스와의 상호 작용을 단순화 / JPA(Java Persistence API)를 통해 객체와 관계형 데이터베이스 간의 매핑을 도와줌
* Spring Security : 애플리케이션의 보안을 강화 / 인증 및 권한 부여 기능을 제공
* H2 Database : 개발 및 테스트 환경에서 사용하기 위한 인메모리 데이터베이스
* MySQL Driver : 실제 운영 환경에서 MySQL 데이터베이스를 사용하기 위함 / 다른 데이터베이스를 사용한다면 해당 데이터베이스 드라이버를 추가할 것
* Spring Boot Actuator : 애플리케이션의 상태를 모니터링하고 관리할 수 있는 엔드포인트를 제공 /  시스템 상태 확인 및 메트릭스 노출에 유용
* Thymeleaf : !!!
* Java Mail Sender I/O : 이메일 알림 기능을 구현 / 추후에 인증 및 알림 기능 고려
* Docker Compose Support : Docker Compose를 사용하여 다중 컨테이너 개발 환경을 설정하고 관리 유용
* Testcontainers : 데이터베이스와 브라우저 테스트를 위한 임시 컨테이너를 제공하여 테스트 환경을 쉽게 설정하고 관리 유용
