package com.MarkRight.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermissions {
    USER_BAN("user:ban"),
    USER_UNBAN("user:unban");
    private final String permission;
}
