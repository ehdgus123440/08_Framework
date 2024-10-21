package edu.kh.project.sse.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.sse.dto.Notification;

public interface SseService {

	/**
	 * 알림 삽입 후 알림 받을 회원 번호 + 알림 개수 반환
	 * @param notification
	 * @return
	 */
	Map<String, Object> insertNotification(Notification notification);

	/**
	 * 로그인한 
	 * @param memberNo
	 * @return
	 */
	List<Notification> selectNotificationList(int memberNo);

	/**
	 * 안읽은 알람 개수 
	 * @param memberNo
	 * @return count
	 */
	int notReadCheck(int memberNo);

	/**
	 * 알림 삭제
	 * @param notificationNo
	 */
	void deleteNotification(int notificationNo);

	/**
	 * 알림 읽음 여부 변경
	 * @param notificationNo
	 */
	void updateNotification(int notificationNo);

}
