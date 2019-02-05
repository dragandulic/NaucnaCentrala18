INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('1','Bojana','Bojanic','boki@gmail.com','Novi Sad','Srbija','111',1,0);
INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('2','Nikola','Nikolic','nina@gmail.com','Nis','Srbija','222',0,1);
INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('3','Aleksandra','Grujic','saska@gmail.com','Novi Sad','Srbija','333',1,0);


INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('1','Elektrotehnika','ELE');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('2','Ekonomija','EKON');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('3','Saobracaj','SAOB');


INSERT INTO `naucnacentrala`.`magazine` (`id`, `name`,`issnnumber`,`methodpayment`,`maineditor_id`,`merchant_id`,`merchant_password`,`amountmag`) VALUES ('1','Tehnika','0040-2176',1,'3','977dk4mdjw3bz82hd71abgm39dm48f','1','2');
INSERT INTO `naucnacentrala`.`magazine` (`id`, `name`,`issnnumber`,`methodpayment`,`maineditor_id`,`merchant_id`,`merchant_password`,`amountmag`) VALUES ('2','Bankarstvo','1451-4354',1,'1','411sldmcv81yt6j5nf9q2nv0m4u7v9','2','3');


INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`) VALUES ('1','abstract', 'finalversion', 'kyeterms', 'pdf', 'Analiza magnetopobudnih sila u multifaznim mašinama u sklopu integrisanih punjača za električna vozila', '1','1','1');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`) VALUES ('2','abstract', 'finalversion', 'kyeterms', 'pdf', 'Međulaboratorijska ispitivanja kod širokopojasnih merenja elektromagnetskih polja', '1','1','2');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`) VALUES ('3','abstract', 'finalversion', 'kyeterms', 'pdf', 'Sistemi emitovanja i standardi satelitske televizijske transmisije u Evropi ', '1','1','2');

INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`) VALUES ('4','abstract', 'finalversion', 'kyeterms', 'pdf', 'Uticaj niskotarifnog prevozioca na cene karata tradicionalnog prevozioca na interkontinentalnim letovima', '3','1','2');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`) VALUES ('5','abstract', 'finalversion', 'kyeterms', 'pdf', 'Izmena putnika na međumesnim linijama ', '3','1','2');

INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`) VALUES ('6','abstract', 'finalversion', 'kyeterms', 'pdf', 'Savremena uloga i značaj profesionalnih kvalifikacija u sektoru bankarstva', '2','2','1');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `pdf`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`) VALUES ('7','abstract', 'finalversion', 'kyeterms', 'pdf', 'Tradicionalni i novi poslovni modeli u bankarskoj industriji', '2','2','2');










INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('1','1');


INSERT INTO `naucnacentrala`.`magazine_reviewers` (`magazine_id`, `reviewers_id`) VALUES ('1','2');


INSERT INTO `naucnacentrala`.`magazine_othereditors` (`magazine_id`, `othereditors_id`) VALUES ('1','3');

INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('1','Korisnik sistem', 'USER');
INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('2','Objavljuje radove u magazin', 'AUTHOR');



INSERT INTO `naucnacentrala`.`user` (`id`, `city`, `confirmpassword`, `country`, `email`, `name`, `password`, `surname`, `membershipfee_id`) VALUES ('1','Berlin', '555', 'Germany', 'maki@gmail.com', 'Maja', '$2a$10$ekSZsklk65QTyPbeM44fK.E9M1E1KbjCp7R/sPRN/iBeeg1av1liW', 'Majic', '1');

INSERT INTO `naucnacentrala`.`user_roles` (`user_id`, `roles_id`) VALUES ('1','2');


INSERT INTO `naucnacentrala`.`membership_fee` (`id`, `startdate`,`enddate`,`amount`) VALUES ('1','2018-12-15 00:00:00','2019-01-01 00:00:00','1');




