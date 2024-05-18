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
-- Data for Name: bank_user; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.bank_user (id, balance) FROM stdin;
1	6
\.


--
-- Name: bank_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: user
--

SELECT pg_catalog.setval('public.bank_user_id_seq', 1, true);


--
-- Name: bank_user bank_user_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.bank_user
    ADD CONSTRAINT bank_user_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

