ALTER TABLE users
ADD COLUMN avatar VARCHAR(255);

UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=Alice+Smith' WHERE email = 'alice@example.com';
UPDATE users SET avatar = 'https://avatar.iran.liara.run/username?username=Bob+Johnson' WHERE email = 'bob@example.com';