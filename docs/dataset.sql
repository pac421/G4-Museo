INSERT INTO `ARTIST` (`id`,`firstname`,`lastname`,`period`) VALUES 
("2D03B69A-D2A4-ECC9-3AF3-7EEA8430E582","Vaughan","Dunn","pharetra. Quisque"),
("D3A33EA3-5095-1F77-0F62-5F060C7DE888","Yoshi","Merritt","eu nulla"),
("AA6C168F-3F03-1692-FB0B-0B134BDA054C","Sandra","Kennedy","Curae; Phasellus"),
("42ADEF38-DE7A-FF22-4403-DA2AFCACFA4C","Jaquelyn","Cruz","est. Nunc"),
("99BA3265-6D41-FE39-0A1B-C07BF4265EEF","Jayme","Barnett","Aliquam rutrum"),
("46F552C5-C3EF-DED4-E1BE-461E4AB99011","MacKenzie","Bush","orci, adipiscing"),
("FFDBD2E0-815B-5078-4638-ECDDACB8FDEF","Ivor","Bentley","orci lacus"),
("13380C4D-4E98-1C78-7663-797D55664933","Clarke","Bailey","mi, ac"),
("FEAD2954-8B95-83D9-4387-51332FDA6F61","Orlando","Zimmerman","facilisis lorem"),
("D85AE153-9196-3C15-B091-E30F640D1164","Alana","Hunter","cursus, diam"),
("D736565A-FA74-4239-97D3-6F7ABA22C94A","Buckminster","Harding","et malesuada");

INSERT INTO `ROLE` (`id`,`label`) VALUES 
("8570B50D-6A6D-D833-FD11-9AE506A3B61F","admin"),
("6C2A3497-F3C1-11F1-6241-A195D7E9FA41","user"),
("DF97E3F9-5686-46FE-2DA7-1D91794290B2","superadmin");

