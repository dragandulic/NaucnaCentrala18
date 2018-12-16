INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('1','Bojana','Bojanic','boki@gmail.com','Novi Sad','Srbija','111',1,0);
INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('2','Nikola','Nikolic','nina@gmail.com','Nis','Srbija','222',0,1);
INSERT INTO `naucnacentrala`.`editor_reviewer` (`id`, `name`,`surname`,`email`,`city`,`country`,`password`, `iseditor`, `isreviewer`) VALUES ('3','Aleksandra','Grujic','saska@gmail.com','Novi Sad','Srbija','333',1,0);


INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('1','Biotehnicke nauke','med1');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('2','Drustvene nauke','dru2');
INSERT INTO `naucnacentrala`.`scientific_area` (`id`, `name`,`code`) VALUES ('3','Prirodne nauke','pri3');


INSERT INTO `naucnacentrala`.`magazine` (`id`, `name`,`issnnumber`,`methodpayment`,`maineditor_id`) VALUES ('1','Matematicki vesnik','0332-4907',1,'3');


INSERT INTO `naucnacentrala`.`magazine_scientificarea` (`magazine_id`, `scientificarea_id`) VALUES ('1','1');


INSERT INTO `naucnacentrala`.`magazine_reviewers` (`magazine_id`, `reviewers_id`) VALUES ('1','2');


INSERT INTO `naucnacentrala`.`magazine_othereditors` (`magazine_id`, `othereditors_id`) VALUES ('1','3');

INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('1','Korisnik sistem', 'USER');
INSERT INTO `naucnacentrala`.`role` (`id`, `description`, `name`) VALUES ('2','Autor sistema', 'AUTHOR');