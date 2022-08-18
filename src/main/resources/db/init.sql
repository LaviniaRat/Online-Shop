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
      address_id integer NOT NULL,
      role text  NOT NULL,
      phone integer
  )

  create sequence product_id_seq start with 21

  create table featuredProducts(
  id int,
  product_id int
  )