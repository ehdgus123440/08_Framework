/*  SSE(Server-Sent Events) 
  서버(요청) -> 클라이언트(응답)
  
  - 서버가 클라이언트에게 실시간으로
    데이터를 전송할 수 있는 기술

  - HTTP 프로토콜 기반으로 동작

  - 단방향 통신 (ex : 무전기)

  1) 클라이언트가 서버에 연결 요청
    -> 클라이언트가 서버로 부터 데이터 받기 위한 대기상태 돌입
      (EvnetSource 객체 이용)
      
  2) 서버가 연결된 클라이언트에게 데이터를 전달
    (서버 -> 클라이언트 데이터 전달하라는 
    요청을 AJAX를 이용해 비동기 요청)
*/

/* SSE 연결하는 함수 
  -> 연결을 요청한 클라이언트가 
    서버로부터 데이터가 전달될 때 까지 대기 상태가됨
    (비동기)
*/
const connectSse = () => {

  /* 로그인이 되어있지 않은 경우 함수 종료 */
  if (notificationLoginCheck === false) return;

  console.log("connectSse() 호출");

  // 서버의 "/sse/connect" 주소로 연결 요청
  const eventSource = new EventSource("/sse/connect");

  // --------------------------------------

  /* 서버로 부터 메시지가 왔을 경우 */
  eventSource.addEventListener("message", e => {
    console.log(e.data); // e.data == 전달 받은 메시지
    // -> Spring HTTPMessageConverter가
    //    JSON 을 변환해서 응답해줌

    const obj = JSON.parse(e.data);
    console.log(obj);

    // 종 버튼 색 추가(활성화)
    const notificationBtn = document.querySelector(".notification-btn");

    notificationBtn.classList.add("fa-solid");
    notificationBtn.classList.remove("fa-regular");


    // 알림 개수 표시
    const notificationCountArea = document.querySelector(".notification-count-area");
    notificationCountArea.innerText = obj.notiCount;


  });

  /* 서버 연결이 종료된 경우(타임아웃 초과) */
  eventSource.addEventListener("error", () => {
    console.log("SSE 재연결 시도");

    eventSource.close(); // 기존 연결 닫기

    setTimeout(() => {
      connectSse();
    }, 5000);
  })


  // eventSource.addEventListener("message", e => {
  //   console.log(e.data); // e.data : 전달 받은 메시지
  // });

};



/** 알림 메시지 전송 함수 
  - 알림을 받을 특정 클라이언트의 id 필요 (memberNo 또는 memberNo를 알아낼 수 있는 값)

  - [동작 원리]
  1) AJAX를 이용해 SseController에 요청

  2) 연결된 클라이언트 대기 명단(emitters)에서 
    클라이언트 id 가 일치하는 회원을 찾아 
    메시지 전달하는 send() 메서드를 수행

  3) 서버로부터 메시지를 전달 받은 클라이언트의 
    eventSource.addEventListener()가 수행됨
*/
const sendNotification = (type, url, pkNo, content) => {

  // type : 댓글, 답글, 좋아요 등을 구분하는 값
  // url : 알림 클릭 시 이동할 페이지 주소
  // pkNo : 알림 받는 회원 번호 또는 회원 번호를 찾을 수 있는 pk 값
  // content : 알림 내용

  /* 로그인이 되어있지 않은 경우 함수 종료 */
  if (notificationLoginCheck === false) return;

  /* 서버로 제출할 데이터를 JS 객체 형태로 저장 */
  const notification = {
    "notificationType": type,
    "notificationUrl": url,
    "pkNo": pkNo,
    "notificationContent": content
  }

  fetch("/sse/send", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(notification)
  })
    .then(response => {
      if (!response.ok) { // 비동기 통신 실패
        throw new Error("알림 전송 실패");
      }
      console.log("알림 전송 성공");
    })
    .catch(err => console.error(err));
};




