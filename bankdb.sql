--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16rc1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bank_operation; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.bank_operation (
    id integer NOT NULL,
    cur_operation character varying(255),
    date_operation date,
    id_person integer,
    money double precision
);


ALTER TABLE public.bank_operation OWNER TO "user";

--
-- Name: bank_operation_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

CREATE SEQUENCE public.bank_operation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.bank_operation_id_seq OWNER TO "user";

--
-- Name: bank_operation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: user
--

ALTER SEQUENCE public.bank_operation_id_seq OWNED BY public.bank_operation.id;


--
-- Name: bank_user; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.bank_user (
    id integer NOT NULL,
    balance double precision
);


ALTER TABLE public.bank_user OWNER TO "user";

--
-- Name: bank_user_id_seq; Type: SEQUENCE; Schema: public; Owner: user
--

ALTER TABLE public.bank_user ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.bank_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: bank_operation id; Type: DEFAULT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.bank_operation ALTER COLUMN id SET DEFAULT nextval('public.bank_operation_id_seq'::regclass);


--
-- Data for Name: bank_operation; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.bank_operation (id, cur_operation, date_operation, id_person, money) FROM stdin;
3	снятие	2024-06-01	1	20
1	пополнение	2024-05-01	1	10
2	пополнение	2024-05-03	3	20
4	перевод на id : 3	2024-06-01	1	30
5	перевод на id : 1	2024-06-01	3	80
\.


--
-- Data for Name: bank_user; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.bank_user (id, balance) FROM stdin;
2	20
3	30
1	80
\.


--
-- Name: bank_operation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.bank_operation_id_seq', 5, true);


--
-- Name: bank_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.bank_user_id_seq', 3, true);


--
-- Name: bank_operation bank_operation_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.bank_operation
    ADD CONSTRAINT bank_operation_pkey PRIMARY KEY (id);


--
-- Name: bank_user bank_user_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.bank_user
    ADD CONSTRAINT bank_user_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

