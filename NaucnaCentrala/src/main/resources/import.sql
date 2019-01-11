INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('1','Bojana','Bojanic','boki@gmail.com','Novi Sad','Srbija','111',1,0);
INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('2','Nikola','Nikolic','nina@gmail.com','Nis','Srbija','222',0,1);
INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('3','Aleksandra','Grujic','saska@gmail.com','Novi Sad','Srbija','333',1,0);


INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('1','Biotehnicke nauke','med1');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('2','Drustvene nauke','dru2');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('3','Prirodne nauke','pri3');


INSERT INTO `naucnacentrala`.`magazine` (`id`, `name`,`issnnumber`,`methodpayment`,`maineditor_id`) VALUES ('1','Matematicki vesnik','0332-4907',1,'3');
INSERT INTO `naucnacentrala`.`magazine` (`id`, `name`,`issnnumber`,`methodpayment`,`maineditor_id`) VALUES ('2','Saskin magazin','5561-3971',1,'1');


INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('1','1');


INSERT INTO `naucnacentrala`.`magazine_reviewers` (`magazine_id`, `reviewers_id`) VALUES ('1','2');


INSERT INTO `naucnacentrala`.`magazine_othereditors` (`magazine_id`, `othereditors_id`) VALUES ('1','3');

INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('1','Korisnik sistem', 'USER');
INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('2','Objavljuje radove u magazin', 'AUTHOR');

INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`) VALUES ('1','Osnove ratarstva i povrtarstva', 'finalversion', 'ratarstvo', 'pdf', 'Zbirka radova instituta za ratarstvo i povrtarstvo', '1');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`) VALUES ('2','Matematicka analiza', 'finalversion', 'analiza', 'pdf', 'Matematicki vesnik', '3');


INSERT INTO `naucnacentrala`.`user` (`id`, `city`, `confirmpassword`, `country`, `email`, `name`, `password`, `surname`, `membershipfee_id`) VALUES ('1','Berlin', '555', 'Germany', 'maki@gmail.com', 'Maja', '$2a$10$ekSZsklk65QTyPbeM44fK.E9M1E1KbjCp7R/sPRN/iBeeg1av1liW', 'Majic', '1');

INSERT INTO `naucnacentrala`.`user_roles` (`user_id`, `roles_id`) VALUES ('1','2');


INSERT INTO `naucnacentrala`.`membership_fee` (`id`, `startdate`,`enddate`,`amount`) VALUES ('1','2018-12-15 00:00:00','2019-01-01 00:00:00','1');




