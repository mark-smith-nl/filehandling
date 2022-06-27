create table file
(
    file_id           bigserial primary key,
    original_filename varchar(128) not null,
    filename          varchar(128) not null unique,
    content           bytea
)
