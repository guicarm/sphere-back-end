ALTER TABLE posts
ADD COLUMN user_id BIGINT;

ALTER TABLE posts
ADD CONSTRAINT fk_user
FOREIGN KEY (user_id) REFERENCES users(id);

INSERT INTO users (name, bio, email, password, created_at, updated_at) VALUES
('Alice', 'Software Developer', 'alice@example.com', '$2a$12$4TgHfKR9lQibgu65IkAxDO1FN7ubGk42sk8iPvpZ.Dcf4Qnyzmtei', NOW(), NOW()),
('Bob', 'Data Scientist', 'bob@example.com', '$2a$12$4TgHfKR9lQibgu65IkAxDO1FN7ubGk42sk8iPvpZ.Dcf4Qnyzmtei', NOW(), NOW());

UPDATE posts SET user_id = (SELECT id FROM users WHERE email = 'alice@example.com') WHERE id IN (1, 2);
UPDATE posts SET user_id = (SELECT id FROM users WHERE email = 'bob@example.com') WHERE id IN (3, 4, 5);