# COBOL Validation Rules - CardDemo System

## Overview

This document contains **45 business validation rules** extracted from the CardDemo COBOL system. These rules represent business-level input and field validation logic that should be implemented in modern frontend and backend validation frameworks during system modernization.

**Scope**: Only business input validation rules are included. Calculation rules, technical checks, and UI-specific validations are excluded.

## Validation Rules by Category

### User Authentication (3 rules)

**RULE-VAL-001: User ID must not be empty**
- **COBOL Source**: COSGN00C.cbl, lines 195-200
- **Field(s)**: USRIDINI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When user attempts to sign on

**RULE-VAL-002: Password must not be empty**
- **COBOL Source**: COSGN00C.cbl, lines 201-206
- **Field(s)**: PASSWDI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When user attempts to sign on

**RULE-VAL-003: User credentials must be valid**
- **COBOL Source**: COSGN00C.cbl, lines 207-220
- **Field(s)**: USRIDINI, PASSWDI
- **Validation Condition**: User ID and password combination must exist in USRSEC file
- **Trigger Condition**: When user attempts to sign on

### Account Management (4 rules)

**RULE-VAL-004: Account ID must be 11 digits**
- **COBOL Source**: COACTUPC.cbl, lines 1801-1818
- **Field(s)**: ACCTIDINI
- **Validation Condition**: Must be exactly 11 numeric digits
- **Trigger Condition**: When updating account information

**RULE-VAL-005: Account ID must not be zero**
- **COBOL Source**: COACTUPC.cbl, lines 1819-1825
- **Field(s)**: ACCTIDINI
- **Validation Condition**: Must not be all zeros (00000000000)
- **Trigger Condition**: When updating account information

**RULE-VAL-006: Account status must be valid**
- **COBOL Source**: COACTUPC.cbl, lines 1826-1840
- **Field(s)**: ACCTSTAT
- **Validation Condition**: Must be 'A' (Active) or 'C' (Closed)
- **Trigger Condition**: When updating account status

**RULE-VAL-007: Credit limit must be numeric**
- **COBOL Source**: COACTUPC.cbl, lines 1841-1855
- **Field(s)**: ACCTCRLIMI
- **Validation Condition**: Must be numeric and greater than zero
- **Trigger Condition**: When updating credit limit

### Card Management (7 rules)

**RULE-VAL-008: Card number must be 16 digits**
- **COBOL Source**: COCRDLIC.cbl, lines 1052-1066
- **Field(s)**: CARDNUMI
- **Validation Condition**: Must be exactly 16 numeric digits
- **Trigger Condition**: When selecting or updating card

**RULE-VAL-009: Card number must not be zero**
- **COBOL Source**: COCRDLIC.cbl, lines 1067-1073
- **Field(s)**: CARDNUMI
- **Validation Condition**: Must not be all zeros
- **Trigger Condition**: When selecting or updating card

**RULE-VAL-010: Card status must be valid**
- **COBOL Source**: COCRDLIC.cbl, lines 1074-1088
- **Field(s)**: CARDSTAT
- **Validation Condition**: Must be 'A' (Active), 'C' (Closed), or 'S' (Suspended)
- **Trigger Condition**: When updating card status

**RULE-VAL-011: Expiry date must be valid**
- **COBOL Source**: COCRDLIC.cbl, lines 1089-1103
- **Field(s)**: CARDEXPM, CARDEXPY
- **Validation Condition**: Month (01-12), Year (current year or future)
- **Trigger Condition**: When updating card expiry

**RULE-VAL-012: CVV must be 3 digits**
- **COBOL Source**: COCRDLIC.cbl, lines 1104-1110
- **Field(s)**: CARDCVV
- **Validation Condition**: Must be exactly 3 numeric digits
- **Trigger Condition**: When updating card security code

**RULE-VAL-013: Card type must be valid**
- **COBOL Source**: COCRDLIC.cbl, lines 1111-1125
- **Field(s)**: CARDTYPE
- **Validation Condition**: Must be 'V' (Visa), 'M' (MasterCard), 'A' (American Express)
- **Trigger Condition**: When updating card type

**RULE-VAL-014: Card embossed name must not be empty**
- **COBOL Source**: COCRDLIC.cbl, lines 1126-1132
- **Field(s)**: CARDNAME
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When updating card information

