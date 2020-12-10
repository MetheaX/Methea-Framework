-- DDL Script
create table if not exists tbl_core_account
(
	account_id varchar(255) not null
		constraint tbl_core_account_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	account_address varchar(255),
	account_email varchar(255) not null,
	account_name varchar(255) not null
);

create table if not exists tbl_core_client
(
	id varchar(255) not null
		constraint tbl_core_client_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	client_id varchar(255) not null
		constraint uk_n0d7a4dkt391l67wnp26vuwf2
			unique,
	client_secret varchar(1024) not null
);

create table if not exists tbl_core_datatable_view
(
	id varchar(255) not null
		constraint tbl_core_datatable_view_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	allow_filter varchar(255) not null,
	column_key varchar(255) not null,
	label_column_head varchar(255) not null,
	sequence integer not null,
	view_name varchar(255) not null
);

create table if not exists tbl_core_group
(
	group_id varchar(255) not null
		constraint tbl_core_group_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	account_id varchar(255) not null,
	group_name varchar(255) not null,
	remarks varchar(255)
);

create table if not exists tbl_core_role
(
	role_id varchar(255) not null
		constraint tbl_core_role_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	name varchar(255) not null
);

create table if not exists tbl_core_role_uri
(
	role_uri_id varchar(255) not null
		constraint tbl_core_role_uri_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	role_id varchar(255) not null,
	uri_id varchar(255) not null,
	uri_name varchar(255) not null
);

create table if not exists tbl_core_system_certificate
(
	id varchar(255) not null
		constraint tbl_core_system_certificate_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	private_key text not null,
	public_key text not null,
	code text not null
		constraint uk_54x2har12lx6l8gr24dfw6sk
			unique
);

create table if not exists tbl_core_uri
(
	uri_id varchar(255) not null
		constraint tbl_core_uri_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	uri_name varchar(255) not null
);

create table if not exists tbl_core_rm_user_perm
(
	permission_id varchar(255) not null
		constraint tbl_core_rm_user_perm_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	role_id varchar(255) not null,
	role_name varchar(255) not null,
	user_login_id varchar(255) not null
);

create table if not exists tbl_core_user
(
	user_id varchar(255) not null
		constraint tbl_core_user_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	group_id varchar(255) not null,
	password varchar(255) not null,
	username varchar(255) not null,
	email varchar(255) not null,
	first_name varchar(255) not null,
	last_name varchar(255) not null,
	phone varchar(255) not null,
	frc_usr_rst_pwd varchar(255) not null
);

create table if not exists tbl_core_user_permission
(
	permission_id varchar(255) not null
		constraint tbl_core_user_permission_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	role_user_id varchar(255) not null,
	uri_name varchar(255) not null,
	user_id varchar(255) not null,
	view_id varchar(255)
);

create table if not exists tbl_api_base
(
	api_id varchar(255) not null
		constraint tbl_api_base_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	api_url_name varchar(255) not null
);

create table if not exists tbl_core_menu
(
	menu_id varchar(255) not null
		constraint tbl_core_menu_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	group_id varchar(255) not null,
	menu_icon varchar(255) not null,
	menu_label varchar(255) not null,
	parent_menu varchar(255),
	uri_id varchar(255) not null,
	uri_name varchar(255) not null,
	group_name varchar(255) not null,
	index integer
);

create table tbl_core_white_uri
(
	white_uri_id varchar(255) not null
		constraint tbl_core_white_uri_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	uri_id varchar(255) not null,
	uri_name varchar(255) not null
);

create unique index tbl_core_white_uri_uri_name_uindex
	on tbl_core_white_uri (uri_name);

create unique index tbl_core_white_uri_uri_id_uindex
	on tbl_core_white_uri (uri_id);

create table tbl_core_allowed_method
(
	white_uri_id varchar(255) not null
		constraint fklq52pom7lg030iwu75hoojjhv
			references tbl_core_white_uri,
	http_method varchar(255)
);

-- Create account
INSERT INTO tbl_core_account (account_id, created_date_time, created_user, status, updated_date_time, updated_user, account_address, account_email, account_name) VALUES ('b5911381-e595-4c67-ad6b-080abe000ed6', '2020-04-14 20:55:44.797000', 'admin', 'A', '2020-04-16 11:15:23.155000', 'admin', 'Phnom Penh, Cambodia', 'methea@methea-mail.io', 'Methea LLC.');
INSERT INTO tbl_core_account (account_id, created_date_time, created_user, status, updated_date_time, updated_user, account_address, account_email, account_name) VALUES ('85ec05cc-f6fa-40d7-a05d-c9ca760faee3', '2020-04-21 21:38:55.864000', 'admin', 'A', '2020-05-06 10:18:42.804374', 'admin', 'Phnom Penh, Cambodia', 'actiniumllc@gmail.com', 'Actinium LLC.');

-- Create group
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('51eaa764-1807-4f4b-bec2-13471ad8a72f', '2020-10-17 15:11:01.314000', 'admin', 'A', '2020-10-17 15:11:01.314000', 'admin', 'b5911381-e595-4c67-ad6b-080abe000ed6', 'M_PUBLIC', 'Public User');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('7cfb627c-064e-4885-92cf-9659af7ee072', '2020-02-01 16:44:59.000000', 'SYS', 'A', '2020-04-18 12:33:11.192000', 'admin', 'b5911381-e595-4c67-ad6b-080abe000ed6', 'M_SYS_ADMIN', 'Methea system admin');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('c096552f-0b4f-4209-b5e4-64d3ff5b1a9e', '2020-04-26 18:56:01.252000', 'admin', 'A', '2020-04-26 18:56:01.254000', 'admin', '85ec05cc-f6fa-40d7-a05d-c9ca760faee3', 'M_SYS_SUPPORT', 'system support group');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('8a9e41e3-6bae-4ab5-bdd3-54dc6530b2f4', '2020-04-26 19:45:43.781000', 'admin', 'A', '2020-04-27 21:02:17.691000', 'admin', '85ec05cc-f6fa-40d7-a05d-c9ca760faee3', 'M_DEV_OPS', ' ');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('1e62737d-fb59-4f50-802c-48d7e03c6428', '2020-04-26 20:02:21.407000', 'admin', 'A', '2020-04-27 21:02:52.021000', 'admin', '85ec05cc-f6fa-40d7-a05d-c9ca760faee3', 'M_SOF_ENGINERE', ' ');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('9ad7537a-dbce-4e9b-b3a4-3da224432628', '2020-04-26 19:37:44.879000', 'admin', 'A', '2020-04-27 21:37:02.274000', 'admin', '85ec05cc-f6fa-40d7-a05d-c9ca760faee3', 'M_ACCOUNTING', ' ');