INSERT INTO `USER` (`id`,`firstname`,`lastname`,`email`,`password`,`role_id`) VALUES 
("16121B9C-A6BD-0963-41DC-AF243E5433B7","Nasim","Cash","dictum.Proin.eget@nasceturridiculus.com","RLC03HEB1CP", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("A091861D-A85B-F958-10C0-8D768A7C586C","Mariko","Oneill","arcu@loremlorem.com","LAU20OIC5QT", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("0D2B13D6-F239-9F6A-1C79-157BA40D38C9","Mechelle","Turner","Aliquam@orciUtsemper.edu","SXI45XJI0ZU", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("5E763F4E-DC1B-DE84-4A9D-B129950702F6","Matthew","Cross","lobortis@et.edu","ZQG16ILV5LY", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("92068823-E2D8-9630-DC77-85D60715A220","Vaughan","Richard","lobortis@Quisqueliberolacus.org","DMG41SEB6HU", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("F7A6E62D-08B4-7123-D15B-7EA45D71F197","Jane","Harding","aliquet.Proin.velit@consequatnec.co.uk","IUG21KSC4IQ", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("AD2F1946-A9D3-E0D6-5DAF-4A42C977586E","Akeem","Holman","augue.id.ante@tinciduntDonec.edu","XQN98HWS4YO", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("8730ECAF-6599-F019-117E-FF755B899794","Anastasia","Huber","imperdiet@dolorQuisquetincidunt.net","YYT43KZF1AS", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("3CD07DEF-3DF1-EF21-089E-4D2A989599C9","Raymond","Flores","pede@dictummi.edu","IEY45JVJ6VZ", "6C2A3497-F3C1-11F1-6241-A195D7E9FA41"),
("25C794E9-D0D4-9023-9723-5D2FDCE4B785","SuperAdmin","Museo","superAdmin@museo.com","OLD18IQD0VM", "DF97E3F9-5686-46FE-2DA7-1D91794290B2"),
("69EAAEAF-C9E5-5632-2287-A7CA079AD48A","Admin","Museo","admin@museo.com","VBA61OQK3XB","8570B50D-6A6D-D833-FD11-9AE506A3B61F");

INSERT INTO `COLLECTION` (`id`,`label`,`period`) VALUES 
("FC72ECCD-679F-6F1C-26A6-8EA7C91BB5FF","gravida","et magnis"),
("3E884099-1312-A88C-603B-F01E23017ED1","arcu.","quis diam."),
("690142F7-B9FD-8CC5-335B-32E494643F55","eu","viverra. Donec"),
("D98DDC2C-A509-51FC-B661-07E5E7630C1B","lorem","Etiam gravida"),
("853DA489-3324-DD15-7949-5CF6BF54F5C9","nec","sagittis. Nullam"),
("268DAAF3-8E70-E3DB-6E22-64AF78236F16","vestibulum","vitae velit"),
("DC821F2D-F74D-6220-87E3-F168E28243EF","faucibus","cursus in,"),
("D668AE43-147B-29B7-BA1B-E0DAFEAE7FD1","et","hendrerit neque."),
("F4FDDCB7-6C8B-9E9F-46F7-F4CFAD8E41B7","amet,","ante. Vivamus"),
("BFF6D928-6963-85BC-15B9-920883E19175","ante","auctor non,"),
("FDCFC613-0862-520D-8915-116DD3C9988C","bibendum","In nec");

INSERT INTO `CATEGORY` (`id`,`label`) VALUES 
("57D52BB7-A944-ABC3-706A-CB4C127D3222","ipsum"),
("1D90FF6C-ED7A-5A19-285C-0AB0D9089B38","Suspendisse"),
("E362BEBE-264B-8160-5253-531B3279F37C","tristique"),
("2287CA34-8952-4476-13A5-52687E860204","lacinia"),
("CFBBCDCD-20A6-0B3E-FE6C-B202A289E56C","euismod"),
("D231F0D2-05A6-B318-3132-1A3170871D48","quam"),
("2039772A-F1CF-A2C4-84B4-44147225B70F","nec"),
("941F6FF6-E176-1CC7-CE88-37FBDF152060","ligula"),
("018B3E35-3425-0F4B-651A-E13D488B83F3","magna"),
("4E2F4787-9DB5-A5A7-E55E-47A829077CB6","vehicula"),
("1DB4BB9D-D29D-5F53-F19A-31759B4DECAC","fermentum");

INSERT INTO `STATE` (`id`,`label`) VALUES 
("0387EE97-336F-F2E0-00E3-11508AA69292","prêt"),
("1793E409-F90C-76E7-0D87-B999FF1F8E5E","restauration"),
("FBD34109-CFE4-C766-80ED-A218864C6D22","exposition"),
("F9868739-8E68-47D0-A9C6-46D4E9BA4F98","rendue"),
("436F79EC-E857-2833-3935-B8C8C0FC5DEC","stockée"),
("522211E0-2F45-9BC1-11D8-6E82AD4A511E","archivée");

INSERT INTO `WORK` (`id`,`label`,`description`,`period`,`height`,`width`,`depth`,`deleted_at`,`collection_id`,`category_id`,`weight`,`deleted_by_id`) VALUES 
("A774BB14-7584-5CBC-4F95-99838EB2F395","non,","non, dapibus rutrum,","convallis ligula.",1,2,2,NULL,"FC72ECCD-679F-6F1C-26A6-8EA7C91BB5FF","57D52BB7-A944-ABC3-706A-CB4C127D3222",82,NULL),
("33B84493-12E7-C2FC-BBA1-B1CE5C3B5D5A","lorem","luctus et ultrices posuere","vitae, orci.",1,1,2,NULL,"3E884099-1312-A88C-603B-F01E23017ED1","1D90FF6C-ED7A-5A19-285C-0AB0D9089B38",57,NULL),
("4CFDBBCB-9F9D-8175-463E-71E6E169A5DA","non,","hendrerit a, arcu.","quis diam",3,3,2,NULL,"690142F7-B9FD-8CC5-335B-32E494643F55","E362BEBE-264B-8160-5253-531B3279F37C",59,NULL),
("6FDE525D-5C02-377B-3C91-2375BD0DDDDF","risus.","convallis ligula. Donec luctus aliquet","vulputate mauris",1,3,2,NULL,"D98DDC2C-A509-51FC-B661-07E5E7630C1B","E362BEBE-264B-8160-5253-531B3279F37C",22,NULL),
("2D0C567C-3FDA-3C95-E0E3-3CF2CC0A4EF2","facilisis","libero at auctor","Integer vulputate,",2,1,2,NULL,"853DA489-3324-DD15-7949-5CF6BF54F5C9","2287CA34-8952-4476-13A5-52687E860204",84,NULL);

INSERT INTO `PICTURE` (`id`,`extension`,`work_id`) VALUES 
("F0FFF8D7-4015-ED77-71F3-4EF385BB4039","png","A774BB14-7584-5CBC-4F95-99838EB2F395"),
("5C3DA1FC-30D9-91C6-ACE8-AD47DBB0F0B1","jpeg","33B84493-12E7-C2FC-BBA1-B1CE5C3B5D5A"),
("5EAFF66A-D7D7-0A5A-4BD4-0088EF8F583D","png","4CFDBBCB-9F9D-8175-463E-71E6E169A5DA"),
("8779258A-AC0F-F108-AF6E-7D3DB95D93BD","png","6FDE525D-5C02-377B-3C91-2375BD0DDDDF"),
("121842BB-8460-E6A5-A7A9-A58D10B92D68","jpeg","2D0C567C-3FDA-3C95-E0E3-3CF2CC0A4EF2"),
("8D3DDD9E-3140-61DF-7C67-6C0C8229654F","png","2D0C567C-3FDA-3C95-E0E3-3CF2CC0A4EF2"),
("CA8AFDF8-41A2-E7AB-AABE-FAA860E1C62F","png","4CFDBBCB-9F9D-8175-463E-71E6E169A5DA"),
("E5EDA672-CE83-0624-01E5-975FC5BC1616","jpeg","A774BB14-7584-5CBC-4F95-99838EB2F395"),
("DE7D9931-FBE6-8F59-CD23-FEB53C45045A","jpeg","A774BB14-7584-5CBC-4F95-99838EB2F395");

INSERT INTO `PROPERTY` (`work_id`,`owned_at`,`owned_from`,`price`) VALUES 
("A774BB14-7584-5CBC-4F95-99838EB2F395","2021-11-13 20:12:15","Casey Erickson",2692927),
("33B84493-12E7-C2FC-BBA1-B1CE5C3B5D5A","2022-01-12 14:25:06","Kessie Gutierrez",1166196),
("4CFDBBCB-9F9D-8175-463E-71E6E169A5DA","2021-01-19 15:39:25","Rose V. Reese",2823850);

INSERT INTO `PROPERTY_STATE` (`id`,`property_id`,`state_id`,`start`,`end`,`comment`) VALUES 
("A774BB14-7584-5CBC-4F95-99838EB2F398","A774BB14-7584-5CBC-4F95-99838EB2F395","FBD34109-CFE4-C766-80ED-A218864C6D22","2020-05-11 19:57:20","2020-07-11 19:57:20","et ultrices posuere"),
("33B84493-12E7-C2FC-BBA1-B1CE5C3B5D5B","33B84493-12E7-C2FC-BBA1-B1CE5C3B5D5A","1793E409-F90C-76E7-0D87-B999FF1F8E5E","2021-02-02 21:44:09","2021-04-02 21:44:09","Donec luctus aliquet"),
("4CFDBBCB-9F9D-8175-463E-71E6E169A5DC","4CFDBBCB-9F9D-8175-463E-71E6E169A5DA","436F79EC-E857-2833-3935-B8C8C0FC5DEC","2021-03-06 23:32:27","2021-05-06 23:32:27","Integer vulputate");

INSERT INTO `LEND` (`work_id`,`start`,`end`,`lender`) VALUES 
("6FDE525D-5C02-377B-3C91-2375BD0DDDDF","2020-10-06 07:32:25","2020-12-06 07:32:25","Libby V. Rosario"),
("2D0C567C-3FDA-3C95-E0E3-3CF2CC0A4EF2","2022-04-08 09:59:48","2022-06-08 09:59:48","Kasimir W. Sawyer");

INSERT INTO `ARTIST_WORK` (`work_id`,`artist_id`) VALUES 
("A774BB14-7584-5CBC-4F95-99838EB2F395","2D03B69A-D2A4-ECC9-3AF3-7EEA8430E582"),
("A774BB14-7584-5CBC-4F95-99838EB2F395","D3A33EA3-5095-1F77-0F62-5F060C7DE888"),
("33B84493-12E7-C2FC-BBA1-B1CE5C3B5D5A","AA6C168F-3F03-1692-FB0B-0B134BDA054C"),
("4CFDBBCB-9F9D-8175-463E-71E6E169A5DA","42ADEF38-DE7A-FF22-4403-DA2AFCACFA4C"),
("4CFDBBCB-9F9D-8175-463E-71E6E169A5DA","99BA3265-6D41-FE39-0A1B-C07BF4265EEF"),
("6FDE525D-5C02-377B-3C91-2375BD0DDDDF","46F552C5-C3EF-DED4-E1BE-461E4AB99011"),
("2D0C567C-3FDA-3C95-E0E3-3CF2CC0A4EF2","46F552C5-C3EF-DED4-E1BE-461E4AB99011");
