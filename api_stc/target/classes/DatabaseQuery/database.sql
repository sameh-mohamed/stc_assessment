
create database stc;
use stc;
create table document_type
(
 document_type_id int GENERATED ALWAYS AS IDENTITY,
 document_type_name varchar(20),
 primary key(document_type_id)
);

create table item(
item_id int GENERATED ALWAYS AS IDENTITY ,
document_type_id int,
item_name text,
parent_id int,
primary key(item_id),
CONSTRAINT `FK_item_item_type_id` FOREIGN KEY (`parent_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `FK_item_document_type_id` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`document_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

create table item_group(
item_id int,
group_id int,
primary key(item_id,group_id)
);

create table files(
id int GENERATED ALWAYS AS IDENTITY  ,
files_binary blob,
item_id int ,
primary key(id),
CONSTRAINT `FK_files_item_id` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

create table `group`(
group_id int  GENERATED ALWAYS AS IDENTITY  ,
group_name varchar(100),
group_description text,
primary key(group_id),
key  `IDX_group_group_name`(group_name)
);

create table permission(
permission_id  int  GENERATED ALWAYS AS IDENTITY primary key,
permission_name varchar(200)
);

create table user(
user_id  int  GENERATED ALWAYS AS IDENTITY,
first_name varchar(100),
last_name varchar(100),
user_email varchar(150),
`password` varchar(100),
primary key(user_id),
unique key `IDX_user_user_email` (user_email)
);

create table user_group
(
user_id int,
group_id int,
primary key (user_id,group_id)
);

create table group_permission(
permission_id int,
group_id int,
primary key(permission_id,group_id),
CONSTRAINT `FK_user_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT `FK_user_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
unique key `IDX_user_group_id_permission_id` (user_email,permission_id,group_id)
);



