package edu.kh.project.sse.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.sse.dto.Notification;
import edu.kh.project.sse.mapper.SseMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SseServiceImpl implements SseService{

	private final SseMapper mapper;
	
	// 알림 삽입 후 알림 받을 회원 번호 + 알림 개수 반환
	@Override
	public Map<String, Object> insertNotification(Notification notification) {

		// 매개 변수 notification에 저장된 값
		// -> type, url, content, pkNo, sendMemberNo
		
		// 결과 저장용 map 
		Map<String, Object> map = null;
		
		// 알림 삽입
		int result = mapper.insertNotification(notification);
		
		if(result > 0) { // 알림 삽입 성공 시
			// 알림을 받아야하는 회원의 번호 + 안읽은 알람 개수 조회
			map = mapper.selectReceiveMember(notification.getNotificationNo());
			
		}
		
		
		return map;
	}

	public List<Notification> selectNotificationList(int memberNo) {
		return mapper.selectNotificationList(memberNo);
	}

	@Override
	public int notReadCheck(int memberNo) {
		return mapper.notReadCheck(memberNo);
	}

	@Override
	public void deleteNotification(int notificationNo) {
		mapper.deleteNotification(notificationNo);
	}
	
	@Override
	public void updateNotification(int notificationNo) {
		mapper.updateNotification(notificationNo);
	}
}
