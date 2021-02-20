-- Player SQL
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,image,qr_code,club_id,active_membership,trust_score)
values (123,'Joe','Bloggs',TO_DATE('17/12/2013', 'DD/MM/YYYY'),'myimage.jpeg','testqrcode',555,true,100);

-- Team
insert into Team (name,location,club_id) values ('Team A','Blackrock',123);
