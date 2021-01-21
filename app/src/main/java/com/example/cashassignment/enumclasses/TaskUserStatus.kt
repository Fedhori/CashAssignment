package com.example.cashassignment.enumclasses

enum class TaskUserStatus {
    MISSION_FINISHED, // 마감
    ALL_SUBMITTED,    // 제출 완료
    MISSION_CLOSED,   // 미션 닫힘
    STOPPED_BY_ADMIN, // 점검 중
    STOPPED_BY_NO_JOB,// 잠시 중단
    VALIDATING,       // 검사 중
    USER_OPEN         // 진행 중
}