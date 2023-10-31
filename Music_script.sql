drop database if exists musictracker;

create database musictracker;
use musictracker;

##################
#   USERS  #
##################

create table users (
	user_id int NOT NULL auto_increment,
    username varchar(30) unique NOT NULL,
    password varchar(15) NOT NULL,
    primary key (user_id),
    status enum('user', 'admin') NOT NULL
);

##################
#   TRACKERS  #
##################

CREATE TABLE trackers (
    tracker_id INT NOT NULL auto_increment,
    user_id INT,
    CONSTRAINT fk_trackers
        FOREIGN KEY(user_id)
        REFERENCES users(user_id)
        ON DELETE CASCADE,
    CONSTRAINT pk_trackers
        PRIMARY KEY(tracker_id)
);

##################
#   ALBUMS  #
##################

CREATE TABLE albums (
	album_id INT NOT NULL auto_increment,
    album_name VARCHAR(30) NOT NULL,
    artist VARCHAR(30) NOT NULL,
    genre VARCHAR(30) NOT NULL,
    track_count INT NOT NULL,
    CONSTRAINT pk_albums
		PRIMARY KEY(album_id)
);

##################
#   ALBUMS_TRACKERS  #
##################

CREATE TABLE albums_trackers (
	album_id INT NOT NULL,
    tracker_id INT NOT NULL,
    completed_tracks INT NOT NULL,
    CONSTRAINT pk_albums_trackers
		PRIMARY KEY(album_id, tracker_id),
	CONSTRAINT fk_albums_trackers
		FOREIGN KEY(album_id)
        REFERENCES albums(album_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_trackers_albums
		FOREIGN KEY(tracker_id)
        REFERENCES trackers(tracker_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);


