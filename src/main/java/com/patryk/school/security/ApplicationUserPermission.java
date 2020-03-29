package com.patryk.school.security;

public enum ApplicationUserPermission {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    LESSON_READ("lesson:read"),
    LESSON_WRITE("lesson:write"),
    MARK_READ("lesson:read"),
    MARK_WRITE("lesson:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
