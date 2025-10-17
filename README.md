# Demo BankX - Aplicación Bancaria Reactiva

Este proyecto es una aplicación bancaria desarrollada con webflux y mongodb

## Descripción

La aplicación implementa un sistema bancario básico con las siguientes características:

## Configuración inicial

La aplicación incluye un seeder que inicializa:

**Reglas de riesgo:**
- PEN: límite máximo de débito 1500
- USD: límite máximo de débito 500

**Cuentas de prueba:**
- 001-0001: Ana Peru, 2000 PEN
- 001-0002: Luis Acuña, 800 PEN

## Comandos para ejecutar

### Ejecutar tests
```bash
./mvnw test
```
## API Endpoints

### Crear transacción
```bash
POST http://localhost:8084/api/transactions
Content-Type: application/json

{
  "accountNumber": "001-0001",
  "type": "DEBIT",
  "amount": 100.50
}
```

### Consultar historial de transacciones
```bash
GET http://localhost:8084/api/transactions?accountNumber=001-0001
```

### Stream de transacciones en tiempo real
```bash
GET http://localhost:8080/api/stream/transactions
Accept: text/event-stream
```

## Ejemplos de prueba

### Transacción exitosa
```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"accountNumber": "001-0001", "type": "DEBIT", "amount": 500}'
```

### Error por límite de riesgo
```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"accountNumber": "001-0001", "type": "DEBIT", "amount": 2000}'
```

### Error por fondos insuficientes
```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{"accountNumber": "001-0002", "type": "DEBIT", "amount": 1000}'
```