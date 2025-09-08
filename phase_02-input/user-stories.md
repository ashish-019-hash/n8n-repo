# CardDemo COBOL Application - Business User Stories (Reviewed)

## Overview

This document contains refined business-level user stories extracted from the CardDemo COBOL codebase. These stories focus exclusively on end-user functionality and business value, suitable for modernizing the system into a user-centric web or mobile application.

The stories are organized by functional modules and exclude all technical implementation details, focusing purely on what users want to achieve and the business value they derive.

## User Types

- **Customer**: Regular users who manage their own accounts, cards, and transactions
- **Administrator**: System administrators who manage users and have elevated privileges
- **Account Holder**: Customers with active credit card accounts

---

## Authentication & Access Management

### User Authentication
**As a** customer  
**I want to** log into the system with my user ID and password  
**So that** I can securely access my account information and perform banking operations

**As a** customer  
**I want to** receive clear error messages when I enter incorrect login credentials  
**So that** I understand what went wrong and can correct my input

**As a** customer  
**I want to** be automatically directed to the appropriate dashboard based on my user type  
**So that** I only see functions I'm authorized to use

**As a** customer  
**I want to** exit the application securely  
**So that** my session is properly terminated and my data remains protected

---

## Account Management

### Account Information Access
**As a** customer  
**I want to** view my account details including account ID, customer information, and current balance  
**So that** I can monitor my account status and financial position

**As a** customer  
**I want to** search for my account using account ID or customer ID  
**So that** I can quickly locate and access my account information

### Account Maintenance
**As a** customer  
**I want to** update my account information including credit limits and personal details  
**So that** I can keep my account current and adjust my credit arrangements

**As a** customer  
**I want to** modify my credit limit and cash advance limit  
**So that** I can adjust my borrowing capacity based on my financial needs

**As a** customer  
**I want to** update my current balance and cycle information  
**So that** my account reflects accurate financial data

---

## Credit Card Management

### Card Portfolio Overview
**As a** customer  
**I want to** view a list of all my credit cards  
**So that** I can see my complete card portfolio and manage multiple cards

**As a** customer  
**I want to** see card details including card number, account association, and status  
**So that** I can identify and manage specific cards

### Card Operations
**As a** customer  
**I want to** select a specific credit card to view detailed information  
**So that** I can review card-specific terms, limits, and usage

**As a** customer  
**I want to** update credit card information and settings  
**So that** I can maintain accurate card details and preferences

**As a** customer  
**I want to** browse through all my credit cards when I have many  
**So that** I can access and manage my complete card portfolio

---

## Transaction Management

### Transaction History
**As a** customer  
**I want to** view a list of all my transactions  
**So that** I can monitor my spending and account activity

**As a** customer  
**I want to** see transaction details including date, amount, merchant, and type  
**So that** I can understand each transaction and verify its accuracy

**As a** customer  
**I want to** browse through my complete transaction history  
**So that** I can review my spending patterns over time

### Transaction Processing
**As a** customer  
**I want to** add new transactions to my account  
**So that** I can record purchases, payments, or other account activities

**As a** customer  
**I want to** specify transaction details including amount, date, and description  
**So that** my transaction records are complete and accurate

**As a** customer  
**I want to** associate transactions with specific credit cards and accounts  
**So that** transactions are properly categorized and tracked

---

## Bill Payment & Financial Operations

### Payment Processing
**As a** customer  
**I want to** pay my credit card bill online  
**So that** I can conveniently manage my payments without visiting a branch

**As a** customer  
**I want to** pay my full account balance  
**So that** I can clear my outstanding debt and avoid interest charges

**As a** customer  
**I want to** see my current balance before making a payment  
**So that** I know exactly how much I owe and can plan my payment

**As a** customer  
**I want to** receive confirmation after making a payment  
**So that** I have proof of payment and peace of mind

### Payment Validation
**As a** customer  
**I want to** have my payment amount validated before processing  
**So that** I don't accidentally make incorrect payments