/** 비동기로 알림 목록을 조회하는 함수 */
const selectNotificationList = () => {

  // 로그인 안된 경우
  if (notificationLoginCheck === false) {
    return;
  }

  fetch("/notification") // get방식
    .then(response => {
      if (response.ok) return response.json();
      throw new Error("알림 목록 조회 실패");
    })
    .then(selectList => {
      console.log(selectList);

      // 이전 알림 목록 삭제
      const notiList = document.querySelector(".notification-list");
      notiList.innerHTML = '';

      for (let data of selectList) {

        // 알림 전체를 감싸는 요소
        const notiItem = document.createElement("li");
        notiItem.className = 'notification-item';


        // 알림을 읽지 않은 경우 'not-read' 추가
        if (data.notificationCheck == 'N') notiItem.classList.add("not-read");


        // 알림 관련 내용(프로필 이미지 + 시간 + 내용)
        const notiText = document.createElement("div");
        notiText.className = 'notification-text';


        // 알림 클릭 시 동작
        notiText.addEventListener("click", e => {

          // 만약 읽지 않은 알람인 경우
          if (data.notificationCheck == 'N') {
            fetch("/notification", {
              method: "PUT",
              headers: { "Content-Type": "application/json" },
              body: data.notificationNo
            })
            // 컨트롤러 메서드 반환값이 없으므로 then 작성 X
          }

          // 클릭 시 알림에 기록된 경로로 이동
          location.href = data.notificationUrl;
        })


        // 알림 보낸 회원 프로필 이미지
        const senderProfile = document.createElement("img");
        if (data.sendMemberProfileImg == null) senderProfile.src = notificationDefaultImage;  // 기본 이미지
        else senderProfile.src = data.sendMemberProfileImg; // 프로필 이미지


        // 알림 내용 영역
        const contentContainer = document.createElement("div");
        contentContainer.className = 'notification-content-container';

        // 알림 보내진 시간
        const notiDate = document.createElement("p");
        notiDate.className = 'notification-date';
        notiDate.innerText = data.notificationDate;

        // 알림 내용
        const notiContent = document.createElement("p");
        notiContent.className = 'notification-content';
        notiContent.innerHTML = data.notificationContent; // 태그가 해석 될 수 있도록 innerHTML

        // 삭제 버튼
        const notiDelete = document.createElement("span");
        notiDelete.className = 'notidication-delete';
        notiDelete.innerHTML = '&times;';


        /* 삭제 버튼 클릭 시 비동기로 해당 알림 지움 */
        notiDelete.addEventListener("click", e => {

          fetch("/notification", {
            method: "DELETE",
            headers: { "Content-Type": "application/json" },
            body: data.notificationNo
          })
            .then(resp => {
              if (resp.ok) return resp.text();
              throw new Error("네트워크 응답이 좋지 않습니다.");
            })
            .then(result => {
              // 클릭된 x버튼이 포함된 알림 삭제
              notiDelete.parentElement.remove();
              notReadCheck();

            })
        })

        // 조립
        notiList.append(notiItem);
        notiItem.append(notiText, notiDelete);
        notiText.append(senderProfile, contentContainer);
        contentContainer.append(notiDate, notiContent);

      }







    })
    .catch(err => console.log(err));
}





// 페이지 로딩 완료 후 수행
document.addEventListener("DOMContentLoaded", () => {
  connectSse();

  // 종 버튼(알림) 클릭 시 알림 목록이 출력하기
  const notificationBtn = document.querySelector(".notification-btn");

  notificationBtn?.addEventListener("click", () => {
    const notificationList = document.querySelector(".notification-list");

    // 알림 목록이 보이고 있을 경우
    if(notificationList.classList.contains("notification-show")){
      notificationList.classList.remove("notification-show")
    }
    else{
      selectNotificationList(); // 비동기로 목록 조회
      notificationList.classList.add("notification-show");
    }
  })

})





















