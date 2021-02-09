package com.example.cashassignment.enumclasses

/*
* 이 enum class의 나열 순서, 갯수는 반드시 fragment_all_mission.xml의 tabLayout의 item들의 나열 순서, 갯수와 일치해야 한다.
* tabLayout에서 특정 tab을 누를 경우 position 값을 받아와 이를 enum으로 변환하여 API의 parameter로 넘기는 형식이기 때문.
*/
enum class TaskCategory {
    ALL, BOX, PICTURE, SURVEY, VALID
}