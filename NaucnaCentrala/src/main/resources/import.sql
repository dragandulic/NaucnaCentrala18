INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('1','Isidor','Isic','isaisic1@gmail.com','Novi Sad','Srbija','$2a$10$1D1cEr/mGPeswS9U8lyLa.giJVScSmej7vAVQwMb3SISVRsVGrFh.',1,0);
INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('2','Dragan','Dulic','dragan.dulic1@gmail.com','Novi Sad','Srbija','$2a$10$U3W0.EF41Fh/fEC0ixJeR.TqaC0ywvawXB0R1KKufJsBwFJh6iHbu',1,0);
INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('3','Nikola','Nikolic','projekatad@gmail.com','Nis','Srbija','$2a$10$NILdsSbWt8TG2eQFMc8UFuPGLv1vttBgZgS.Et6/WH3pVwkz4w62K',0,1);





INSERT INTO `naucnacentrala`.`magazine` (`id`, `name`,`issnnumber`, `dbfile_id`,`methodpayment`,`maineditor_id`,`merchant_id`,`merchant_password`,`amountmag`,`bitcointoken`,`client_id`,`client_secret`) VALUES ('1','Tehnika','0040-2176','8',0,'1','977dk4mdjw3bz82hd71abgm39dm48f','1','2','VYAwg4CCyDxZDyLeRXinbSkJ6DzGbGQwFFK4utH2','Ae_veWhOruZQCZjrHCUBECmnkXvoEhO_vhIZGCkglx1qvqyGkI22FQVRxtfaeJP1CTL2NZMQJZZHyhXc','EJV26g8cD_0Fl4cCafn17KSp8eDuJEVvVEz_PM3IRSzvh64M-pCqAsz3fx_zraNNL5xTI6q0-kTNJ2HO');
INSERT INTO `naucnacentrala`.`magazine` (`id`, `name`,`issnnumber`, `dbfile_id`,`methodpayment`,`maineditor_id`,`merchant_id`,`merchant_password`,`amountmag`,`bitcointoken`,`client_id`,`client_secret`) VALUES ('2','Bankarstvo','1451-4354','9',0,'2','411sldmcv81yt6j5nf9q2nv0m4u7v9','2','3','63ia_f8W5zsz3yhZmgomWgJ7bqziwLiiQu9eYAmZ','ARaJUwD8O9-cjYkit1QgALKUsO3CdX9Bl_eUA6-rsINvxI-IVFeolaSQqSs8ZUEad3D5AGNmY491zGKd','EAvfBEVBG3lushFJX_oPbgn4kmg-9qxwBPLV9f_Dhda6oWCd864DVh-u7qjlB-IF2Jr2ln-jnD9fSX-Y');
INSERT INTO `naucnacentrala`.`magazine` (`id`, `name`,`issnnumber`, `dbfile_id`,`methodpayment`,`maineditor_id`,`merchant_id`,`merchant_password`,`amountmag`,`bitcointoken`,`client_id`,`client_secret`) VALUES ('3','Gradjevinarstvo','3312-0901','12',1,'3','411vkol342cdax9m18do5a38dvyw9e','3','2','dCJnxHQ1f6Q3HQXfJWqoKRgFuDyfMMLeWFw-FNGN','ATHOXo2z6yhdpIRweuNRpT0DCFtYHTorJydNeTtDyU2U5Z8kChwKSkB1ON_YlDX63Upen-uO_JmdQrDB','EAwpOW2YkI0BTx9I_bZr7u0_CidIz9VkFX-oRSkT80MKgazpSLIxjvPIbroaSL6r2o93C4a65cLnX5uv');


INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('1','abstract', 'finalversion', 'kyeterms', '1', 'Analiza magnetopobudnih sila u multifaznim mašinama u sklopu integrisanih punjača za električna vozila', '1','1','1','verified');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('2','abstract', 'finalversion', 'kyeterms', '2', 'Međulaboratorijska ispitivanja kod širokopojasnih merenja elektromagnetskih polja', '1','1','2','verified');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('3','abstract', 'finalversion', 'kyeterms', '3', 'Sistemi emitovanja i standardi satelitske televizijske transmisije u Evropi', '1','1','2','verified');

INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('4','abstract', 'finalversion', 'kyeterms', '4', 'Uticaj niskotarifnog prevozioca na cene karata tradicionalnog prevozioca na interkontinentalnim letovima', '3','1','2','verified');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('5','abstract', 'finalversion', 'kyeterms', '5', 'Izmena putnika na međumesnim linijama', '3','1','2','verified');

INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('6','abstract', 'finalversion', 'kyeterms', '6', 'Savremena uloga i značaj profesionalnih kvalifikacija u sektoru bankarstva', '2','2','1','verified');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('7','abstract', 'finalversion', 'kyeterms', '7', 'Tradicionalni i novi poslovni modeli u bankarskoj industriji', '2','2','2','verified');


INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('8','abstract', 'finalversion', 'kyeterms', '10', 'Nelinearna seizmička analiza stubova kontinualnog AB mosta', '2','3','1','verified');
INSERT INTO `naucnacentrala`.`labor` (`id`, `abstrct`, `finalversion`, `keyterms`, `dbfile_id`, `title`, `scientificarea_id`,`magazine_id`,`pricelabor`,`state`) VALUES ('9','abstract', 'finalversion', 'kyeterms', '11', 'Odvajanje grede konačne dužine od Vinklerove nezatežuće podloge pri dejstvu sile na kraju grede', '2','3','2','verified');


INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('1','Elektrotehnika','ELE');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('2','Masinstvo','MAS');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('3','Ekonomija','EKON');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('4','Finansija','SAOB');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('5','Saobracaj','SAOB');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('6','Zeleznica','SAOB');

INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('1','1');
INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('1','2');
INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('2','3');
INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('2','4');
INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('3','5');
INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('3','6');



INSERT INTO `naucnacentrala`.`magazine_reviewers` (`magazine_id`, `reviewers_id`) VALUES ('1','2');


INSERT INTO `naucnacentrala`.`magazine_othereditors` (`magazine_id`, `othereditors_id`) VALUES ('1','3');

INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('1','Korisnik sistem', 'USER');
INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('2','Objavljuje radove u magazin', 'AUTHOR');
INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('3','Editor pregleda rad', 'EDITOR');
INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('4','Revjuer prgleda pdf', 'REVIEWER');


INSERT INTO `naucnacentrala`.`user` (`id`, `city`, `confirmpassword`, `country`, `email`, `name`, `password`, `surname`) VALUES ('1','Berlin', '555', 'Germany', 'donthavename3478@gmail.com', 'Maja', '$2a$10$ekSZsklk65QTyPbeM44fK.E9M1E1KbjCp7R/sPRN/iBeeg1av1liW', 'Majic');
INSERT INTO `naucnacentrala`.`user` (`id`, `city`, `confirmpassword`, `country`, `email`, `name`, `password`, `surname`) VALUES ('2','Novi Sad', '888', 'Serbia', 'grujic@gmail.com', 'Aleksandra', '$2a$10$PaIKqe6ReSEtD7mW/H73PO./dzyVDsRsDTobqQKE2Olzn3IisACVC', 'Grujić');
INSERT INTO `naucnacentrala`.`user` (`id`, `city`, `confirmpassword`, `country`, `email`, `name`, `password`, `surname`) VALUES ('3','Novi Sad', '777', 'Serbia', 'milos@gmail.com', 'Milos', '$2a$10$FHj/rYlKZIIt60smslJDO.KO/9W5wGjQwcnYojbL2AxvY4fpBqE1K', 'Amidzic');


INSERT INTO `naucnacentrala`.`user_roles` (`user_id`, `roles_id`) VALUES ('1','2');
INSERT INTO `naucnacentrala`.`user_roles` (`user_id`, `roles_id`) VALUES ('2','1');
INSERT INTO `naucnacentrala`.`user_roles` (`user_id`, `roles_id`) VALUES ('3','2');


INSERT INTO `naucnacentrala`.`editor_reviewer_roles` (`editor_reviewer_id`, `roles_id`) VALUES ('1','3');
INSERT INTO `naucnacentrala`.`editor_reviewer_roles` (`editor_reviewer_id`, `roles_id`) VALUES ('2','3');
INSERT INTO `naucnacentrala`.`editor_reviewer_roles` (`editor_reviewer_id`, `roles_id`) VALUES ('3','3');



INSERT INTO `naucnacentrala`.`membership_fee` (`id`, `startdate`,`enddate`,`amount`,`user_id`,`magazine_id`) VALUES ('1','2018-12-15 00:00:00','2019-01-01 00:00:00','1','2','1');
INSERT INTO `naucnacentrala`.`membership_fee` (`id`, `startdate`,`enddate`,`amount`,`user_id`,`magazine_id`) VALUES ('2','2019-03-09 00:00:00','2019-04-20 00:00:00','2','1','3');

