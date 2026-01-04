# 🌐 Nimble Gateway – API Gateway
 
API Gateway desenvolvida para orquestrar e gerenciar as comunicações entre os microsserviços da plataforma Nimble. Atua como ponto único de entrada, fornecendo roteamento, balanceamento de carga, autenticação e monitoramento centralizado.
 
## 🚀 Funcionalidades Principais
 
- **🔀 Roteamento Inteligente**
  - Roteamento dinâmico para microsserviços
  - Balanceamento de carga
  - Circuit breaker para resiliência
 
- **🔒 Segurança Centralizada**
  - Autenticação JWT
  - Rate limiting por serviço/usuário
  - Filtros de segurança HTTP
 
- **📊 Monitoramento**
  - Métricas em tempo real
  - Logs centralizados
  - Rastreamento de requisições distribuídas
 
- **⚡ Performance**
  - Cache de respostas
  - Compressão de payload
  - Timeout configurável por rota
 
## 🛠️ Tecnologias Utilizadas
 
- **Gateway:** Spring Cloud Gateway
- **Service Discovery:** Eureka
- **Load Balancer:** Spring Cloud LoadBalancer
- **Circuit Breaker:** Resilience4j
- **Autenticação:** JWT
- **Containerização:** Docker
- **Monitoramento:** Prometheus e Grafana
- **Orquestração:** Kubernetes
 
## 🏗️ Arquitetura
```
Nimble Gateway
├── API Gateway (Spring Cloud Gateway)
│   ├── Filtros de Roteamento
│   ├── Filtros de Segurança
│   └── Filtros de Log/Monitoramento
├── Service Discovery (Eureka)
└── Config Server (opcional)
```


## 🚀 Como Executar

### Pré-requisitos

- Java 17
- Maven 3.8+
- Docker e Docker Compose

### Configuração

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/VictorDevWakanda/API-Gateway.git
   cd nimble-gateway

   ```

2. **Build do projeto:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Autor

**João Victor Mota**

- 💼 [LinkedIn](https://www.linkedin.com/in/jvnmdev/)
- 📧 victormota_03@hotmail.com

---

Sinta-se à vontade para entrar em contato para dúvidas, sugestões ou oportunidades!