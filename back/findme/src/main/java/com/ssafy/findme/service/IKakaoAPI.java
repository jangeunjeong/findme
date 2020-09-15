package com.ssafy.findme.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.findme.dto.FriendDTO;

public interface IKakaoAPI {

	String getAccessToken(String authorize_code);

	HashMap<String, Object> getUserInfo(String access_Token);

	void kakaoLogout(String access_Token);

	void profile(String access_Token);

	List<FriendDTO> friends(String access_Token);

	void sendToMe(String access_Token, Long recruit_id);

	void sendToFriends(String access_Token, String recruit_id, List<String> list);

	void sendMessagejorok(String access_Token);

	void secession(String access_Token);
}