-- Create role
INSERT INTO tbl_core_role (role_id, created_date_time, created_user, status, updated_date_time, updated_user, name) VALUES ('354ce17a-2c55-4b62-bd2c-12afdc8050be', '2020-10-17 15:12:34.671000', 'admin', 'A', '2020-10-17 15:12:34.671000', 'admin', 'ROLE_PUBLIC');
INSERT INTO tbl_core_role (role_id, created_date_time, created_user, status, updated_date_time, updated_user, name) VALUES ('2d601157-6bb9-4117-8178-603d6551993c', '2020-02-01 16:43:21.000000', 'SYS', 'A', '2020-02-01 16:43:27.000000', 'SYS', 'ROLE_ADMIN');
INSERT INTO tbl_core_role (role_id, created_date_time, created_user, status, updated_date_time, updated_user, name) VALUES ('4dd72ac4-eae9-40dc-9048-01e392103649', '2020-05-02 11:54:51.267000', 'admin', 'A', '2020-05-02 13:50:05.269000', 'admin', 'ROLE_SYS_STARTER');

-- Create user
INSERT INTO tbl_core_user (user_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, password, username, email, first_name, last_name, phone, frc_usr_rst_pwd) VALUES ('d0b152a2-35c0-4120-ab66-635563b706ae', '2020-10-17 15:12:14.948000', 'admin', 'A', '2020-10-17 15:12:14.948000', 'admin', '51eaa764-1807-4f4b-bec2-13471ad8a72f', '$2a$10$MTXMIPYHJfhrLCOvsuME5e0r6uZVZ1NX.vPe0PglxfyQmwd236Qtq', 'PUBLIC_USER', 'public.user@methea.io', 'Public', 'User', '+85500000001', 'Y');
INSERT INTO tbl_core_user (user_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, password, username, email, first_name, last_name, phone, frc_usr_rst_pwd) VALUES ('a212eb79-592f-4af9-ab82-94d8b260edce', '2020-04-25 22:05:44.841000', 'admin', 'A', '2020-04-29 21:46:18.508000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', '$2a$10$8n9kU6aiagp016XeXUKNUOZPiz6x6Lv3z8YXhUvrv.hK.0Rc1lYMS', 'system_support', 'emily@gmail.coom', 'Emily', 'Victor', '+8559243445', 'N');
INSERT INTO tbl_core_user (user_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, password, username, email, first_name, last_name, phone, frc_usr_rst_pwd) VALUES ('afd72031-f13c-4384-90c7-930bca202974', '2020-02-01 16:35:01.000000', 'SYS', 'A', '2020-05-01 12:32:36.523000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', '$2a$10$17.6ysiFAWXV6UILkqVLCu2KEWLGxeHsA/Ffu7qxIbi8HEoX973ji', 'admin', 'methea.info@mail.io', 'Admin', 'Admin', '+855 92386749', 'N');

