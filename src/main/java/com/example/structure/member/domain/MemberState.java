package com.example.structure.member.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum MemberState {
    ACTIVE,
    DELETED,
    DORMANT
}
