USE songs;
-- Initial data for Producer table
INSERT INTO producer(FIRST_NAME, LAST_NAME, CITY, ADDRESS, STATE) VALUES ('Nile', 'Rogers', 'New-york', 'Azza', 'Israel');

-- Initial data for Singer table
INSERT INTO singer(FIRST_NAME, LAST_NAME, CITY, ADDRESS, STATE) VALUES ('Bob', 'Dylan', 'Migdal', 'Azza', 'Israel');
INSERT INTO singer(FIRST_NAME, LAST_NAME) VALUES ('Bob', 'Marley');
INSERT INTO singer(FIRST_NAME, LAST_NAME) VALUES ('Josh', 'Dylan');

-- Initial data for Song table
INSERT INTO song(NAME, LENGTH, PRODUCER_ID) VALUES ('the dylan song', 2.3, 1);
INSERT INTO song(NAME, LENGTH) VALUES ('Big brown eyes', 2.3);

-- Initial data for Singer_Song table
INSERT INTO singer_song(SINGER_ID, SONG_ID) VALUES (1, 1);
INSERT INTO singer_song(SINGER_ID, SONG_ID) VALUES (3, 1);
INSERT INTO singer_song(SINGER_ID, SONG_ID) VALUES (2, 2);