INSERT INTO `naucnacentrala`.`editorsa`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`,`scientifica_area_id`)VALUES('1','Novi Sad','Serbia','projekatad@gmail.com','Zeljana','$2a$10$yOW7DgvDyuoTEQ2ltCMA6O3yp7rTVZt4VSmwI1uLKAAaFQ3AgmZz6','Zeljanic','editor1',1);

INSERT INTO `naucnacentrala`.`editorsa`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`,`scientifica_area_id`)VALUES('3','Novi Sad','Serbia','projekatad@gmail.com','Marko','$2a$10$yOW7DgvDyuoTEQ2ltCMA6O3yp7rTVZt4VSmwI1uLKAAaFQ3AgmZz6','Markovic','editor3',3);
INSERT INTO `naucnacentrala`.`editorsa`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`,`scientifica_area_id`)VALUES('4','Novi Sad','Serbia','projekatad@gmail.com','Zika','$2a$10$yOW7DgvDyuoTEQ2ltCMA6O3yp7rTVZt4VSmwI1uLKAAaFQ3AgmZz6','Zikic','editor4',4);
INSERT INTO `naucnacentrala`.`editorsa`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`,`scientifica_area_id`)VALUES('5','Novi Sad','Serbia','projekatad@gmail.com','Nele','$2a$10$yOW7DgvDyuoTEQ2ltCMA6O3yp7rTVZt4VSmwI1uLKAAaFQ3AgmZz6','Mladenovic','editor5',5);
INSERT INTO `naucnacentrala`.`editorsa`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`,`scientifica_area_id`)VALUES('6','Novi Sad','Serbia','projekatad@gmail.com','Pele','$2a$10$yOW7DgvDyuoTEQ2ltCMA6O3yp7rTVZt4VSmwI1uLKAAaFQ3AgmZz6','Knele','editor6',6);


INSERT INTO `naucnacentrala`.`editorsa_roles`(`editorsa_id`,`roles_id`) VALUES ('1','3');
INSERT INTO `naucnacentrala`.`editorsa_roles`(`editorsa_id`,`roles_id`) VALUES ('2','3');
INSERT INTO `naucnacentrala`.`editorsa_roles`(`editorsa_id`,`roles_id`) VALUES ('3','3');
INSERT INTO `naucnacentrala`.`editorsa_roles`(`editorsa_id`,`roles_id`)  VALUES ('4','3');
INSERT INTO `naucnacentrala`.`editorsa_roles`(`editorsa_id`,`roles_id`)  VALUES ('5','3');
INSERT INTO `naucnacentrala`.`editorsa_roles`(`editorsa_id`,`roles_id`)  VALUES ('6','3');


INSERT INTO `naucnacentrala`.`magazine_editorsa`(`magazine_id`,`editorsa_id`) VALUES ('1','1');

INSERT INTO `naucnacentrala`.`magazine_editorsa`(`magazine_id`,`editorsa_id`) VALUES ('2','3');
INSERT INTO `naucnacentrala`.`magazine_editorsa`(`magazine_id`,`editorsa_id`) VALUES ('2','4');
INSERT INTO `naucnacentrala`.`magazine_editorsa`(`magazine_id`,`editorsa_id`) VALUES ('3','5');
INSERT INTO `naucnacentrala`.`magazine_editorsa`(`magazine_id`,`editorsa_id`) VALUES ('3','6');





INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('1','Novi Sad','Serbia','projekatad@gmail.com','Mitar','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Georgijevic','reviewer1');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('2','Novi Sad','Serbia','projekatad@gmail.com','Dusan','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Amidzic','reviewer2');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('3','Novi Sad','Serbia','projekatad@gmail.com','Zoran','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Jelic','reviewer3');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('4','Novi Sad','Serbia','projekatad@gmail.com','Milos','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Ilicic','reviewer4');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('5','Novi Sad','Serbia','projekatad@gmail.com','Jela','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Karadjordjevic','reviewer5');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('6','Novi Sad','Serbia','projekatad@gmail.com','Borka','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Cvijanovic','reviewer6');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('7','Novi Sad','Serbia','projekatad@gmail.com','Vojin','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Dejanovic','reviewer7');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('8','Novi Sad','Serbia','projekatad@gmail.com','Aleksandar','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Grujic','reviewer8');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('9','Novi Sad','Serbia','projekatad@gmail.com','Ognjen','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Makovski','reviewer9');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('10','Novi Sad','Serbia','projekatad@gmail.com','Sava','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Zecevic','reviewer10');
INSERT INTO `naucnacentrala`.`reviewer`(`id`,`city`,`country`,`email`,`name`,`password`,`surname`,`username`)VALUES('11','Novi Sad','Serbia','projekatad@gmail.com','Anka','$2a$10$bK4l.8aKMUg2/EkbFCJB1.mF8Izajk6aU7bGcacFo5pRk4O9gPp7u','Babic','reviewer11');


INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('1','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('2','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('3','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('4','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('5','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('6','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('7','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('8','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('9','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('10','3');
INSERT INTO `naucnacentrala`.`reviewer_roles`(`reviewer_id`,`roles_id`) VALUES ('11','3');



INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('1','1');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('2','1');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('3','1');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('4','1');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('5','2');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('6','2');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('7','2');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('9','3');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('8','3');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('1','4');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('5','4');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('8','4');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('9','4');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('6','5');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('7','5');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('10','6');
INSERT INTO `naucnacentrala`.`reviewer_scientifica_area`(`reviewer_id`,`scientifica_area_id`) VALUES ('11','6');