-- Create URI
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('84aa1832-5c58-4568-ae54-738659e6aaa1', '2020-02-01 16:46:09.000000', 'SYS', 'A', '2020-02-01 16:46:21.000000', 'SYS', '/login/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('66ab5499-bdba-4569-a850-b713bd1afbe9', '2020-02-01 16:46:08.000000', 'SYS', 'A', '2020-02-01 16:46:20.000000', 'SYS', '/logout/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('57201e94-41f5-450a-b751-d9ad28c005c5', '2020-02-01 16:46:06.000000', 'SYS', 'A', '2020-02-01 16:46:19.000000', 'SYS', '/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('63644b6a-b12c-4bc8-8932-cfd5586e1547', '2020-05-16 11:46:31.000000', 'SYS', 'A', '2020-05-16 11:46:43.000000', 'SYS', '/');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('fd7978dc-2c7c-4321-8b77-635501d6f4b5', '2020-02-01 16:46:05.000000', 'SYS', 'A', '2020-05-31 16:19:13.670121', 'admin', '/resources/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('57fa54d8-27d3-4246-bcdc-f87a6a4a5584', '2020-04-30 21:49:29.000000', 'SYS', 'A', '2020-05-31 16:52:53.512518', 'admin', '/profile/change-password/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('c26ecceb-acdf-484c-99ae-1a2748b3a746', '2020-02-01 16:46:07.000000', 'SYS', 'A', '2020-06-18 15:09:56.104222', 'admin', '/access-denied/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('2404ee87-d57b-4eb2-ac16-003dd9b93c46', '2020-06-28 18:50:22.196126', 'admin', 'A', '2020-06-28 18:50:22.196126', 'admin', '/app/accounts');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('ea7a70f1-bbf0-433c-a4fe-7c165896550b', '2020-06-28 18:50:38.295220', 'admin', 'A', '2020-06-28 18:50:38.295220', 'admin', '/app/groups');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('59dd27f1-9e52-4a39-91b2-f0602e1f3bf7', '2020-06-28 18:50:53.780755', 'admin', 'A', '2020-06-28 18:50:53.780755', 'admin', '/app/users');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('b91aa537-64c2-4eba-b954-694a5c02f504', '2020-06-28 18:51:11.873383', 'admin', 'A', '2020-06-28 18:51:11.873383', 'admin', '/app/roles');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('f232b85d-a675-4cac-914a-52e8c6c94c0a', '2020-06-28 18:51:25.466412', 'admin', 'A', '2020-06-28 18:51:25.466412', 'admin', '/app/uris');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('68af8c6a-a1d0-408d-a075-901cf462dbfc', '2020-06-28 18:51:53.797357', 'admin', 'A', '2020-06-28 18:51:53.797357', 'admin', '/app/roleURIs');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('ebb7533c-1999-4b79-889d-55d805fe7a4c', '2020-06-28 18:52:07.056753', 'admin', 'A', '2020-06-28 18:52:07.056753', 'admin', '/app/permissions');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('9ad0ab0d-ee63-4b3b-8db0-a711682ec702', '2020-06-28 18:52:23.357812', 'admin', 'A', '2020-06-28 18:52:23.357812', 'admin', '/app/system-certificate');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('ea1b5b8d-4364-47eb-9e8c-b2306f580cf2', '2020-06-28 18:52:38.420038', 'admin', 'A', '2020-06-28 18:52:38.420038', 'admin', '/app/apibases');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('68154b9a-b88b-4b79-8f4c-e48c39e64ee8', '2020-06-28 18:52:58.875554', 'admin', 'A', '2020-06-28 18:52:58.875554', 'admin', '/app/webservice-clients');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('0405b76c-7b97-4e5d-8272-7675afb12b25', '2020-06-28 18:53:18.194725', 'admin', 'A', '2020-06-28 18:53:18.194725', 'admin', '/app/datatable');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('122d912e-c69a-42d7-8ad6-76e7dea74ab5', '2020-06-28 18:53:53.892673', 'admin', 'A', '2020-06-28 18:53:53.892673', 'admin', '/app');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('f72d0592-b65d-4168-aeff-0b7d09f1a92c', '2020-06-28 18:54:40.555054', 'admin', 'A', '2020-06-28 18:54:40.555054', 'admin', '/app/clear-caches');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('b3c17b2e-fe79-43f6-b9e3-6dcb38236bdc', '2020-07-11 14:36:28.225791', 'admin', 'A', '2020-07-11 14:36:28.225791', 'admin', '/app/menu');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('65124f82-d905-424f-9dcd-762a0c9da32d', '2020-11-21 22:33:23.448000', 'admin', 'A', '2020-11-21 22:33:23.448000', 'admin', '/unauthorized-access/**');

-- Map role with URI
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('910b886f-cf7d-4bcc-8c3a-f42a88db07c6', '2020-02-01 16:49:18.000000', 'SYS', 'A', '2020-02-01 16:49:06.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', '66ab5499-bdba-4569-a850-b713bd1afbe9', '/logout/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('4f1954c4-dfda-4769-98c5-3c20fd872c90', '2020-02-01 16:49:20.000000', 'SYS', 'A', '2020-02-01 16:49:07.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', '84aa1832-5c58-4568-ae54-738659e6aaa1', '/login/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('03a042b3-32fe-4d93-a524-e1f6aad09f3e', '2020-02-01 16:49:20.000000', 'SYS', 'A', '2020-02-01 16:49:08.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', '57201e94-41f5-450a-b751-d9ad28c005c5', '/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('801f935e-0c05-4f26-b218-637c18de9f83', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-06-18 14:52:25.985603', 'admin', '4dd72ac4-eae9-40dc-9048-01e392103649', '66ab5499-bdba-4569-a850-b713bd1afbe9', '/logout/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('38aae2d5-1474-4258-9700-9b2dc1aed253', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-06-18 14:52:26.014607', 'admin', '4dd72ac4-eae9-40dc-9048-01e392103649', '63644b6a-b12c-4bc8-8932-cfd5586e1547', '/');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('4be64049-52fe-4638-b2b0-1054367228c7', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-06-18 14:52:26.014607', 'admin', '4dd72ac4-eae9-40dc-9048-01e392103649', '84aa1832-5c58-4568-ae54-738659e6aaa1', '/login/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('3c02217f-f545-41f4-a002-a365f5733997', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-06-18 14:52:26.014607', 'admin', '4dd72ac4-eae9-40dc-9048-01e392103649', 'fd7978dc-2c7c-4321-8b77-635501d6f4b5', '/resources/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('f99862a1-3dda-43a3-b796-0cb617bb8911', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-06-18 14:52:26.014607', 'admin', '4dd72ac4-eae9-40dc-9048-01e392103649', '57fa54d8-27d3-4246-bcdc-f87a6a4a5584', '/profile/change-password/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('fa384b19-1479-4222-bee3-3ac52c7e6f03', '2020-02-01 16:49:16.000000', 'SYS', 'A', '2020-02-01 16:49:04.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', 'c26ecceb-acdf-484c-99ae-1a2748b3a746', '/access-denied/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('0c33623d-510a-46d1-886c-7b0336393e3a', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-06-18 14:52:26.003320', 'admin', '4dd72ac4-eae9-40dc-9048-01e392103649', 'c26ecceb-acdf-484c-99ae-1a2748b3a746', '/access-denied/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('17036ea8-a9d9-41c0-a56a-a68d8be07b65', '2020-07-11 14:38:11.472778', 'admin', 'A', '2020-07-11 14:38:11.472778', 'admin', '2d601157-6bb9-4117-8178-603d6551993c', 'b3c17b2e-fe79-43f6-b9e3-6dcb38236bdc', '/app/menu');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('8b5c278e-ce13-403a-9654-d5f70f84e822', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-05-16 16:06:11.474881', 'admin', '2d601157-6bb9-4117-8178-603d6551993c', '57fa54d8-27d3-4246-bcdc-f87a6a4a5584', '/profile/change-password/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('664d6f54-7b0b-465b-9396-f268929f23de', '2020-02-01 16:49:19.000000', 'SYS', 'A', '2020-05-31 16:19:13.671117', 'admin', '2d601157-6bb9-4117-8178-603d6551993c', 'fd7978dc-2c7c-4321-8b77-635501d6f4b5', '/resources/**');

-- Permission view
INSERT INTO tbl_core_rm_user_perm (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, first_name, last_name, role_id, role_name, user_login_id) VALUES ('99cc9a24-be65-4919-9ebd-449ed95fa5ba', '2020-05-31 11:31:35.135234', 'admin', 'A', '2020-05-31 16:05:20.580448', 'admin', 'Admin', 'Admin', '2d601157-6bb9-4117-8178-603d6551993c', 'ROLE_ADMIN', 'admin');

-- Apply permission
INSERT INTO tbl_core_user_permission (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, role_user_id, uri_name, user_id, view_id) VALUES ('1e49c624-aa83-4b4f-90e1-de926061f9d5', '2020-02-01 17:00:08.000000', 'SYS', 'A', '2020-02-01 17:00:21.000000', 'SYS', '03a042b3-32fe-4d93-a524-e1f6aad09f3e', '/**', 'methea-client', null);
INSERT INTO tbl_core_user_permission (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, role_user_id, uri_name, user_id, view_id) VALUES ('289e9c1f-75bc-4d42-a88a-dccec7bf4a20', '2020-05-31 16:05:20.576458', 'admin', 'A', '2020-05-31 16:05:20.576458', 'admin', '910b886f-cf7d-4bcc-8c3a-f42a88db07c6', '/logout/**', 'admin', '99cc9a24-be65-4919-9ebd-449ed95fa5ba');
INSERT INTO tbl_core_user_permission (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, role_user_id, uri_name, user_id, view_id) VALUES ('efa888b4-50c2-4cb7-bdde-c2285943bd24', '2020-05-31 16:05:20.576458', 'admin', 'A', '2020-05-31 16:05:20.576458', 'admin', 'fa384b19-1479-4222-bee3-3ac52c7e6f03', '/access-denied/**', 'admin', '99cc9a24-be65-4919-9ebd-449ed95fa5ba');
INSERT INTO tbl_core_user_permission (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, role_user_id, uri_name, user_id, view_id) VALUES ('e9e4d852-ec9f-4d89-b171-a1a96b18e82a', '2020-05-31 16:05:20.576458', 'admin', 'A', '2020-05-31 16:05:20.576458', 'admin', '4f1954c4-dfda-4769-98c5-3c20fd872c90', '/login/**', 'admin', '99cc9a24-be65-4919-9ebd-449ed95fa5ba');
INSERT INTO tbl_core_user_permission (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, role_user_id, uri_name, user_id, view_id) VALUES ('ad0dedaf-c2c1-4858-a8cc-aad2c5c46560', '2020-05-31 16:05:20.577456', 'admin', 'A', '2020-05-31 16:05:20.577456', 'admin', '03a042b3-32fe-4d93-a524-e1f6aad09f3e', '/**', 'admin', '99cc9a24-be65-4919-9ebd-449ed95fa5ba');
INSERT INTO tbl_core_user_permission (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, role_user_id, uri_name, user_id, view_id) VALUES ('04084520-e95e-4c1d-af67-d6e6f399d8fb', '2020-05-31 16:05:20.577456', 'admin', 'A', '2020-05-31 16:05:20.577456', 'admin', '664d6f54-7b0b-465b-9396-f268929f23de', '/resources/**', 'admin', '99cc9a24-be65-4919-9ebd-449ed95fa5ba');
INSERT INTO tbl_core_user_permission (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, role_user_id, uri_name, user_id, view_id) VALUES ('92d7113a-35eb-47e0-b21e-c659aec3cead', '2020-05-31 16:05:20.577456', 'admin', 'A', '2020-05-31 16:05:20.577456', 'admin', '8b5c278e-ce13-403a-9654-d5f70f84e822', '/profile/change-password/**', 'admin', '99cc9a24-be65-4919-9ebd-449ed95fa5ba');

-- Config datatable
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('27b5591e-7201-4acf-9aca-479648f2de4e', '2020-02-01 16:37:32.000000', 'SYS', 'A', '2020-02-01 16:37:45.000000', 'SYS', 'Y', 'accountName', 'Account Name', 2, 'accountList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('9e0a7571-4a4d-4657-8ba7-d90cc6b64c82', '2020-02-01 16:37:33.000000', 'SYS', 'A', '2020-02-01 16:37:46.000000', 'SYS', 'Y', 'accountEmail', 'Account Email', 3, 'accountList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('bdde9c2d-7305-4627-9bc0-faecf24f85a2', '2020-02-01 16:37:35.000000', 'SYS', 'I', '2020-02-01 16:37:48.000000', 'SYS', 'N', 'accountCreatedDate', 'Created Date Time', 5, 'accountList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('0007405d-6d5f-4583-8027-f08a35a2979b', '2020-02-01 16:37:34.000000', 'SYS', 'I', '2020-02-01 16:37:47.000000', 'SYS', 'N', 'accountCreatedUser', 'Created User', 4, 'accountList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('205b2e14-c7af-483e-9746-facb8f5dece1', '2020-02-01 16:37:36.000000', 'SYS', 'I', '2020-02-01 16:37:50.000000', 'SYS', 'N', 'accountUpdatedUser', 'Updated User', 6, 'accountList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('743b0501-4f89-4b10-8d67-e6a706dea01e', '2020-02-01 16:37:37.000000', 'SYS', 'I', '2020-02-01 16:37:51.000000', 'SYS', 'N', 'accountUpdatedDate', 'Updated Date Time', 7, 'accountList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('6139c78b-215a-437c-871a-8f874d0f1581', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'status', 'Status', 8, 'accountList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('598e804e-d96f-410c-87f0-68fb3beb1549', '2020-02-01 16:37:32.000000', 'SYS', 'A', '2020-02-01 16:37:45.000000', 'SYS', 'Y', 'groupName', 'Group Name', 2, 'groupList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('b5218fe3-2275-4cc5-9980-c1f9fec33cb5', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'N', 'id', 'id', 1, 'groupList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('7f3ef137-d7a8-4dca-97c0-c3279dab567a', '2020-02-01 16:37:32.000000', 'SYS', 'A', '2020-02-01 16:37:45.000000', 'SYS', 'Y', 'status', 'Status', 4, 'groupList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('93bc41b5-7909-4c66-ac0c-34ba19f7a9f2', '2020-02-01 16:37:32.000000', 'SYS', 'A', '2020-02-01 16:37:45.000000', 'SYS', 'Y', 'accountName', 'Account Name', 3, 'groupList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('67b13010-d929-4a2c-81a0-46c8c9562680', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'N', 'id', 'id', 1, 'userList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('93b1a76b-5d8b-4fcb-ad7b-84cec183b008', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'username', 'Username', 2, 'userList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('6233c2c2-625a-408a-9cda-f794e8524b72', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'status', 'Status', 8, 'userList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('e0c3cf7e-6f9d-4587-ab6a-1d0ef302cd52', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'groupName', 'Group Name', 3, 'userList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('60fe1423-dfe9-4534-8735-b6f48082a600', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'firstName', 'First Name', 4, 'userList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('432e23ea-043d-4517-9e09-3daae9b01a58', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'lastName', 'Last Name', 5, 'userList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('c34a6625-4c77-4e6d-8481-b75775b7381a', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'email', 'Email', 6, 'userList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('1268c6b4-d614-413a-a0f4-d2aba153312d', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'phone', 'Phone Number', 7, 'userList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('0f515ec5-8aa2-4065-ab58-dac25850abe4', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'N', 'id', 'id', 1, 'roleList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('9f77270a-d7ff-4e4f-83de-004336207771', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'name', 'Role name', 2, 'roleList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('641229db-c7dc-46ba-94d0-40b46d2372d3', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'status', 'Status', 3, 'roleList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('136baeb4-73b3-4db6-adc5-1700081df318', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'N', 'id', 'id', 1, 'uriList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('ad665ae6-7778-4dea-9d73-27a7f7f71e43', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'status', 'Status', 3, 'uriList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('a79110b0-7648-4fcd-b6eb-c69466657da0', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'N', 'id', 'id', 1, 'roleURIsList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('da7a5c3f-ac41-4938-8438-c8223a281bca', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'roleName', 'Role Name', 2, 'roleURIsList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('dd8b9ccd-2e34-402f-b363-217b92d1776e', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'uriName', 'URI Name', 3, 'roleURIsList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('d90dd485-efba-4094-be18-8d6ae7044d89', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'status', 'Status', 4, 'roleURIsList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('7977aae3-cf76-421f-a768-ba53eb38298b', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'N', 'id', 'id', 1, 'permissionList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('99a45532-3373-4dba-9129-fd0ad1cf31c6', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'userLoginId', 'Username', 2, 'permissionList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('9856a1e4-97ad-49cf-b152-8be9b7b6f19a', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'firstName', 'Firstname', 3, 'permissionList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('4bf9fdbd-0ee2-4467-bcd0-2345ed215a1e', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'lastName', 'Lastname', 4, 'permissionList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('44992052-a8ea-4199-a3b0-1d610cdf58ce', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'roleName', 'Role name', 5, 'permissionList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('20022625-e783-4438-851b-65f55512be39', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'status', 'Status', 6, 'permissionList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('917d802b-3f9f-4f53-9b35-72e5c0ff1eaa', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'status', 'Status', 3, 'apiBaseList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('292e339f-2bc9-4468-a980-60d7a2686d61', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'N', 'id', 'id', 1, 'apiBaseList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('8d254c76-aae3-437b-9bb6-bf8ec9c15ebc', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'N', 'id', 'id', 1, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('db186281-cd73-4d20-a4ec-36be98fad2cc', '2020-02-01 16:37:32.000000', 'SYS', 'A', '2020-02-01 16:37:45.000000', 'SYS', 'Y', 'viewName', 'Table List Name', 2, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('c07f5854-ad35-4012-9a05-908855421b3b', '2020-02-01 16:37:33.000000', 'SYS', 'A', '2020-02-01 16:37:46.000000', 'SYS', 'Y', 'labelColumnHead', 'Label Column Head', 3, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('0fa5563f-502f-42f7-8822-2f4fce083ee4', '2020-02-01 16:37:34.000000', 'SYS', 'A', '2020-02-01 16:37:47.000000', 'SYS', 'Y', 'allowFilter', 'Allow Filter', 5, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('94c6bc36-aebb-4044-b079-8eb14689a7f1', '2020-02-01 16:37:35.000000', 'SYS', 'A', '2020-02-01 16:37:48.000000', 'SYS', 'Y', 'columnKey', 'Attribute Name', 4, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('e159823e-ab69-4bc1-a9cc-26c01549aaac', '2020-02-01 16:37:36.000000', 'SYS', 'A', '2020-02-01 16:37:50.000000', 'SYS', 'N', 'sequence', 'Column Seq', 6, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('d1309152-5a50-4996-9aec-93cc6e069a47', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'status', 'Status', 7, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('c7f92b41-0f46-4932-af35-e6b11d950edb', '2020-07-12 11:16:57.922734', 'admin', 'A', '2020-07-12 11:16:57.922734', 'admin', 'Y', 'menuIcon', 'Menu Icon', 6, 'menuList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('3f4724e0-6375-42ee-b0c3-b3a0c523df27', '2020-07-11 14:57:39.037483', 'admin', 'A', '2020-07-12 11:17:08.540771', 'admin', 'Y', 'status', 'Status', 7, 'menuList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('6b9559c4-48fa-4b80-96e0-bf9aae9841a9', '2020-07-11 14:55:01.745884', 'admin', 'A', '2020-07-11 14:55:01.745884', 'admin', 'Y', 'menuLabel', 'Menu Label', 2, 'menuList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('fa508ada-1d65-434b-b3ee-69c116615d08', '2020-07-11 14:55:38.394496', 'admin', 'A', '2020-07-11 14:55:38.394496', 'admin', 'Y', 'uriName', 'URL', 3, 'menuList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('1291b29f-2dbd-47a3-ae7a-9557b326d4a3', '2020-07-11 14:56:14.698387', 'admin', 'A', '2020-07-11 14:56:14.698387', 'admin', 'Y', 'groupName', 'Group Name', 4, 'menuList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('366995b4-c652-4c0f-906d-392c27275076', '2020-07-11 14:54:03.874564', 'admin', 'A', '2020-07-11 14:54:19.489967', 'admin', 'N', 'id', 'id', 1, 'menuList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('3467bc3e-4948-46fa-86b6-860a92198da8', '2020-07-11 14:56:53.999434', 'admin', 'A', '2020-07-12 11:16:05.879731', 'admin', 'Y', 'index', 'Index', 5, 'menuList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('3fffefcb-218d-496b-b3d1-02c4b7f09e35', '2020-10-18 12:51:01.513000', 'admin', 'A', '2020-10-18 12:51:01.513000', 'admin', 'N', 'id', 'id', 1, 'whiteurlList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('2a5d9c0c-ae5a-4a3c-a808-b0a4b3cb57df', '2020-10-18 12:51:59.802000', 'admin', 'A', '2020-10-18 12:51:59.802000', 'admin', 'Y', 'uriName', 'URL', 3, 'whiteurlList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('7a0d67d4-ade0-4f9c-b5b7-d242891eea43', '2020-10-18 12:52:39.649000', 'admin', 'A', '2020-10-18 12:52:39.649000', 'admin', 'Y', 'status', 'Status', 5, 'whiteurlList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('e5fecb75-4d85-41e6-af62-eb6aa89c939e', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-12-07 17:16:14.678000', 'admin', 'Y', 'apiUrl', 'API Endpoint', 2, 'apiBaseList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('0d72aad0-d0ed-4951-a341-53e09b0d9ef4', '2020-10-18 12:51:33.916000', 'admin', 'I', '2020-10-18 12:51:33.916000', 'admin', 'N', 'uriId', 'URI ID', 2, 'whiteurlList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('ce0eb0f9-da6d-4b9f-a266-7f8d34c90b6e', '2020-10-18 12:52:20.420000', 'admin', 'I', '2020-10-18 12:52:20.420000', 'admin', 'Y', 'allowedMethod', 'Allow Methods', 4, 'whiteurlList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('b2f5030b-0a55-4feb-bb6c-4e5e629c4639', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-12-08 08:49:07.820000', 'admin', 'N', 'id', 'id', 1, 'accountList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('dab9b5d1-e76d-4dc7-b907-7932a24feede', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-12-08 08:50:54.139000', 'admin', 'Y', 'uriName', 'URL', 2, 'uriList');

-- Sys certificate
INSERT INTO tbl_core_system_certificate (id, created_date_time, created_user, status, updated_date_time, updated_user, private_key, public_key, code) VALUES ('5a2ec2e7-46a7-46fe-8111-391cc90dd5d0', '2020-12-07 18:26:10.015000', 'admin', 'A', '2020-12-07 18:26:10.015000', 'admin', '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', 'CERT_1');
INSERT INTO tbl_core_system_certificate (id, created_date_time, created_user, status, updated_date_time, updated_user, private_key, public_key, code) VALUES ('fcee598e-6828-4204-94a1-dfc9ef260de3', '2020-12-07 18:26:10.018000', 'admin', 'A', '2020-12-07 18:26:10.018000', 'admin', 'MIIJQwIBADANBgkqhkiG9w0BAQEFAASCCS0wggkpAgEAAoICAQCLKbX0O0xE4+pX9E5mzVLfxdX8pSYx/2mnl1+FcgIUFJ7Is/XOXhPa3GZlMrb4k/QOgw4H0v445jW8C2h/qk8xLRtJwDp2f99vl0tzsrykjNwmQPq3I+GtKWT7sYhw2FNlNVlMQzQ4Ym+RsCKY4atWcHagjam96Xu/yqdqD3foIuWN0LjMp+Lj1I/VoNt4cuOJoRAdwnojlhYiZ4n4nghcpk9KZwQDz1a2oNygw39vXptOUptNJtsQhv69jUQ8wja8Vg8lBEyEbxrxLWff102FtiPS0AUimUobWvq9IjFrD6F4WK0OGo4TlOAvrB22bvfu3FR7MIiN1KtINEWMVigePJq7pLWIZYjaOPbwRCkEvEO9Ne3MJ3Py4HA+H1Umi5NnVg0LHPsyzIa4psWxZ9f1124ClPZo1UL3C8VNMEn766ecoCOywDfoeut+VqMHUhS7iRv8NP+TJ69zSpdH628YoOmh4UQlGmJyWaBR7T/CDnbTQVO3FFox3jvnVF0fLMXYWZQKVYj0dyIn7s1D1RlDhk5OJHqK5Al/WCbP0AtXXTG2h9aXlVSNYc9o+pf5jNOkZGz/xp52Hav0nn8d5qKUwoTiSs7p0Vmq4w3rDhc7HVke7dhIWDHVVQpMD6bNRgANpL3NRE/T1BS6gWVouKtuulaepTcqaYa08w92C/MXbQIDAQABAoICAB/qiIsDgIn6NZhHdSW5qGye7GVdoQVtfRdNLcCmtZhSZFpGf7T9OZOD4c4K1BDtqdccMn8T4m89SQygFy7UagYuwKfo+N+Nkzp+GfVmhEI+9gyl8XpINe1nZXweKtsqmfHHyvulHBhkZw8kNMMYWpngACzxYIEoR9PkUAIQmyD3w+sC+Ccjx/MVoIlPs5YLz2iUsmQI4sc/mEL6uRd2cxeAowW+25peFpabDQ6n15W8vmS3qFuI8kv3kfv6JrDCoBgQqreEJWJoMucmUUDymZp1fADQ6aSWQHtGoeP3sBrMQm2d9M6bw4L1LXdxg30ANUeoa423Q1I3+LJ+HodBiUCTZee4O8UfaY5tw9waN6R9E0ZA4Urh41a9fLS54J+/+7hHAidKAJ3hCz0nlVXgjRO92qMI5HP4PIJQeh+mDjRd/ZAFktgMJb9UF68AucV5DWisFxFuI/oDm70XRXmBz6wjghVt0RgrGbkru1pZN0sYYtK6RusyWxzn+UuEbjb0Kj/9OzPfwgHDgq6TzQBjeDwIwbm21LFqcRGljTzENtc+KZzquZ8P4p16p3DpnoM3ZNjjKomrjHgQ7GAzng20MCWp474tRL85o0BF99IJyNtm9nH+LQIMX6aoZ1MnGVu5NXRwzG+qXdw7TfrepdYbZ/1id1lU9ZXIHvG/ptb4jKQHAoIBAQDDzb/6S/JL8rp0SFnuZ09zKDtS0EwXj1oaC+nUMZhBD/oinDjT9mZ2zuM9j9LHjPjsFf+s2u52WUysuqpwhEmcD6HahPNfPkSkKy7CrRz4d+7C2sCUB8PmXsg+LaaAMdlFhyBOQEBwgwKk2l2QkjcUqo/Qxj+c0QC107n1K6BGnskQJbXAP0j3xaPzDcI1gZ+nVHB8I0iJJlvMG53WFQzk8e58Gr73b06NnLQcSI9ghUp5NdkD7Vo0pNZ4JUceRqJPvH9qjFqicOLm/XqhzyGq+OFZbQQUGQ7kYlWFQRBIZN9zXUIl7TBlAGMw2Z5NeXxOcwyWopCwP3s4LoCJ+Oi7AoIBAQC18jAC4rHvii/11N523w0DjXrrHpgmnCPrz+zu5Vx2Rxg7lUyJ3H5vo4uLy00Fbg96FTSA4oij73j6hQAQiDqu3z/6s+Y4c2+9mD3KIFa+sxuoh6JuOy1bDgKIin02YsLq+DXVoreaHIrskd6Ea3QKAVmwt+KpcWksusqQrzpoIQt2pdPWs4ppmGptV4XFbUo26qGVqrQViqW85MHLtbKYUqspigf1C57z/4y237Im7EAdc+PzbTemFZn0rmFrV1wFU/Zuz5fawSHTKzAXWDvsNWw6ezjMBH1NtxYnQyQEK0KpO9CAhgD2Z2UTCAbLDLxLXL+WO8hnsOCgEZ4RMHH3AoIBAQCtp/E3/9to2GHdd72q3x1ruKbEj9CspIr4xtAfTc0HBzCFOaB8/O9rN5n6o3ABY1nxqKEVoSYU+KY0G2nGWM0gCA04TOm78vHV2CakqfwjsdqkHnbl0u0/h5h47OvajltweqOAQyPjpY3Wv4R0D/mh7pOWSf1pC0Rh/uHSO1ruxyzzkT5cvsPsW/FIQ0+XPNtoAE+zCWfvm4GWyHd3wzuoYZltoBr2QFWe6vgijYf7xruCPOu3u+5g+kDsVcN5apPRvBAVY12O7CeRTgZRM+9cP1c9hJ41ZPP8QeovQME8M5qqKm/cSzy8l1dhn9/yPmis+wSTJKLGbcbO0Q9XTta5AoIBAQCIPPYIYxkbpZyt9hwyZGiJPXpJ7hm953LaU1I1vBbVFf0mOHAcVrdx/sMyBSQCKdx67L/otjfckSdEZyyaNHfEk7SDlAuSP5XvIL07yaVKnDDcq9tLsRIQOVhaR2DkxWTwrrUY6AOU6UR5SeyVuSQljcdMFRUrDZnUfXXKivswF3ZO/HK4NK4yzYb7ouqu31wP7Pbba/v2mV/rJluans5BUr2JmCrHKVF3oOYL1Uq/7G1B4JJSp3hg65HhbmXSC5MDBlDjvpwVmEIsrg0PnekYwRPuYnAwDDiLZcm30oV4fbAnqntAkZbM6rT7YCo+BqZDxApNgyxXwFEiaq6yblUXAoIBAC+EWsYCZi1/Bf7k/wVJaeDm16y6HY9vnovb/kTbw5wdgN2hCafb8cpvzjk199j1wzqmyYz6/8uK99dxs9jDG/rgaqSinVoucYf9Z5VEc5dMG9CcQUwVvr17v3OBnge0Rmszs/rR1tejP8jT6w9+dKVTcjwKC7XUGddXPu2uSimmEg1D5meRIy+GE3kLR7a4gByjI76Gj5WNJiEjK5ImHr1DJDGQttdKWvaWw11wz5Ykqd80awj0xh0fa9Pdws6m9hLt5Fn38uVSb2MzGsiIrKDO3c2gQZloE19OP9QQTBzdZgKZEJarQLJHzfb7Vo1b5mbslc2EKsVrk3Axbnla3bM=', 'MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAiym19DtMROPqV/ROZs1S38XV/KUmMf9pp5dfhXICFBSeyLP1zl4T2txmZTK2+JP0DoMOB9L+OOY1vAtof6pPMS0bScA6dn/fb5dLc7K8pIzcJkD6tyPhrSlk+7GIcNhTZTVZTEM0OGJvkbAimOGrVnB2oI2pvel7v8qnag936CLljdC4zKfi49SP1aDbeHLjiaEQHcJ6I5YWImeJ+J4IXKZPSmcEA89WtqDcoMN/b16bTlKbTSbbEIb+vY1EPMI2vFYPJQRMhG8a8S1n39dNhbYj0tAFIplKG1r6vSIxaw+heFitDhqOE5TgL6wdtm737txUezCIjdSrSDRFjFYoHjyau6S1iGWI2jj28EQpBLxDvTXtzCdz8uBwPh9VJouTZ1YNCxz7MsyGuKbFsWfX9dduApT2aNVC9wvFTTBJ++unnKAjssA36HrrflajB1IUu4kb/DT/kyevc0qXR+tvGKDpoeFEJRpiclmgUe0/wg5200FTtxRaMd4751RdHyzF2FmUClWI9HciJ+7NQ9UZQ4ZOTiR6iuQJf1gmz9ALV10xtofWl5VUjWHPaPqX+YzTpGRs/8aedh2r9J5/HeailMKE4krO6dFZquMN6w4XOx1ZHu3YSFgx1VUKTA+mzUYADaS9zURP09QUuoFlaLirbrpWnqU3KmmGtPMPdgvzF20CAwEAAQ==', 'CERT_2');

-- Load Menu
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('93742b9e-6758-48bc-9736-ae39650f888f', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-07-04 16:11:22.000000', 'SYS', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-id-badge', 'Roles', 'dbf68434-77b9-46b2-9811-38c52f9d521d', 'b91aa537-64c2-4eba-b954-694a5c02f504', '/app/roles', 'M_SYS_ADMIN', 5);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('4e905158-86ee-483f-bb9d-1664105d1584', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-07-04 16:11:22.000000', 'SYS', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-user-o', 'Users', 'dbf68434-77b9-46b2-9811-38c52f9d521d', '59dd27f1-9e52-4a39-91b2-f0602e1f3bf7', '/app/users', 'M_SYS_ADMIN', 4);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('103725a3-1d6c-44b2-bd92-2f7104bb98c2', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-12-08 08:24:02.582000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-server', 'URLs', 'eeed2b3c-86d0-467a-84bd-136030e3d824', 'f232b85d-a675-4cac-914a-52e8c6c94c0a', '/app/uris', 'M_SYS_ADMIN', 6);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('efaf8c11-679f-4d5d-8392-1af5fb7415de', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-07-04 16:11:22.000000', 'SYS', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-recycle', 'Clear System Cache', '4eec4eb1-b952-4fd8-9f3a-ca9808355c94', 'f72d0592-b65d-4168-aeff-0b7d09f1a92c', '/app/clear-caches', 'M_SYS_ADMIN', 16);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('c7acaf3b-70da-455a-aa23-9526d4bfda66', '2020-07-11 14:31:12.000000', 'SYS', 'A', '2020-07-11 14:31:23.000000', 'SYS', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-th-list', 'Menu Configuration', '4eec4eb1-b952-4fd8-9f3a-ca9808355c94', 'b3c17b2e-fe79-43f6-b9e3-6dcb38236bdc', '/app/menu', 'M_SYS_ADMIN', 15);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('082666ab-9584-4d3a-9506-e941a6728e3e', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-12-07 16:18:37.275000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-unlock', 'Roles-URLs Mapping', 'dbf68434-77b9-46b2-9811-38c52f9d521d', '68af8c6a-a1d0-408d-a075-901cf462dbfc', '/app/roleURIs', 'M_SYS_ADMIN', 7);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('1cdb2253-b75c-4808-bbd5-098868b2a51d', '2020-10-17 13:15:03.487000', 'admin', 'A', '2020-12-08 09:46:21.600000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-link', 'Whitelist URLs', 'eeed2b3c-86d0-467a-84bd-136030e3d824', '062357e9-41e6-476d-85c6-6783a44fa003', '/app/whiteurls', 'M_SYS_ADMIN', 9);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('eeed2b3c-86d0-467a-84bd-136030e3d824', '2020-12-07 17:12:28.220000', 'admin', 'A', '2020-12-07 17:12:28.220000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-cog fa-lg', 'System Configurations', 'P', '', '', 'M_SYS_ADMIN', 12);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('4c0895dc-8621-4dc6-8e0e-9a17efdeee1e', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-07-04 16:11:22.000000', 'SYS', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-table fa-lg', 'Datable Configuration', '4eec4eb1-b952-4fd8-9f3a-ca9808355c94', '0405b76c-7b97-4e5d-8272-7675afb12b25', '/app/datatable', 'M_SYS_ADMIN', 14);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('d802cebc-88ba-4084-bbe3-c0864c20c49b', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-07-04 16:11:22.000000', 'SYS', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-user-secret', 'Clients', '251525ad-2c29-4c4f-8ab1-f594ad53a9ae', '68154b9a-b88b-4b79-8f4c-e48c39e64ee8', '/app/webservice-clients', 'M_SYS_ADMIN', 12);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('43824009-9d49-42ee-bcd9-f8b2d5edfc65', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-07-04 16:11:22.000000', 'SYS', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-lock', 'User Permissions', 'dbf68434-77b9-46b2-9811-38c52f9d521d', 'ebb7533c-1999-4b79-889d-55d805fe7a4c', '/app/permissions', 'M_SYS_ADMIN', 8);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('4eec4eb1-b952-4fd8-9f3a-ca9808355c94', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-07-04 16:11:22.000000', 'SYS', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-plus-square fa-lg', 'Addons', 'P', '', '', 'M_SYS_ADMIN', 13);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('cc4b9d8d-425b-4b94-9aa9-9a76ea1a3eb3', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-12-07 17:13:47.834000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-id-card', 'System Certificate', 'eeed2b3c-86d0-467a-84bd-136030e3d824', '9ad0ab0d-ee63-4b3b-8db0-a711682ec702', '/app/system-certificate', 'M_SYS_ADMIN', 10);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('14a182e2-eb26-4749-ae32-3ff88291dc31', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-12-07 17:15:18.568000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-paper-plane-o', 'API Endpoints', '251525ad-2c29-4c4f-8ab1-f594ad53a9ae', 'ea1b5b8d-4364-47eb-9e8c-b2306f580cf2', '/app/apibases', 'M_SYS_ADMIN', 11);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('88d5c594-03ae-4d59-8bcd-f5d4cf9e90d2', '2020-07-12 10:38:49.525968', 'admin', 'A', '2020-07-12 10:38:49.525968', 'admin', 'c096552f-0b4f-4209-b5e4-64d3ff5b1a9e', 'fa fa-user-o', 'Users', 'da4d98c9-f41e-4ba1-851a-c447bb44e410', '59dd27f1-9e52-4a39-91b2-f0602e1f3bf7', '/app/users', 'M_SYS_SUPPORT', 2);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('da4d98c9-f41e-4ba1-851a-c447bb44e410', '2020-07-12 10:43:38.103649', 'admin', 'A', '2020-07-12 11:43:01.921750', 'admin', 'c096552f-0b4f-4209-b5e4-64d3ff5b1a9e', 'fa fa-cog fa-lg', 'Support Config', 'P', '', '', 'M_SYS_SUPPORT', 1);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('b1918c27-38b7-4d3a-bb6e-0267a98ef723', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-12-02 20:07:23.178000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-group', 'Groups', 'dbf68434-77b9-46b2-9811-38c52f9d521d', 'ea7a70f1-bbf0-433c-a4fe-7c165896550b', '/app/groups', 'M_SYS_ADMIN', 3);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('19338af3-447c-4337-832a-445d4799102f', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-12-02 20:09:53.855000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-building-o', 'Organizations', 'dbf68434-77b9-46b2-9811-38c52f9d521d', '2404ee87-d57b-4eb2-ac16-003dd9b93c46', '/app/accounts', 'M_SYS_ADMIN', 2);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('251525ad-2c29-4c4f-8ab1-f594ad53a9ae', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-12-07 17:29:06.266000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-rss fa-lg', 'Outbound Configurations', 'P', '', '', 'M_SYS_ADMIN', 9);
INSERT INTO tbl_core_menu (menu_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, menu_icon, menu_label, parent_menu, uri_id, uri_name, group_name, index) VALUES ('dbf68434-77b9-46b2-9811-38c52f9d521d', '2020-07-04 16:11:34.000000', 'SYS', 'A', '2020-12-07 17:30:27.933000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', 'fa fa-home fa-lg', 'Inbound Configurations', 'P', '', '', 'M_SYS_ADMIN', 1);

-- White List URI
INSERT INTO tbl_core_white_uri (white_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_id, uri_name) VALUES ('cc01ffe0-7512-4815-871e-7db057df4ad9', '2020-10-18 14:06:31.490000', 'admin', 'A', '2020-10-18 14:06:31.509000', 'admin', '84aa1832-5c58-4568-ae54-738659e6aaa1', '/login/**');
INSERT INTO tbl_core_white_uri (white_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_id, uri_name) VALUES ('d8149c81-7e6b-413d-a8fd-743062063f76', '2020-10-18 15:03:14.225000', 'admin', 'A', '2020-10-18 15:03:14.231000', 'admin', 'c26ecceb-acdf-484c-99ae-1a2748b3a746', '/access-denied/**');
INSERT INTO tbl_core_white_uri (white_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_id, uri_name) VALUES ('c599d444-bb0f-4939-80ff-11e750d73da9', '2020-11-21 22:34:04.508000', 'admin', 'A', '2020-11-21 22:34:04.522000', 'admin', '65124f82-d905-424f-9dcd-762a0c9da32d', '/unauthorized-access/**');

-- Allow Methods
INSERT INTO tbl_core_allowed_method (white_uri_id, http_method) VALUES ('cc01ffe0-7512-4815-871e-7db057df4ad9', 'POST');
INSERT INTO tbl_core_allowed_method (white_uri_id, http_method) VALUES ('cc01ffe0-7512-4815-871e-7db057df4ad9', 'PUT');
INSERT INTO tbl_core_allowed_method (white_uri_id, http_method) VALUES ('d8149c81-7e6b-413d-a8fd-743062063f76', 'GET');
INSERT INTO tbl_core_allowed_method (white_uri_id, http_method) VALUES ('c599d444-bb0f-4939-80ff-11e750d73da9', 'GET');

