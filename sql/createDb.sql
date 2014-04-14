DROP database AdmissionCommittee;
CREATE database IF NOT EXISTS  AdmissionCommittee DEFAULT CHARACTER SET utf8;

USE AdmissionCommittee;

GRANT SELECT, INSERT, UPDATE, DELETE, RELOAD, SHUTDOWN, PROCESS, FILE, SUPER ON *.* 
TO 'Administrator'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;

GRANT SELECT ON AdmissionCommittee.Faculty 
TO 'user'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;

GRANT SELECT ON AdmissionCommittee.Subjects 
TO 'user'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;