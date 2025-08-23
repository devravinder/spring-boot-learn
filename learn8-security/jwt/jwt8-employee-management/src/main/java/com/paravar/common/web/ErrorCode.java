package com.paravar.common.web;

public interface ErrorCode {
    String REST = "rest.api";

    String SERVER_ERROR = "server.error";

    String EMPLOYEE_NOT_FOUND = "employee.notFound";


    String VALIDATION = "validation.error";
    String SIZE_LIMIT_EXCEEDED = "validation.sizeLimitExceeded";
    String FILE_NOT_SUPPORTED = "validation.fileNotSupported";
}
