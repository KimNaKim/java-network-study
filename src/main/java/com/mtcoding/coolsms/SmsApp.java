package com.mtcoding.coolsms;

import com.solapi.sdk.SolapiClient;
import com.solapi.sdk.message.exception.SolapiMessageNotReceivedException;
import com.solapi.sdk.message.model.Message;
import com.solapi.sdk.message.service.DefaultMessageService;

public class SmsApp {
    public static void main(String[] args) {
        //대문자는 final 변수, 객체 생성 없이 호출이 가능하면 static
        DefaultMessageService messageService =  SolapiClient.INSTANCE
                .createInstance("NCSLFS0AI1FSE14H", "W7E4VSSZUBKDLBYBQRVYNYE4IVIFJHK8");
        // Message 패키지가 중복될 경우 com.solapi.sdk.message.model.Message로 치환하여 주세요
        Message message = new Message();
        message.setFrom("01082546657");
        message.setTo("01082546657");
        message.setText("확인용문자1");

        try {
            // send 메소드로 ArrayList<Message> 객체를 넣어도 동작합니다!
            //쓰기 버퍼에 메세지 문장을 담아 API 서버에 전송
            messageService.send(message);
        } catch (SolapiMessageNotReceivedException exception) {
            // 발송에 실패한 메시지 목록을 확인할 수 있습니다!
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
