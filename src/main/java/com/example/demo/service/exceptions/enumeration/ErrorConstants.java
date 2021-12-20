package com.example.demo.service.exceptions.enumeration;

public enum ErrorConstants {
    GENERAL {
        @Override
        public String getValue() {
            return "error.general";
        }

        @Override
        public String getMessage() {
            return "general error";
        }
    },
    NOT_FOUND {
        @Override
        public String getValue() {
            return "error.not_found";
        }

        @Override
        public String getMessage() {
            return "NOT_FOUND";
        }
    },
    QUERY_FILTER_NOT_IMPLEMENTED {
        @Override
        public String getValue() {
            return "error.query_filter_not_implemented";
        }

        @Override
        public String getMessage() {
            return "QUERY_FILTER_NOT_IMPLEMENTED";
        }
    },
    METHOD_NOT_IMPLEMENTED {
        @Override
        public String getValue() {
            return "error.method_not_implemented";
        }

        @Override
        public String getMessage() {
            return "METHOD_NOT_IMPLEMENTED";
        }
    },
    QUERY_FILTER_NOT_IMPLEMENTED_INVALID_PERMISSION {
        @Override
        public String getValue() {
            return "error.query_filter_not_implemented_invalid_permission";
        }

        @Override
        public String getMessage() {
            return "QUERY_FILTER_NOT_IMPLEMENTED_INVALID_PERMISSION";
        }
    },
    LOGIN_REQUESTED {
        @Override
        public String getValue() {
            return "error.login_is_requested";
        }

        @Override
        public String getMessage() {
            return "The login is requested";
        }
    },
    ACCESS_DENIED {
        @Override
        public String getValue() {
            return "error.accessDenied";
        }

        @Override
        public String getMessage() {
            return "You don't have access to this resource";
        }
    },
    EMPTY_EMAIL {
        @Override
        public String getValue() {
            return "error.empty_email";
        }

        @Override
        public String getMessage() {
            return "No email found to send mail";
        }
    },
    INCORRECT_LOGIN_OR_PASSWORD {
        @Override
        public String getValue() {
            return "error.incorrect_login_or_password";
        }

        @Override
        public String getMessage() {
            return "Incorrect login or password";
        }
    },
    USER_HAS_NO_ACCESS_TO_THIS_ITEM {
        @Override
        public String getValue() {
            return "user.has.no.access.to.this.item";
        }

        @Override
        public String getMessage() {
            return "User has no access to this item";
        }
    },
    USER_NOT_ACTIVATED {
        @Override
        public String getValue() {
            return "error.user_not_activated";
        }

        @Override
        public String getMessage() {
            return "User is not active from LDAP server anymore";
        }
    },