**As a** customer  
**I want to** see updated account balances after payment  
**So that** I can verify my payment was processed correctly

---

## Reporting & Analytics

### Transaction Reporting
**As a** customer  
**I want to** generate transaction reports for specific time periods  
**So that** I can analyze my spending patterns and create financial summaries

**As a** customer  
**I want to** view formatted reports with transaction details  
**So that** I can use the information for budgeting and financial planning

**As a** customer  
**I want to** filter transactions by date range  
**So that** I can focus on specific periods for analysis

---

## User Administration (Admin Only)

### User Management
**As an** administrator  
**I want to** view a list of all system users  
**So that** I can monitor user accounts and system access

**As an** administrator  
**I want to** add new users to the system  
**So that** I can grant access to new customers or staff members

**As an** administrator  
**I want to** update existing user information and permissions  
**So that** I can maintain accurate user records and appropriate access levels

**As an** administrator  
**I want to** delete user accounts when necessary  
**So that** I can remove access for users who no longer need system access

### Security Management
**As an** administrator  
**I want to** manage user passwords and security settings  
**So that** I can ensure system security and help users with access issues

**As an** administrator  
**I want to** assign user types and permission levels  
**So that** users have appropriate access to system functions

---

## Application Navigation

### User Interface Navigation
**As a** customer  
**I want to** access all available functions through an intuitive interface  
**So that** I can easily find and use the features I need

**As a** customer  
**I want to** return to the main dashboard from any page  
**So that** I can navigate freely throughout the application

### Error Handling
**As a** customer  
**I want to** receive clear error messages when something goes wrong  
**So that** I understand the problem and know how to resolve it

**As a** customer  
**I want to** be guided to correct input errors efficiently  
**So that** I can complete my tasks without frustration

### User Experience
**As a** customer  
**I want to** maintain my work progress as I navigate through the application  
**So that** I don't lose my work or have to re-enter information

**As a** customer  
**I want to** have my user preferences remembered throughout my session  
**So that** the system can provide personalized service

---

## Credit Assessment & Risk Management

### Credit Score Management
**As a** customer  
**I want to** view my current credit score and rating  
**So that** I can understand my creditworthiness and eligibility for credit products

**As a** customer  
**I want to** track changes in my credit score over time  
**So that** I can monitor my credit health and financial progress

**As an** administrator  
**I want to** validate that credit scores fall within acceptable ranges (300-850)  
**So that** I can ensure accurate credit assessments and risk management

### Personal Information Management
**As a** customer  
**I want to** update my Social Security Number with proper validation  
**So that** I can ensure my identity information is accurate and secure

**As a** customer  
**I want to** update my phone number with format validation  
**So that** I can ensure the bank can contact me reliably

**As a** customer  
**I want to** update my address with state validation  
**So that** I can ensure my location information is accurate for compliance

### Financial Risk Management
**As a** customer  
**I want to** request credit limit increases based on my financial profile  
**So that** I can access more credit when my financial situation improves

**As a** customer  
**I want to** request cash advance limit adjustments  
**So that** I can manage my short-term cash flow needs

**As an** administrator  
**I want to** review and approve credit limit changes  
**So that** I can manage the bank's credit risk exposure

---

## Summary

These user stories represent the core business functionality of the CardDemo COBOL application from an end-user perspective. They focus on:

- **Customer Self-Service**: Account and card management, transaction history, bill payment
- **Financial Operations**: Balance inquiries, payment processing, transaction recording
- **Administrative Functions**: User management and system administration
- **User Experience**: Intuitive navigation, error handling, and personalized service

The stories are designed to support modernization efforts by clearly defining business requirements independent of the current COBOL implementation, making them suitable for translation into modern web or mobile applications.

**Total User Stories**: 44  
**Functional Areas Covered**: 8  
**User Types Addressed**: 3 (Customer, Administrator, Account Holder)

---

*Reviewed and refined from CardDemo COBOL codebase analysis*  
*Source: 01.phase-1-output/user-stories.md*  
*Review Date: July 16, 2025*
