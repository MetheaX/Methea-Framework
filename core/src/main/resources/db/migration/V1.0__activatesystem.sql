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
	client_secret varchar(1024) not null,
	verify_code text not null
);

create table if not exists tbl_core_client_certificate
(
	id varchar(255) not null
		constraint tbl_core_client_certificate_pkey
			primary key,
	created_date_time timestamp not null,
	created_user varchar(255) not null,
	status varchar(255) not null,
	updated_date_time timestamp not null,
	updated_user varchar(255) not null,
	client_id varchar(255) not null,
	verify_key text not null
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

-- Create account
INSERT INTO tbl_core_account (account_id, created_date_time, created_user, status, updated_date_time, updated_user, account_address, account_email, account_name) VALUES ('b5911381-e595-4c67-ad6b-080abe000ed6', '2020-04-14 20:55:44.797000', 'admin', 'A', '2020-04-16 11:15:23.155000', 'admin', 'Phnom Penh, Cambodia', 'methea@methea-mail.io', 'Methea LLC.');
INSERT INTO tbl_core_account (account_id, created_date_time, created_user, status, updated_date_time, updated_user, account_address, account_email, account_name) VALUES ('85ec05cc-f6fa-40d7-a05d-c9ca760faee3', '2020-04-21 21:38:55.864000', 'admin', 'A', '2020-05-06 10:18:42.804374', 'admin', 'Phnom Penh, Cambodia', 'actiniumllc@gmail.com', 'Actinium LLC.');

-- Create group
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('7cfb627c-064e-4885-92cf-9659af7ee072', '2020-02-01 16:44:59.000000', 'SYS', 'A', '2020-04-18 12:33:11.192000', 'admin', 'b5911381-e595-4c67-ad6b-080abe000ed6', 'M_SYS_ADMIN', 'Methea system admin');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('c096552f-0b4f-4209-b5e4-64d3ff5b1a9e', '2020-04-26 18:56:01.252000', 'admin', 'A', '2020-04-26 18:56:01.254000', 'admin', '85ec05cc-f6fa-40d7-a05d-c9ca760faee3', 'M_SYS_SUPPORT', 'system support group');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('8a9e41e3-6bae-4ab5-bdd3-54dc6530b2f4', '2020-04-26 19:45:43.781000', 'admin', 'A', '2020-04-27 21:02:17.691000', 'admin', '85ec05cc-f6fa-40d7-a05d-c9ca760faee3', 'M_DEV_OPS', ' ');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('1e62737d-fb59-4f50-802c-48d7e03c6428', '2020-04-26 20:02:21.407000', 'admin', 'A', '2020-04-27 21:02:52.021000', 'admin', '85ec05cc-f6fa-40d7-a05d-c9ca760faee3', 'M_SOF_ENGINERE', ' ');
INSERT INTO tbl_core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, account_id, group_name, remarks) VALUES ('9ad7537a-dbce-4e9b-b3a4-3da224432628', '2020-04-26 19:37:44.879000', 'admin', 'A', '2020-04-27 21:37:02.274000', 'admin', '85ec05cc-f6fa-40d7-a05d-c9ca760faee3', 'M_ACCOUNTING', ' ');

-- Create role
INSERT INTO tbl_core_role (role_id, created_date_time, created_user, status, updated_date_time, updated_user, name) VALUES ('2d601157-6bb9-4117-8178-603d6551993c', '2020-02-01 16:43:21.000000', 'SYS', 'A', '2020-02-01 16:43:27.000000', 'SYS', 'ROLE_ADMIN');
INSERT INTO tbl_core_role (role_id, created_date_time, created_user, status, updated_date_time, updated_user, name) VALUES ('4dd72ac4-eae9-40dc-9048-01e392103649', '2020-05-02 11:54:51.267000', 'admin', 'A', '2020-05-02 13:50:05.269000', 'admin', 'ROLE_SYS_STARTER');