    INCORRECT_USER_RESET_KEY {
        @Override
        public String getValue() {
            return "error.incorrect_user_reset_key";
        }

        @Override
        public String getMessage() {
            return "No user was found for this reset key";
        }
    },
    INCORRECT_USER_ACTIVATION_KEY {
        @Override
        public String getValue() {
            return "error.incorrect_user_activation_key";
        }

        @Override
        public String getMessage() {
            return "No user was found for this activation key";
        }
    },
    RESET_KEY_DATE_EXPIRED {
        @Override
        public String getValue() {
            return "error.reset.key.date.expired";
        }

        @Override
        public String getMessage() {
            return "Expired reset key date";
        }
    },
    INVALID_PASSWORD {
        @Override
        public String getValue() {
            return "error.invalid_password";
        }

        @Override
        public String getMessage() {
            return "Invalid password";
        }
    },
    INVALID_LDAP_EMAIL_OR_CREDENTIALS {
        @Override
        public String getValue() {
            return "error.invalid_ldap_email_or_credentials";
        }

        @Override
        public String getMessage() {
            return "Invalid credentials from LDAP authentication";
        }
    },
    EMPTY_AUTHORITIES {
        @Override
        public String getValue() {
            return "error.empty.authorities";
        }

        @Override
        public String getMessage() {
            return "User Authorities cannot be empty.";
        }
    },
    CONCURRENCY_FAILURE {
        @Override
        public String getValue() {
            return "error.concurrencyFailure";
        }

        @Override
        public String getMessage() {
            return "Concurrency error in database access";
        }
    },
    CONSTRAINT_VIOLATION_EXCEPTION {
        @Override
        public String getValue() {
            return "error.constraint.violation.exception";
        }

        @Override
        public String getMessage() {
            return "This object has relationship with other object, please remove the relationship and try again!";
        }
    },
    INVALID_PARAMS {
        @Override
        public String getValue() {
            return "error.invalidParams";
        }

        @Override
        public String getMessage() {
            return "Invalid params passed to the method_";
        }
    },
    VALIDATION {
        @Override
        public String getValue() {
            return "error.validation";
        }

        @Override
        public String getMessage() {
            return "Validation error (missing / invalid payload) for entity provided_";
        }
    },
    EMAIL_ALREADY_IN_USE {
        @Override
        public String getValue() {
            return "error.email.already.in.use";
        }

        @Override
        public String getMessage() {
            return "The provided email is already in use";
        }
    },
    SECTOR_NAME_ALREADY_EXISTS {
        @Override
        public String getValue() {
            return "error.sector.sector_name_already_exists";
        }

        @Override
        public String getMessage() {
            return "Sector name already exists";
        }
    },
    PATIENT_NAME_ALREADY_EXISTS {
        @Override
        public String getValue() {
            return "error.patient.patient_name_already_exists";
        }

        @Override
        public String getMessage() {
            return "Patient name already exists";
        }
    },
    TOKEN_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.token.not.found";
        }

        @Override
        public String getMessage() {
            return "No user has this token";
        }
    },
    INVALID_AUTH_CODE {
        @Override
        public String getValue() {
            return "error.invalid_auth_code";
        }

        @Override
        public String getMessage() {
            return "INVALID_AUTH_CODE";
        }
    },
    INVALID_AUTHORITY_ROLE {
        @Override
        public String getValue() {
            return "error.invalid_auth_role_for_user";
        }

        @Override
        public String getMessage() {
            return "Invalid authority role for User";
        }
    },
    TOKEN_EXPIRED {
        @Override
        public String getValue() {
            return "error.token.expired";
        }

        @Override
        public String getMessage() {
            return "Expired token";
        }
    },
    IMAGE_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.image.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided ID for image wasn't found.";
        }
    },
    IMAGE_ID_CANNOT_BE_NULL {
        @Override
        public String getValue() {
            return "error.image.id.cannot.be.null";
        }

        @Override
        public String getMessage() {
            return "The provided ID to update image cannot null.";
        }
    },
    S3_URL_NOT_AVAILABLE {
        @Override
        public String getValue() {
            return "error.s3_url";
        }

        @Override
        public String getMessage() {
            return "The URL for this resource could not be generated_";
        }
    },
    EMAIL_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.email_not_registered";
        }

        @Override
        public String getMessage() {
            return "Email address not registered";
        }
    },
    USER_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.user_not_found";
        }

        @Override
        public String getMessage() {
            return "The informed user is not found";
        }
    },
    CITY_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.city.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided ID for city wasn't found.";
        }
    },
    ADDRESS_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.address.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for address wasn't found.";
        }
    },
    CONTRACT_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.contract.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for contract wasn't found.";
        }
    },
    DOCTOR_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.doctor.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for doctor wasn't found.";
        }
    },
    SERVICE_TYPE_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.service.type.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for service type wasn't found.";
        }
    },
    ATTACHMENT_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.attachment.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for attachment wasn't found.";
        }
    },
    ATTACHMENT_WITHOUT_CONTENT {
        @Override
        public String getValue() {
            return "error.attachment_without_content";
        }

        @Override
        public String getMessage() {
            return "The provided Id for attachment wasn't found.";
        }
    },
    CONTRACT_ATTACHMENT_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.contract.attachment.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for contract attachment wasn't found.";
        }
    },
    COMPANY_DATA_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.company.data.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for company data wasn't found.";
        }
    },
    PAYMENT_NATURE_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.payment.nature.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for payment nature wasn't found.";
        }
    },
    BOND_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.bond.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for bond wasn't found.";
        }
    },
    BASE_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.base.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for Base wasn't found.";
        }
    },
    DIFFERENT_VALUE_COST_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.different.value.cost.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for DifferentValueCost wasn't found.";
        }
    },
    EXTRAORDINARY_EXPENSE_COST_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.extraordinary.expense.cost.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for ExtraordinaryExpenseCost wasn't found.";
        }
    },
    INVALID_START_DATE {
        @Override
        public String getValue() {
            return "error.start.date.invalid";
        }

        @Override
        public String getMessage() {
            return "The provided StartDate is contained between an existing interval.";
        }
    },
    INVALID_END_DATE {
        @Override
        public String getValue() {
            return "error.end.date.invalid";
        }

        @Override
        public String getMessage() {
            return "The provided EndDate is contained between an existing interval.";
        }
    },
    CONTRACT_IS_INACTIVE {
        @Override
        public String getValue() {
            return "error.contract.is.inactive";
        }

        @Override
        public String getMessage() {
            return "The provided Contract is inactive.";
        }
    },
    DUPLICATE_NAME {
        @Override
        public String getValue() {
            return "error.duplicate.name";
        }

        @Override
        public String getMessage() {
            return "The provided name has existing";
        }
    },
    COMPANY_DATA_IS_INACTIVE {
        @Override
        public String getValue() {
            return "error.company.data.is.inactive";
        }

        @Override
        public String getMessage() {
            return "The provided CompanyData is inactive.";
        }
    },
    PAYMENT_NATURE_IS_INACTIVE {
        @Override
        public String getValue() {
            return "error.payment.nature.is.inactive";
        }

        @Override
        public String getMessage() {
            return "The provided PaymentNature is inactive.";
        }
    },
    BOND_IS_INACTIVE {
        @Override
        public String getValue() {
            return "error.bond.is.inactive";
        }

        @Override
        public String getMessage() {
            return "The provided Bond is inactive.";
        }
    },
    BASE_IS_INACTIVE {
        @Override
        public String getValue() {
            return "error.base.is.inactive";
        }

        @Override
        public String getMessage() {
            return "The provided Base is inactive.";
        }
    },
    EMPTY_DESCRIPTION {
        @Override
        public String getValue() {
            return "error.description.is.empty";
        }

        @Override
        public String getMessage() {
            return "The provided description for service type was empty.";
        }
    },
    ON_CALL_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.on.call.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided On Call ID is invalid for this resource or not found";
        }
    },

    ON_CALL_DOCTOR_IMAGE_HAS_MULTIPLE_FACES {
        @Override
        public String getValue() {
            return "error.on_call_doctor_image_has_multiple_faces";
        }

        @Override
        public String getMessage() {
            return "The image has multiple persons. Please send an image with only one person";
        }
    },
    ON_CALL_DOCTOR_IMAGE_DOESNT_MATCH_DOCTOR_IMAGE {
        @Override
        public String getValue() {
            return "error.on_call_doctor_image_doesnt_match_doctor_image";
        }

        @Override
        public String getMessage() {
            return "The image not match with default doctor profile image";
        }
    },
    DOCTOR_PROFILE_IMAGE_UNAVAILABLE {
        @Override
        public String getValue() {
            return "error.doctor_profile_image_unavailable";
        }

        @Override
        public String getMessage() {
            return "The doctor doet not have a default profile image";
        }
    },
    FILE_UNAVAILABLE {
        @Override
        public String getValue() {
            return "error.file_unavailable";
        }

        @Override
        public String getMessage() {
            return "FILE_UNAVAILABLE";
        }
    },
    BORE_COST_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.bore.cost.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Bore Cost ID is invalid for this resource or not found";
        }
    },
    ACCESS_CONTROL_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.access.control.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Access Control ID is invalid for this resource or not found";
        }
    },
    STATUS_MUST_BE_OK {
        @Override
        public String getValue() {
            return "error.status.must.be.ok";
        }

        @Override
        public String getMessage() {
            return "The provided Status must be OK";
        }
    },
    STATUS_MUST_BE_PROGRAMMED {
        @Override
        public String getValue() {
            return "error.status.must.be.programmed";
        }

        @Override
        public String getMessage() {
            return "The provided Status must be PROGRAMMED";
        }
    },
    HIRED_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.hired.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Request ID is invalid for this resource or not found";
        }
    },
    REQUEST_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.request.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for Hired wasn't found.";
        }
    },
    CONTRACTING_PARTY_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.contracting.party.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Id for Contracting Party wasn't found.";
        }
    },
    HIRED_ALREADY_EXISTS {
        @Override
        public String getValue() {
            return "error.hired.already.exists";
        }

        @Override
        public String getMessage() {
            return "The provided name for Hired already exists.";
        }
    },
    CONTRACTING_PARTY_ALREADY_EXISTS {
        @Override
        public String getValue() {
            return "error.contracting.party.already.exists";
        }

        @Override
        public String getMessage() {
            return "The provided Id for Contracting Party already exists.";
        }
    },
    MESSAGE_NOT_FOUND {
        @Override
        public String getValue() {
            return "error.message.not.found";
        }

        @Override
        public String getMessage() {
            return "The provided Message ID is invalid for this resource or not found";
        }
    },
    MESSAGE_IS_INACTIVE {
        @Override
        public String getValue() {
            return "error.message.is.inactive";
        }

        @Override
        public String getMessage() {
            return "The provided Message is inactive.";
        }
    },
    OCCURRENCE_ALREADY_ATTACHED_TO_GROUP {
        @Override
        public String getValue() {
            return "error.occurrence_already_attached_to_group";
        }

        @Override
        public String getMessage() {
            return "OCCURRENCE_ALREADY_ATTACHED_TO_GROUP";
        }
    },
    OCCURRENCE_ALREADY_ATTACHED_TO_CLASSIFICATION {
        @Override
        public String getValue() {
            return "error.occurrence_already_attached_to_classification";
        }

        @Override
        public String getMessage() {
            return "OCCURRENCE_ALREADY_ATTACHED_TO_CLASSIFICATION";
        }
    },
    NULL_STATUS {
        @Override
        public String getValue() {
            return "error.status.is.null";
        }

        @Override
        public String getMessage() {
            return "The provided Status cannot be null.";
        }
    },
    LAT_LNG_MUST_NOT_BE_NULL {
        @Override
        public String getValue() {
            return "error.lat.and.lng.must.not.be.null";
        }

        @Override
        public String getMessage() {
            return "The provided Lat and Lng cannot be null.";
        }
    },
    ENTITY_DOESNT_HAS_REVISION {
        @Override
        public String getValue() {
            return "entity.doesnt.has.revision";
        }

        @Override
        public String getMessage() {
            return "The provided entity doesn't has revision";
        }
    },
    INVALID_PASSCODE {
        @Override
        public String getValue() {
            return "error.occurrence.invalid_passcode";
        }

        @Override
        public String getMessage() {
            return "Invalid passcode";
        }
    };

    public abstract String getValue();

    public abstract String getMessage();
}
