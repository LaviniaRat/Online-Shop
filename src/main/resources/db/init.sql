--CREATE DATABASE
CREATE DATABASE ONLINESHOP

--CREATE TABLES

CREATE TABLE PRODUCT
(
    id integer NOT NULL,
    title text  NOT NULL,
    description text  NOT NULL,
    image integer NOT NULL,
    price integer NOT NULL,
    gender character varying(50)
)

CREATE TABLE ADDRESS
(
    id integer NOT NULL,
    address character(50)  NOT NULL,
    city character(50) NOT NULL,
    country character(50)  NOT NULL,
    postal_code integer NOT NULL
  )

  CREATE TABLE IMAGES
  (
      id integer NOT NULL,
      product_id integer NOT NULL,
      image_id integer
  )

  CREATE TABLE PRODUCT_ORDER
  (
      id integer NOT NULL,
      product_id integer NOT NULL,
      count integer NOT NULL,
      price integer NOT NULL
  )

  CREATE TABLE SHOPPING_CART
  (
      id integer NOT NULL,
      order_id integer NOT NULL,
      user_id integer NOT NULL,
      date date NOT NULL
  )

  CREATE TABLE USERS
  (
      id integer NOT NULL,
      first_name text NOT NULL,
      last_name text  NOT NULL,
      email text  NOT NULL,
      password text  NOT NULL,
      role text  NOT NULL,
      phone integer
  )

  create sequence product_id_seq start with 24
  create sequence category_id_seq start with 21
  create sequence user_id_seq start with 2
  create sequence address_id_seq start with 2
  create sequence id start with 1
  create sequence customer_id_seq start with 2
  create sequence cart_id_seq start with 1
  create sequence order_id_seq start with 1

  create table featuredProducts(
      id int,
      product_id int
  )

 create table cartItem(
 cart_id int primary key not null,
 product_id int,
 quantity int,
 order_id int
 )

    create table customers(
    id int primary key not null,
    name varchar(50),
    email varchar(50),
    address varchar(200),
    city varchar(50),
    country varchar(50),
    zip int
    )