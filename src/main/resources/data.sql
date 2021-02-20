-- Player SQL
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,image,qr_code,club_id,active_membership,trust_score)
values (123,'Joe','Bloggs',TO_DATE('17/12/2013', 'DD/MM/YYYY'),'myimage.jpeg','testqrcode',555,true,100);
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,image,qr_code,club_id,active_membership,trust_score)
values (124,'Jane','Bloggs',TO_DATE('16/10/2013', 'DD/MM/YYYY'),'myimage2.jpeg','testqrcode2',234,true,100);
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,image,qr_code,club_id,active_membership,trust_score)
values (125,'Jim','Bloggs',TO_DATE('11/04/2013', 'DD/MM/YYYY'),'myimage3.jpeg','testqrcode3',123,true,100);

-- Team
insert into Team (name,location,club_id) values ('Team A','Blackrock',123);
insert into Team (name,location,club_id) values ('Team B','Na Fianna',234);
insert into Team (name,location,club_id) values ('Team C','Cuala',555);
