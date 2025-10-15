# OCI Kafka Spring Boot Demo

Oracle Cloud Infrastructure (OCI) Kafka와 Spring Boot를 사용한 실시간 메시징 데모 애플리케이션

## 🚀 기능

- **Kafka Producer**: REST API를 통한 메시지 발송
- **Kafka Consumer**: 실시간 메시지 수신 
- **WebSocket**: STOMP over SockJS를 사용한 실시간 브로드캐스팅
- **Web UI**: 메시지 입력 및 실시간 표시

## 🛠️ 기술 스택

- Spring Boot 3.5.6
- Java 17
- Maven
- Spring Kafka
- Spring WebSocket
- Thymeleaf
- SockJS + STOMP

## 📦 OCI VM에서 실행 방법

### 1. 프로젝트 클론
```bash
git clone https://github.com/AndyPandi/oci-kafka-springboot.git
cd oci-kafka-springboot
```

### 2. Kafka 설정 확인
`src/main/resources/application.yml` 파일에서 Kafka 연결 정보 확인:
```yaml
spring:
  kafka:
    bootstrap-servers: bootstrap-clstr-o6zxkfmtekqncrpt.kafka.ap-chuncheon-1.oci.oraclecloud.com:9092
    properties:
      security.protocol: SASL_PLAINTEXT
      sasl.mechanism: SCRAM-SHA-512
      sasl.jaas.config: org.apache.kafka.common.security.scram.ScramLoginModule required username="super-user-o6zxkfmtekqncrpt" password="fcaa1140-8e4d-45fc-8e0b-f9d649cf20fa";
```

### 3. 애플리케이션 실행
```bash
./mvnw spring-boot:run
```

### 4. 웹 브라우저에서 테스트
- URL: `http://VM_IP:8080`
- 메시지 입력 후 실시간으로 수신 확인

## 🔧 아키텍처

```
Browser → REST API (/api/publish) → Kafka Producer → Kafka Topic
                                                          ↓
Browser ← WebSocket (/topic/messages) ← Kafka Consumer ←
```

## 📝 주요 파일

- `KafkaProducerService.java`: Kafka 메시지 발송
- `KafkaConsumerService.java`: Kafka 메시지 수신 및 WebSocket 브로드캐스트  
- `MessageController.java`: REST API 엔드포인트
- `WebSocketConfig.java`: WebSocket/STOMP 설정
- `index.html`: 프론트엔드 UI

## 🐛 트러블슈팅

### Kafka 연결 오류
- OCI VCN 내부에서만 접속 가능
- 보안 그룹에서 포트 9092 허용 확인
- DNS 해결 문제 시 IP 주소 직접 사용

### 애플리케이션 시작 오류
- Java 17 설치 확인
- Maven wrapper 실행 권한 확인: `chmod +x mvnw`
