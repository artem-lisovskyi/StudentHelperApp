-- Користувачі
CREATE TABLE users
(
    id       INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    role     VARCHAR(20)         NOT NULL
);
-- Додавання тестових даних до користувачів
INSERT INTO users (username, password, email, role)
VALUES ('user', 'user', 'user@test.com', 'USER');




-- Новини та оголошення
CREATE TABLE news
(
    id         INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    image_url  TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- Додавання тестових даних до новин
INSERT INTO news (title, content, image_url, created_at)
VALUES ('Стипендії для відмінників',
        'Студенти, які мають середній бал вище 90, можуть подати заявку на підвищену стипендію.',
        'https://pon.org.ua/uploads/posts/2022-09/stypendii-22.jpg',
        NOW()),

       ('Додаткові консультації перед іспитами',
        'Викладачі проведуть додаткові заняття для підготовки до сесії. Перелік консультацій доступний у деканаті.',
        'https://mudra.ua/assets/cache/images/f/f4ec97e6fdce59a006292c977cd603ee.jpg',
        NOW()),

       ('Оновлення бібліотечного фонду',
        'До бібліотеки надійшли нові підручники з програмування та аналізу даних.',
        'https://bzh.life/static/ckef/img/2023/she5-1.jpg',
        NOW()),

       ('Реєстрація на весняний семестр',
        'Студенти можуть зареєструватися на курси весняного семестру до 10 березня.',
        'https://storage1b.censor.net/images/3/b/a/5/3ba58913d980826b441064be3fd159fb/original.jpg',
        NOW()),

       ('День відкритих дверей',
        'Запрошуємо всіх бажаючих на День відкритих дверей 15 березня. Ви зможете познайомитися з викладачами та студентами.',
        'https://lh3.googleusercontent.com/proxy/ppoYCvTVDduLLE6eh1w8MqxdbGUSxnfV7HuxByHh_lloDwSaEFdylsU7NRqbm9hWu5KaUI07sVrcKVRwu3xi8AhOq1qBd3nj4vvcw7KCnKAH5Io',
        NOW());