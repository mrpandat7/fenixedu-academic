ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH DROP COLUMN WORKED_DAYS;
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN WORKED_DAYS_WITH_BONUS_DAYS_DISCOUNT  int(2) NOT NULL default '0' AFTER MAXIMUM_WORKING_DAYS;
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN WORKED_DAYS_WITH_A17_VACATIONS_DAYS_DISCOUNT  int(2) NOT NULL default '0' AFTER WORKED_DAYS_WITH_BONUS_DAYS_DISCOUNT;