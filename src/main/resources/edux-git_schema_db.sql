--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: student_subject_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE student_subject_seq
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: student_subject; Type: TABLE; Schema: public; ; Tablespace: 
--

CREATE TABLE student_subject (
    ss_id integer DEFAULT nextval('student_subject_seq'::regclass) NOT NULL,
    ss_student_id integer NOT NULL,
    ss_subject_id integer NOT NULL
);



--
-- Name: student_task_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE student_task_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: student_task; Type: TABLE; Schema: public; ; Tablespace: 
--

CREATE TABLE student_task (
    st_id integer DEFAULT nextval('student_task_seq'::regclass) NOT NULL,
    st_repository character varying,
    st_points real DEFAULT 0 NOT NULL,
    st_student_id integer NOT NULL,
    st_task_id integer NOT NULL
);



--
-- Name: subject_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE subject_seq
    START WITH 3
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: subject; Type: TABLE; Schema: public; ; Tablespace: 
--

CREATE TABLE subject (
    s_id integer DEFAULT nextval('subject_seq'::regclass) NOT NULL,
    s_name character varying(255) NOT NULL,
    s_code character varying(255) NOT NULL,
    s_status character varying(255) NOT NULL,
    s_teacher_id integer NOT NULL
);

--
-- Name: task_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE task_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: task; Type: TABLE; Schema: public; ; Tablespace: 
--

CREATE TABLE task (
    t_id integer DEFAULT nextval('task_seq'::regclass) NOT NULL,
    t_name character varying NOT NULL,
    t_description text NOT NULL,
    t_start_date timestamp without time zone NOT NULL,
    t_stop_date timestamp without time zone NOT NULL,
    t_max_points real NOT NULL,
    t_subject_id integer NOT NULL
);


--
-- Name: user_profile_seq; Type: SEQUENCE; Schema: public; 
--

CREATE SEQUENCE user_profile_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: user_profile; Type: TABLE; Schema: public; ; Tablespace: 
--

CREATE TABLE user_profile (
    up_id integer DEFAULT nextval('user_profile_seq'::regclass) NOT NULL,
    up_email character varying(255) NOT NULL,
    up_password character varying(255) NOT NULL,
    up_enabled boolean NOT NULL,
    up_first_name character varying(255) NOT NULL,
    up_last_name character varying(255) NOT NULL,
    up_authority character varying(255) NOT NULL,
    up_github_account character varying(255),
    up_rid text
);


--
-- Name: pk_student_subject; Type: CONSTRAINT; Schema: public; ; Tablespace: 
--

ALTER TABLE ONLY student_subject
    ADD CONSTRAINT pk_student_subject PRIMARY KEY (ss_id);


--
-- Name: pk_student_task; Type: CONSTRAINT; Schema: public; ; Tablespace: 
--

ALTER TABLE ONLY student_task
    ADD CONSTRAINT pk_student_task PRIMARY KEY (st_id);


--
-- Name: pk_subject; Type: CONSTRAINT; Schema: public; ; Tablespace: 
--

ALTER TABLE ONLY subject
    ADD CONSTRAINT pk_subject PRIMARY KEY (s_id);


--
-- Name: pk_task; Type: CONSTRAINT; Schema: public; ; Tablespace: 
--

ALTER TABLE ONLY task
    ADD CONSTRAINT pk_task PRIMARY KEY (t_id);


--
-- Name: pk_user_profile; Type: CONSTRAINT; Schema: public; ; Tablespace: 
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT pk_user_profile PRIMARY KEY (up_id);


--
-- Name: uq_up_email; Type: CONSTRAINT; Schema: public; ; Tablespace: 
--

ALTER TABLE ONLY user_profile
    ADD CONSTRAINT uq_up_email UNIQUE (up_email);


--
-- Name: fk_ss_student; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY student_subject
    ADD CONSTRAINT fk_ss_student FOREIGN KEY (ss_student_id) REFERENCES user_profile(up_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_ss_subject; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY student_subject
    ADD CONSTRAINT fk_ss_subject FOREIGN KEY (ss_subject_id) REFERENCES subject(s_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_student_st; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY student_task
    ADD CONSTRAINT fk_student_st FOREIGN KEY (st_student_id) REFERENCES user_profile(up_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_task_st; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY student_task
    ADD CONSTRAINT fk_task_st FOREIGN KEY (st_task_id) REFERENCES task(t_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: fk_task_subject; Type: FK CONSTRAINT; Schema: public; 
--

ALTER TABLE ONLY task
    ADD CONSTRAINT fk_task_subject FOREIGN KEY (t_subject_id) REFERENCES subject(s_id);


--
-- Name: public; Type: ACL; Schema: -; 
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

