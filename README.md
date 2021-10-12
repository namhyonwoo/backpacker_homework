# backpacker_homework
## 백패커/아이디어스 개발과제
```개발환경 JAVA, SPRING BOOT 2.5, GRADLE 7```
### 자바 패키지 
  - webapp\src\main\java\com\backpacker\homework
- controller
  - 컨트롤러와 컨트롤러 어드바이스
- domain
  - 회원(member), 주문(order)의 도메인 객체
- service
  - 서비스 객체
- repository
  - repository 인터페이스와 구현체 (jpa, spring-data-jpa)
- test
  - repository와 service 테스트 코드
### 로그인
  - cookie session기반
### 데이타베이스 
  - mysql로 작업
  - table 생성 sql: 패키지 내의 schema.sql. (더미 데이터는 넣지 않았습니다)
### api문서
- swagger-ui: http://localhost:8080/swagger-ui