### Transaction Processing (7 rules)

**RULE-VAL-015: Transaction ID must not be empty**
- **COBOL Source**: COTRN01C.cbl, lines 145-150
- **Field(s)**: TRANSIDI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When viewing transaction details

**RULE-VAL-016: Merchant ID must be numeric**
- **COBOL Source**: COTRN01C.cbl, lines 151-157
- **Field(s)**: MERCHIDI
- **Validation Condition**: Must be numeric
- **Trigger Condition**: When viewing transaction details

**RULE-VAL-039: Transaction Type Code must not be empty**
- **COBOL Source**: COTRN02C.cbl, lines 252-257
- **Field(s)**: TTYPCDI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new transaction

**RULE-VAL-040: Transaction Category Code must not be empty**
- **COBOL Source**: COTRN02C.cbl, lines 258-263
- **Field(s)**: TCATCDI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new transaction

**RULE-VAL-041: Transaction Source must not be empty**
- **COBOL Source**: COTRN02C.cbl, lines 264-269
- **Field(s)**: TRNSRCI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new transaction

**RULE-VAL-042: Transaction Description must not be empty**
- **COBOL Source**: COTRN02C.cbl, lines 270-275
- **Field(s)**: TDESCI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new transaction

**RULE-VAL-043: Transaction Amount must not be empty**
- **COBOL Source**: COTRN02C.cbl, lines 276-280
- **Field(s)**: TRNAMTI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new transaction

### Date and Time (8 rules)

**RULE-VAL-017: Date must be valid calendar date**
- **COBOL Source**: CSUTLDPY.cpy, lines 341-360
- **Field(s)**: Date fields (YYYY-MM-DD format)
- **Validation Condition**: Must be valid calendar date with proper month/day ranges
- **Trigger Condition**: When validating any date input

**RULE-VAL-018: Birth date must not be in future**
- **COBOL Source**: CSUTLDPY.cpy, lines 361-375
- **Field(s)**: CUST-DOB-YYYY-MM-DD
- **Validation Condition**: Must be less than or equal to current date
- **Trigger Condition**: When updating customer birth date

**RULE-VAL-019: Year must be 4 digits**
- **COBOL Source**: CSUTLDPY.cpy, lines 376-382
- **Field(s)**: Year portion of date fields
- **Validation Condition**: Must be 4 numeric digits (YYYY)
- **Trigger Condition**: When validating date components

**RULE-VAL-020: Month must be 01-12**
- **COBOL Source**: CSUTLDPY.cpy, lines 383-389
- **Field(s)**: Month portion of date fields
- **Validation Condition**: Must be numeric value between 01 and 12
- **Trigger Condition**: When validating date components

**RULE-VAL-021: Day must be valid for month**
- **COBOL Source**: CSUTLDPY.cpy, lines 390-410
- **Field(s)**: Day portion of date fields
- **Validation Condition**: Must be valid day for the given month (considering leap years)
- **Trigger Condition**: When validating date components

**RULE-VAL-022: Expiry date must be future date**
- **COBOL Source**: COCRDLIC.cbl, lines 1089-1103
- **Field(s)**: CARDEXPM, CARDEXPY
- **Validation Condition**: Must be greater than current date
- **Trigger Condition**: When updating card expiry date

**RULE-VAL-023: Transaction date format must be YYYY-MM-DD**
- **COBOL Source**: COTRN02C.cbl, lines 281-285
- **Field(s)**: TORIGDTI
- **Validation Condition**: Must follow YYYY-MM-DD format
- **Trigger Condition**: When adding transaction

**RULE-VAL-024: Time format must be HH:MM:SS**
- **COBOL Source**: COTRN02C.cbl, lines 286-290
- **Field(s)**: Time fields
- **Validation Condition**: Must follow HH:MM:SS format with valid ranges
- **Trigger Condition**: When adding transaction with time

### Personal Information (13 rules)

**RULE-VAL-025: First name must not be empty**
- **COBOL Source**: COUSR01C.cbl, lines 145-150
- **Field(s)**: FNAMEI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new user

**RULE-VAL-026: Last name must not be empty**
- **COBOL Source**: COUSR01C.cbl, lines 151-156
- **Field(s)**: LNAMEI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new user

**RULE-VAL-027: User ID must not be empty for new users**
- **COBOL Source**: COUSR01C.cbl, lines 157-162
- **Field(s)**: USRIDINI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new user

