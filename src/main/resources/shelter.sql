-- Таблица информации о приюте
-- id приюта,
-- информация о приюте,
-- адрес приюта,
-- телефон приюта,
-- схема проезда (ссылка на яндекс.карты проезда),
-- режим работы приюта,
-- правила пропуска на территорию приюта,
-- правила нахождения на территории приюта,
-- контакты охраны для пропуска авто,
-- правила общения с животным,
-- правила знакомства с животным,
-- список документов для усыновления животного из приюта,
-- рекомендации по транспортировке,
-- рекомендации по обустройству для щеноков/котят,
-- рекомендации по обустройству для взрослыхе животных,
-- рекомендации по обустройству для больных животных,
-- советы кинолога, (только для собак и щенков)
-- возможные причины отказа в усыновлении

create table shelter
(
    id                    bigserial not null primary key,
    information           text,
    address               varchar(255),
    phone                 varchar(255),
    driving_link          varchar(255),
    working_hours         varchar(255),
    territory_admission   text,
    territory_staying     text,
    security_contacts     varchar(255),
    animal_acquaintance   text,
    document_list         text,
    travel_recommendation text,
    child_arranging       text,
    adult_arranging       text,
    sick_arrangement      text,
    handler_advice        text,
    refusal_reason        text
);

-- Таблица усыновителей
-- id усыновителя,
-- фио усыновителя,
-- контакты усыновителя
-- предпочтительное время для связи
-- animal_id усыновленного животного dog/cat

create table users
(
    id           bigserial not null primary key,
    user_name    varchar(255),
    user_contact varchar(255),
    call_time    varchar(255),
    animal_id    bigint
);

-- Таблица проверенных кинологов
-- id
-- фио
-- контакты
-- предпочтительное время для звонка
-- организация
-- дополнительная информация (личные дипломы, достяжения кинолога)

create table handler
(
    id        bigserial not null primary key,
    surname   varchar(255),
    contact   varchar(255),
    call_time varchar(255),
    company   varchar(255),
    add_info  text
);

-- Таблица животных (собаки и щенки)
-- id
-- кличка
-- возраст животного
-- особенности
-- больная
-- дата усыновления

create table dog
(
    id       bigserial not null primary key,
    nickname varchar(255),
    age      integer,
    feature  text,
    illness  boolean,
    adoption date
);

-- Таблица животных (кошки и котята)
-- id
-- кличка
-- возраст животного
-- особенности
-- больная
-- дата усыновления

create table cat
(
    id       bigserial not null primary key,
    nickname varchar(255),
    age      integer,
    feature  text,
    illness  boolean,
    adoption date
);

-- Таблица отчеты о содержании животных, взятых из приюта
-- id
-- дата и время отчета
-- id усыновителя
-- id животного
-- id фото животного на дату отчета
-- рацион животного
-- общее самочувствие животного
-- изменения в поведении животного

create table report
(
    id          bigserial not null primary key,
    report_time timestamp,
    adopter_id  bigint,
    animal_id   bigint,
    photo_id    bigint,
    diet        text,
    health      text,
    behavior    text
);

-- Таблица фото животных, взятых из приюта
-- id
-- дата и время фото
-- id животного
-- фотография животного

create table photo
(
    id         bigserial not null primary key,
    animal_id  bigint,
    photo_time timestamp,
    photo      bytea
);

-- мои структуры. Когда скорректируешь, то лишнее уберешь
-- Таблицы cats и dogs
-- id
-- кличка
-- порода
-- возраст
-- описание
CREATE TABLE cats
(
    id          BIGINT generated by default as identity primary key,
    nick_name   TEXT   NOT NULL,
    breed       TEXT   NOT NULL,
    age         BIGINT NOT NULL,
    description TEXT   NOT NULL
);

CREATE TABLE dogs
(
    id          BIGINT generated by default as identity primary key,
    nick_name   TEXT   NOT NULL,
    breed       TEXT   NOT NULL,
    age         BIGINT NOT NULL,
    description TEXT   NOT NULL
);


-- Таблицы cat_photo и dog_photo
-- id
-- название файла
-- размер файла
-- тип файла
-- массив для хранения фотографии

CREATE TABLE cat_photo
(
    id         BIGINT generated by default as identity primary key,
    file_path  TEXT   NOT NULL,
    file_size  BIGINT NOT NULL,
    media_type TEXT   NOT NULL,
    photoDog   oid    NOT NULL,
    cat_id     bigint
);
CREATE TABLE dog_photo
(
    id         BIGINT generated by default as identity primary key,
    file_path  TEXT   NOT NULL,
    file_size  BIGINT NOT NULL,
    media_type TEXT   NOT NULL,
    photoDog   oid    NOT NULL,
    dog_id     bigint
);

