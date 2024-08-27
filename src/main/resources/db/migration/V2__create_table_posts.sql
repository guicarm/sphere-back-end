CREATE TABLE IF NOT EXISTS posts (
    id bigint auto_increment,
    text varchar(255),
    created_at datetime
    primary key (id)
);

INSERT INTO posts (text, created_at) VALUES ('First post on the blog!', '2024-08-01 10:00:00');
INSERT INTO posts (text, created_at) VALUES ('Just attended a great conference!', '2024-08-10 14:30:00');
INSERT INTO posts (text, created_at) VALUES ('Launched a new product today!', '2024-08-15 09:45:00');
INSERT INTO posts (text, created_at) VALUES ('Exploring new technology trends.', '2024-08-20 16:20:00');
INSERT INTO posts (text, created_at) VALUES ('Celebrating 1000 followers on social media!', '2024-08-25 18:00:00');
