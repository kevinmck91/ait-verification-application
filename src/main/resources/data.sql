-- Player SQL - Team A
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,age_group,image,club_id,active_membership,trust_score)
values (100001,'Joe','Bloggs01',TO_DATE('13/01/2007', 'DD/MM/YYYY'), 2007, FILE_READ('classpath:TeamA/t_a_player_1.png'),10001,true,100);
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,age_group,image,club_id,active_membership,trust_score)
values (100002,'Joe','Bloggs02',TO_DATE('14/01/2007', 'DD/MM/YYYY'), 2007, FILE_READ('classpath:TeamA/t_a_player_2.png'),10001,true,100);
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,age_group,image,club_id,active_membership,trust_score)
values (100003,'Joe','Bloggs03',TO_DATE('15/01/2007', 'DD/MM/YYYY'), 2007, FILE_READ('classpath:TeamA/t_a_player_3.png'),10001,true,100);
insert into PLAYER (membership_id,firstname,lastname,date_of_birth,age_group,image,club_id,active_membership,trust_score)
values (100004,'Joe','Bloggs04',TO_DATE('16/01/2007', 'DD/MM/YYYY'), 2007, FILE_READ('classpath:TeamA/t_a_player_4.png'),10001,true,100);


-- Team A
insert into Team (name,location,club_id) values ('Team A','Dublin',10001);

