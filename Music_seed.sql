-- Seed data for `users` table
INSERT INTO users (username, password, status)
VALUES ('wendy', 'password123', 'user'),
    ('admin_user', 'adminpass', 'admin');
-- Seed data for `trackers` table
INSERT INTO trackers (user_id)
VALUES (1),
    (2);
-- Seed data for `albums` table
INSERT INTO albums (album_name, artist, genre, track_count)
VALUES ('Night at the Opera', 'Queen', 'Rock', 10),
    ('CHASER', 'Femtanyl', 'Hardcore', 6),
    ('Album Three', 'Artist C', 'Jazz', 8),
    (
        'Legend',
        'Bob Marley & The Wailers',
        'Reggae',
        14
    ),
    ('Rumours', 'Fleetwood Mac', 'Rock', 11),
    (
        'Purple Rain',
        'Prince and the Revolution',
        'Pop/Rock',
        9
    ),
    ('Abbey Road', 'The Beatles', 'Rock', 17),
    ('Thriller', 'Michael Jackson', 'Pop', 9),
    ('Back in Black', 'AC/DC', 'Hard Rock', 10),
    ('Nevermind', 'Nirvana', 'Grunge', 12);
-- Seed data for `albums_trackers` table
INSERT INTO albums_trackers (album_id, tracker_id, completed_tracks)
VALUES (1, 1, 5),
    -- 5 tracks completed out of 10
    (2, 1, 5),
    -- 7 tracks completed out of 12
    (3, 2, 3);
-- 3 tracks completed out of 8