-- Create user
INSERT INTO tbl_core_user (user_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, password, username, email, first_name, last_name, phone, frc_usr_rst_pwd) VALUES ('a212eb79-592f-4af9-ab82-94d8b260edce', '2020-04-25 22:05:44.841000', 'admin', 'A', '2020-04-29 21:46:18.508000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', '$2a$10$8n9kU6aiagp016XeXUKNUOZPiz6x6Lv3z8YXhUvrv.hK.0Rc1lYMS', 'system_support', 'emily@gmail.coom', 'Emily', 'Victor', '+8559243445', 'N');
INSERT INTO tbl_core_user (user_id, created_date_time, created_user, status, updated_date_time, updated_user, group_id, password, username, email, first_name, last_name, phone, frc_usr_rst_pwd) VALUES ('afd72031-f13c-4384-90c7-930bca202974', '2020-02-01 16:35:01.000000', 'SYS', 'A', '2020-05-01 12:32:36.523000', 'admin', '7cfb627c-064e-4885-92cf-9659af7ee072', '$2a$10$17.6ysiFAWXV6UILkqVLCu2KEWLGxeHsA/Ffu7qxIbi8HEoX973ji', 'admin', 'methea.info@mail.io', 'Admin', 'Admin', '+855 92386749', 'N');

