# inflearn_KimYoungHan_spring_advanced

## 로그 추적기
### 요구 사항
- 모든 PUBLIC 메서드의 호출과 응답 정보를 로그로 출력

- 애플리케이션의 흐름을 변경하면 안됨
  - 로그를 남긴다고 해서 비즈니스 로직의 동작에 영향을 주면 안됨
  
- 메서드 호출에 걸린 시간 (0000ms)

- 정상 흐름과 예외 흐름 구분
  - 예외 발생시 예외 정보가 남아야 함
  
- 메서드 호출의 깊이 표현
```
정상 요청
[796bccd9] OrderController.request()
[796bccd9] |-->OrderService.orderItem()
[796bccd9] |   |-->OrderRepository.save()
[796bccd9] |   |<--OrderRepository.save() time=1004ms
[796bccd9] |<--OrderService.orderItem() time=1014ms
[796bccd9] OrderController.request() time=1016ms

예외 발생
[b7119f27] OrderController.request()
[b7119f27] |-->OrderService.orderItem()
[b7119f27] |   |-->OrderRepository.save()
[b7119f27] |   |<X-OrderRepository.save() time=0ms ex=java.lang.IllegalStateException: 예외 발생!
[b7119f27] |<X-OrderService.orderItem() time=10ms ex=java.lang.IllegalStateException: 예외 발생!
[b7119f27] OrderController.request() time=11ms ex=java.lang.IllegalStateException: 예외 발생!
```
> [796bccd9] 이런 것이 트랜잭션ID 단위 (DB 트랜잭션이 아님)

- HTTP 요청을 구분
  - HTTP 요청 단위로 특정 ID를 남겨서 어떤 HTTP 요청에서 시작된 것인지 명확하게 구분이 가능해야 함
  - 트랜잭션 ID (DB 트랜잭션X), 여기서는 하나의 HTTP 요청이 시작해서 끝날 때 까지를 하나의 트랜잭션이라 함



<br>
<hr>
<br>

### 강의 끝나고 다 이해했는지 스스로 체크해볼 것
```
지금: TraceId를 파라미터로 넘기네? 불편하다
 → ThreadLocal로 해결하네
  → 근데 로그 코드가 비즈니스에 침투하네
   → 프록시/AOP로 분리하네
    → 이게 Spring AOP 원리구나
     → 현업에서는 OpenTelemetry가 이걸 자동으로 해주는구나
      → 그 데이터를 Jaeger/Zipkin이 수집하는구나
       → Grafana로 보는구나
```
