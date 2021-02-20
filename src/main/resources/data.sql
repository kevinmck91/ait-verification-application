-- Player SQL
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,image,qr_code,club_id,active_membership,trust_score) values (123,'Joe','Bloggs','01-Jan-1970','myimage.jpeg','testqrcode',555,true,100);

-- Team
insert into Team (name,location,club_id) values ('Team A','Blackrock',123);
