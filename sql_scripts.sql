use dev_wellcare;
select * from doctors;
select * from patients;
select * from users;
desc users;
desc doctors;
SELECT * FROM users WHERE username = 'Dino123';

ALTER TABLE users MODIFY role VARCHAR(20);
ALTER TABLE doctors DROP COLUMN doc_phone;
ALTER TABLE doctors 
MODIFY COLUMN doc_mobile VARCHAR(255) NULL;

/*
this query I had to use when I added the Patients
*/
ALTER TABLE users MODIFY role_ads ENUM('ADMIN', 'DOCTOR', 'PATIENT'); 
