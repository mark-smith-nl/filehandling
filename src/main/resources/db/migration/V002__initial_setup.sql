drop table if exists file;

create table file
(
    file_id           bigserial primary key,
    original_filename varchar(128) not null,
    filename          varchar(128) not null unique,
    filetype          varchar(16),
    description       varchar(256),
    encoding          varchar(16),
    content           bytea
)