# Рекомендації для розробників проекту JunieMVC

## 🚀 Технологічний стек
- **Java 21**
- **Spring Boot 4.x** (Spring Data JPA, Spring Web)
- **H2 Database** (для розробки та тестів)
- **Flyway** (міграції бази даних)
- **Lombok** (генерація коду)
- **MapStruct** (мапінг об'єктів)
- **Maven** (збірка проекту)

## 📁 Структура проекту
- `src/main/java/.../entities`: Сутності бази даних (JPA).
- `src/main/java/.../repositories`: Інтерфейси Spring Data JPA для доступу до даних.
- `src/main/java/.../services`: Бізнес-логіка (інтерфейси та реалізації).
- `src/main/java/.../controller`: REST контролери.
- `src/main/resources/db/migration`: Скрипти Flyway для міграції БД.

## 🛠 Запуск та тестування
### Запуск проекту
Використовуйте Maven wrapper:
```powershell
./mvnw spring-boot:run
```

### Запуск тестів
- **Усі тести:** `./mvnw test`
- **Unit тести (Services):** Знаходяться в `src/test/java/.../services`
- **Slicing тести (Data JPA):** Знаходяться в `src/test/java/.../repositories`, позначені `@DataJpaTest`.
- **Web Layer тести:** Знаходяться в `src/test/java/.../controller`, використовують `MockMvc` та `@WebMvcTest`.

## 💡 Найкращі практики
1. **Lombok:** Використовуйте анотації `@Data`, `@Builder`, `@RequiredArgsConstructor` для зменшення шаблонного коду.
2. **Профілі:** Використовуйте профіль `test` для запусків тестів (налаштовано в `src/test/resources/application-test.properties`).
3. **Сервісний шар:** Вся бізнес-логіка має бути в сервісах. Контролери лише делегують виклики.
4. **DTO:** Для передачі даних між шарами рекомендується використовувати DTO та MapStruct для конвертації.
5. **Тестування:** 
   - Використовуйте `AssertJ` для перевірок (assertions).
   - Використовуйте `Mockito` для стабів (mocks) у тестах контролерів.
