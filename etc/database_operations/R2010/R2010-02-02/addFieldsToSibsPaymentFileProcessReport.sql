
ALTER TABLE SIBS_PAYMENT_FILE_PROCESS_REPORT ADD COLUMN STANDALONE_ENROLMENT_GRATUITY_EVENT_AMOUNT varchar(255);

UPDATE SIBS_PAYMENT_FILE_PROCESS_REPORT SET STANDALONE_ENROLMENT_GRATUITY_EVENT_AMOUNT = '0.0';