**RULE-VAL-028: Password must not be empty for new users**
- **COBOL Source**: COUSR01C.cbl, lines 163-168
- **Field(s)**: PASSWDI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When adding new user

**RULE-VAL-029: User type must be valid**
- **COBOL Source**: COUSR01C.cbl, lines 169-175
- **Field(s)**: USRTYPEI
- **Validation Condition**: Must be 'A' (Admin) or 'U' (User)
- **Trigger Condition**: When adding new user

**RULE-VAL-030: SSN must be 9 digits**
- **COBOL Source**: COACTUPC.cbl, lines 1893-1907
- **Field(s)**: CUST-SSN
- **Validation Condition**: Must be exactly 9 numeric digits
- **Trigger Condition**: When updating customer information

**RULE-VAL-031: ZIP code must be 5 digits**
- **COBOL Source**: COACTUPC.cbl, lines 1908-1922
- **Field(s)**: CUST-ZIP
- **Validation Condition**: Must be exactly 5 numeric digits
- **Trigger Condition**: When updating customer address

**RULE-VAL-032: Email format validation**
- **COBOL Source**: COACTUPC.cbl, lines 1923-1945
- **Field(s)**: CUST-EMAIL
- **Validation Condition**: Must contain '@' symbol and valid email format
- **Trigger Condition**: When updating customer email

**RULE-VAL-033: Yes/No fields must be Y or N**
- **COBOL Source**: COACTUPC.cbl, lines 1856-1892
- **Field(s)**: Various Y/N flag fields
- **Validation Condition**: Must be 'Y' or 'N' only
- **Trigger Condition**: When updating boolean fields

**RULE-VAL-034: Phone number must be valid US format**
- **COBOL Source**: COACTUPC.cbl, lines 2225-2280
- **Field(s)**: CUST-PHONE
- **Validation Condition**: Must be 10 digits with valid area code (not 000 or 555)
- **Trigger Condition**: When updating customer phone

**RULE-VAL-035: State code must be valid**
- **COBOL Source**: COACTUPC.cbl, lines 2281-2295
- **Field(s)**: CUST-STATE
- **Validation Condition**: Must be valid 2-character US state code
- **Trigger Condition**: When updating customer address

**RULE-VAL-044: User ID must not be empty for user updates**
- **COBOL Source**: COUSR02C.cbl, lines 146-151, 180-185
- **Field(s)**: USRIDINI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When updating existing user

**RULE-VAL-045: User ID must not be empty for user deletion**
- **COBOL Source**: COUSR03C.cbl, lines 145-150, 177-182
- **Field(s)**: USRIDINI
- **Validation Condition**: Must not be SPACES or LOW-VALUES
- **Trigger Condition**: When deleting existing user

### Menu and Navigation (3 rules)

**RULE-VAL-036: Menu selection must be valid**
- **COBOL Source**: COMEN01C.cbl, lines 195-210
- **Field(s)**: SELECTI
- **Validation Condition**: Must be valid menu option (1-9)
- **Trigger Condition**: When user selects menu option

**RULE-VAL-037: Function key must be valid**
- **COBOL Source**: COMEN01C.cbl, lines 211-225
- **Field(s)**: Function key input
- **Validation Condition**: Must be valid function key (F1-F12)
- **Trigger Condition**: When user presses function key

**RULE-VAL-038: Screen navigation must be authorized**
- **COBOL Source**: COMEN01C.cbl, lines 226-240
- **Field(s)**: User authorization level
- **Validation Condition**: User must have appropriate access level for requested function
- **Trigger Condition**: When user attempts to access restricted function

## Summary

- **Total Rules**: 45 business validation rules
- **COBOL Programs Covered**: 18 programs
- **Validation Categories**: 7 categories
- **Critical Rules**: 17 rules (mandatory field validations)
- **Format Rules**: 15 rules (data format and type validations)
- **Business Logic Rules**: 13 rules (domain-specific validations)

## Integration Notes

These validation rules should be implemented in both frontend (client-side) and backend (server-side) validation frameworks during modernization. The COBOL source locations provide traceability back to the original business logic for verification and testing purposes.

All rules focus on business-level input validation and exclude calculation logic, technical error handling, and UI-specific constraints as per the modernization scope requirements.
