-- 全面轉向 InnoDB
ALTER TABLE appconfigs ENGINE=InnoDB; 
ALTER TABLE articles ENGINE=InnoDB; 
ALTER TABLE article_tags ENGINE=InnoDB; 
ALTER TABLE exceptions ENGINE=InnoDB; 
ALTER TABLE logs ENGINE=InnoDB; 
ALTER TABLE tags ENGINE=InnoDB; 
ALTER TABLE upfiles ENGINE=InnoDB; 
ALTER TABLE users ENGINE=InnoDB; 