-- Create URI
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('84aa1832-5c58-4568-ae54-738659e6aaa1', '2020-02-01 16:46:09.000000', 'SYS', 'A', '2020-02-01 16:46:21.000000', 'SYS', '/login/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('fd7978dc-2c7c-4321-8b77-635501d6f4b5', '2020-02-01 16:46:05.000000', 'SYS', 'A', '2020-02-01 16:46:18.000000', 'SYS', '/resources/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('c26ecceb-acdf-484c-99ae-1a2748b3a746', '2020-02-01 16:46:07.000000', 'SYS', 'A', '2020-02-01 16:46:20.000000', 'SYS', '/access-denied/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('66ab5499-bdba-4569-a850-b713bd1afbe9', '2020-02-01 16:46:08.000000', 'SYS', 'A', '2020-02-01 16:46:20.000000', 'SYS', '/logout/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('57201e94-41f5-450a-b751-d9ad28c005c5', '2020-02-01 16:46:06.000000', 'SYS', 'A', '2020-02-01 16:46:19.000000', 'SYS', '/**');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('63644b6a-b12c-4bc8-8932-cfd5586e1547', '2020-05-16 11:46:31.000000', 'SYS', 'A', '2020-05-16 11:46:43.000000', 'SYS', '/');
INSERT INTO tbl_core_uri (uri_id, created_date_time, created_user, status, updated_date_time, updated_user, uri_name) VALUES ('57fa54d8-27d3-4246-bcdc-f87a6a4a5584', '2020-04-30 21:49:29.000000', 'SYS', 'A', '2020-05-16 16:04:09.116507', 'admin', '/profile/change-password/**');

-- Map role with URI
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('664d6f54-7b0b-465b-9396-f268929f23de', '2020-02-01 16:49:19.000000', 'SYS', 'A', '2020-02-01 16:49:07.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', 'fd7978dc-2c7c-4321-8b77-635501d6f4b5', '/resources/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('910b886f-cf7d-4bcc-8c3a-f42a88db07c6', '2020-02-01 16:49:18.000000', 'SYS', 'A', '2020-02-01 16:49:06.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', '66ab5499-bdba-4569-a850-b713bd1afbe9', '/logout/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('fa384b19-1479-4222-bee3-3ac52c7e6f03', '2020-02-01 16:49:16.000000', 'SYS', 'A', '2020-02-01 16:49:04.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', 'c26ecceb-acdf-484c-99ae-1a2748b3a746', '/access-denied/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('4f1954c4-dfda-4769-98c5-3c20fd872c90', '2020-02-01 16:49:20.000000', 'SYS', 'A', '2020-02-01 16:49:07.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', '84aa1832-5c58-4568-ae54-738659e6aaa1', '/login/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('03a042b3-32fe-4d93-a524-e1f6aad09f3e', '2020-02-01 16:49:20.000000', 'SYS', 'A', '2020-02-01 16:49:08.000000', 'SYS', '2d601157-6bb9-4117-8178-603d6551993c', '57201e94-41f5-450a-b751-d9ad28c005c5', '/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('38aae2d5-1474-4258-9700-9b2dc1aed253', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-04-30 21:51:32.000000', 'SYS', '4dd72ac4-eae9-40dc-9048-01e392103649', '63644b6a-b12c-4bc8-8932-cfd5586e1547', '/');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('4be64049-52fe-4638-b2b0-1054367228c7', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-04-30 21:51:32.000000', 'SYS', '4dd72ac4-eae9-40dc-9048-01e392103649', '84aa1832-5c58-4568-ae54-738659e6aaa1', '/login/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('3c02217f-f545-41f4-a002-a365f5733997', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-04-30 21:51:32.000000', 'SYS', '4dd72ac4-eae9-40dc-9048-01e392103649', 'fd7978dc-2c7c-4321-8b77-635501d6f4b5', '/resources/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('801f935e-0c05-4f26-b218-637c18de9f83', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-04-30 21:51:32.000000', 'SYS', '4dd72ac4-eae9-40dc-9048-01e392103649', '66ab5499-bdba-4569-a850-b713bd1afbe9', '/logout/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('0c33623d-510a-46d1-886c-7b0336393e3a', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-04-30 21:51:32.000000', 'SYS', '4dd72ac4-eae9-40dc-9048-01e392103649', 'c26ecceb-acdf-484c-99ae-1a2748b3a746', '/access-denied/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('f99862a1-3dda-43a3-b796-0cb617bb8911', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-05-16 16:06:04.025298', 'admin', '4dd72ac4-eae9-40dc-9048-01e392103649', '57fa54d8-27d3-4246-bcdc-f87a6a4a5584', '/profile/change-password/**');
INSERT INTO tbl_core_role_uri (role_uri_id, created_date_time, created_user, status, updated_date_time, updated_user, role_id, uri_id, uri_name) VALUES ('8b5c278e-ce13-403a-9654-d5f70f84e822', '2020-04-30 21:51:24.000000', 'SYS', 'A', '2020-05-16 16:06:11.474881', 'admin', '2d601157-6bb9-4117-8178-603d6551993c', '57fa54d8-27d3-4246-bcdc-f87a6a4a5584', '/profile/change-password/**');

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
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('b2f5030b-0a55-4feb-bb6c-4e5e629c4639', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'N', 'id', 'id', 1, 'accountList');
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
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('dab9b5d1-e76d-4dc7-b907-7932a24feede', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'uriName', 'URI name', 2, 'uriList');
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
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('e5fecb75-4d85-41e6-af62-eb6aa89c939e', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'apiUrl', 'API Base URL', 2, 'apiBaseList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('917d802b-3f9f-4f53-9b35-72e5c0ff1eaa', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'Y', 'status', 'Status', 3, 'apiBaseList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('292e339f-2bc9-4468-a980-60d7a2686d61', '2020-05-16 11:33:01.000000', 'SYS', 'A', '2020-05-16 11:33:11.000000', 'SYS', 'N', 'id', 'id', 1, 'apiBaseList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('8d254c76-aae3-437b-9bb6-bf8ec9c15ebc', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'N', 'id', 'id', 1, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('db186281-cd73-4d20-a4ec-36be98fad2cc', '2020-02-01 16:37:32.000000', 'SYS', 'A', '2020-02-01 16:37:45.000000', 'SYS', 'Y', 'viewName', 'Table List Name', 2, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('c07f5854-ad35-4012-9a05-908855421b3b', '2020-02-01 16:37:33.000000', 'SYS', 'A', '2020-02-01 16:37:46.000000', 'SYS', 'Y', 'labelColumnHead', 'Label Column Head', 3, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('0fa5563f-502f-42f7-8822-2f4fce083ee4', '2020-02-01 16:37:34.000000', 'SYS', 'A', '2020-02-01 16:37:47.000000', 'SYS', 'Y', 'allowFilter', 'Allow Filter', 5, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('94c6bc36-aebb-4044-b079-8eb14689a7f1', '2020-02-01 16:37:35.000000', 'SYS', 'A', '2020-02-01 16:37:48.000000', 'SYS', 'Y', 'columnKey', 'Attribute Name', 4, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('e159823e-ab69-4bc1-a9cc-26c01549aaac', '2020-02-01 16:37:36.000000', 'SYS', 'A', '2020-02-01 16:37:50.000000', 'SYS', 'N', 'sequence', 'Column Seq', 6, 'dataTableList');
INSERT INTO tbl_core_datatable_view (id, created_date_time, created_user, status, updated_date_time, updated_user, allow_filter, column_key, label_column_head, sequence, view_name) VALUES ('d1309152-5a50-4996-9aec-93cc6e069a47', '2020-02-01 16:37:38.000000', 'SYS', 'A', '2020-02-01 16:37:52.000000', 'SYS', 'Y', 'status', 'Status', 7, 'dataTableList');

-- Sys certificate
INSERT INTO tbl_core_system_certificate (id, created_date_time, created_user, status, updated_date_time, updated_user, private_key, public_key, code) VALUES ('b3e1967e-bd40-4ab3-a0bf-582f928ce069', '2020-05-06 10:17:46.113191', 'SYS', 'A', '2020-05-06 10:17:46.113191', 'SYS', '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', 'CERT_1');

-- Webservice client
INSERT INTO tbl_core_client (id, created_date_time, created_user, status, updated_date_time, updated_user, client_id, client_secret, verify_code) VALUES ('5921fcf8-8f5a-4ad9-9063-88e4c1c06cea', '2020-05-06 10:30:03.945141', 'SYS', 'A', '2020-05-06 10:30:03.945141', 'SYS', 'methea-client', '$2a$10$46OA8zAOLOZRlOBcGLV5qe3aa2WrjRhey2oftCMdt3JEnXqyqCP3y', 's067b3aYiiGgtF1kemHrDJQ943R38nrVljX/acSHGJxWTr3jXazHQ7Jrtvh7C8kc3pm4EaOVeyO3LanTcwERqqxzCjxTD34PxOQFMZ7tV06876ZE5JwYY7uC5RqATA3bC3SsbCU5747aXckPgYJcuDwn138Ea1yzDtpLy8JGsWF2XKPoBCp/XoqgHcEzxuo3VRHvfftIVsIfnCkwY6yV9keb6825gJDPCWYD9BwoyoUEu5qF9uUeoEq+hSI9JffcTyPtXDNFrYY1qT3vdX1fdrBmexXzXG24vOGEuO4wjX7/qmru7q4/c03zBe4A3P+Z670y0M5I6cKRkqjGKEPim3ro5U8a6ewU6Pi2WOjY1c5dt1xtW5RKc9tCEM+A4XvE6SJvDcX0sUJL1UjGZDkZ/mkKM+eqaJ0kVxwuuV+FEEJLzdPA2gE7Y6jbdIvbZ7AwCkR6Wn/Gcee0U6IgMrK/IhUFXgYzy2gUPWMRQBXAT0sdxGh+GxhBfE8YvXzN3MX6');

-- Client certificate
INSERT INTO tbl_core_client_certificate (id, created_date_time, created_user, status, updated_date_time, updated_user, client_id, verify_key) VALUES ('320b4922-b748-42d4-add5-2c0bd736698f', '2020-05-06 10:30:03.967882', 'SYS', 'A', '2020-05-06 10:30:03.967882', 'SYS', 'methea-client', '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');

-- API Base URLs
INSERT INTO tbl_api_base (api_id, created_date_time, created_user, status, updated_date_time, updated_user, api_url_name) VALUES ('04dfabc0-f665-4373-afa1-f6e738e4a671', '2020-06-06 17:21:43.295544', 'admin', 'A', '2020-06-06 17:23:46.561184', 'admin', '/api/v1/**');




