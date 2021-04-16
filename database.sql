DROP TABLE IF EXISTS public.transactions;
DROP TABLE IF EXISTS public.accounts;
DROP TABLE IF EXISTS public.clients;

CREATE TABLE public.clients
(
    id bigserial NOT NULL,
    name character(256) NOT NULL,
    surname character(256) NOT NULL,
    patronymic character(256) NOT NULL,
    address character(256),
    age smallint,
    CONSTRAINT clients_pkey PRIMARY KEY (id)
);

ALTER TABLE public.clients
    OWNER to postgres;

CREATE TABLE public.accounts
(
    id bigserial NOT NULL,
    clients_id bigint NOT NULL,
    balance double precision NOT NULL,
    "number" character(30) NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (id),
    CONSTRAINT fk_accounts FOREIGN KEY (clients_id)
        REFERENCES public.clients (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.accounts
    OWNER to postgres;

CREATE TABLE public.transactions
(
    id bigserial NOT NULL,
    oper_type character(1) NOT NULL,
    account_snd bigint,
    account_rcv bigint NOT NULL,
    amount double precision NOT NULL,
    comment character(256),
    oper_date time without time zone NOT NULL,
    CONSTRAINT pk_transactions PRIMARY KEY (id),
    CONSTRAINT fk_transactions_rcv FOREIGN KEY (account_rcv)
        REFERENCES public.accounts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_transactions_snd FOREIGN KEY (account_snd)
        REFERENCES public.accounts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.transactions
    OWNER to postgres;

insert into clients (name, surname, patronymic, address, age)  values ('Иванов','Иван','Иванович','г. Москва, ул. Ленина, 156, кв. 44',30),
                                                                      ('Петров','Петр','Петрович','г. Санкт-Петрбург, проспект Мира, 81, кв. 10',21),
                                                                      ('Сидоров','Сидор','Сидорович','г. Казань, ул. Друждбы народов, 10, кв. 194',41),
                                                                      ('Павлов','Павел','Павлович','г. Воронеж, ул. Строителей, 56, кв. 414',43),
                                                                      ('Михайлов','Михаил','Михайлович','г. Екатеринбург, ул. Светлая, 41, кв. 64',22),
                                                                      ('Андреев','Андрей','Андреевич','г. Уфа, ул. Молодежная, 7, кв. 155',50),
                                                                      ('Романов','Роман','Романович','г. Калининград, ул. Энергетиков, 20, кв. 1',14);

insert into accounts (clients_id, balance, number) VALUES (1, 10000, 100200300400),
							  (1, 11000, 200300400500),
                                                          (1, 40000, 300400500600),
                                                          (2, 40000, 400500600700),
                                                          (2, 20000, 500600700800),
                                                          (2, 59000, 600700800900),
                                                          (3, 11000, 700800900000),
                                                          (3, 43000, 800900000100),
                                                          (4, 19000, 000100200300),
                                                          (5, 10000, 102203304405),
                                                          (6, 30000, 106207308409),
                                                          (7, 60000, 110221312413),
                                                          (7, 60000, 114215316418);

insert into transactions (oper_type, account_snd, account_rcv, amount, comment, oper_date)
        VALUES ('r', null, 1, 10000, 'зарплата','2021-03-01 13:00:00'),
               ('r', null, 2, 11000, 'зарплата','2021-03-08 13:00:00'),
               ('r', null, 3, 50000, 'зарплата','2021-03-07 13:00:00'),
               ('r', null, 4, 40000, 'зарплата','2021-03-04 13:00:00'),
               ('r', null, 5, 20000, 'зарплата','2021-03-05 13:00:00'),
               ('r', null, 6, 50000, 'зарплата','2021-03-03 13:00:00'),
               ('r', null, 7, 11000, 'зарплата','2021-03-02 13:00:00'),
               ('r', null, 8, 44000, 'зарплата','2021-03-01 13:00:00'),
               ('r', null, 9, 20000, 'зарплата','2021-03-05 13:00:00'),
               ('r', null, 10, 10000, 'зарплата','2021-02-28 13:00:00'),
               ('r', null, 11, 30000, 'зарплата','2021-03-02 13:00:00'),
               ('r', null, 12, 60000, 'зарплата','2021-03-05 13:00:00'),
		               ('r', null, 13, 60000, 'зарплата','2021-03-05 13:00:00');


insert into transactions (oper_type, account_snd, account_rcv, amount, comment, oper_date)
VALUES ('w', 1, 4, 1000, 'на чай','2021-03-02 13:00:00'),
       ('w', 2, 5, 100, 'в долг','2021-03-09 13:00:00'),
       ('w', 3, 6, 500, 'возврат','2021-03-08 13:00:00'),
       ('w', 4, 7, 400, '','2021-03-05 13:00:00'),
       ('w', 5, 8, 200, 'за товар','2021-03-06 13:00:00'),
       ('w', 6, 9, 500, '','2021-03-04 13:00:00'),
       ('w', 7, 9, 110, 'в подарок','2021-03-04 13:00:00'),
       ('w', 8, 10, 440, 'на др','2021-03-04 13:00:00'),
       ('w', 9, 11, 200, '','2021-03-06 13:00:00'),
       ('w', 10, 12, 100, '','2021-03-08 13:00:00'),
       ('w', 11, 1, 300, '','2021-03-04 13:00:00'),
       ('w', 12, 2, 600, 'жене','2021-03-06 13:00:00');