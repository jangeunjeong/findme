package com.ssafy.findme.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.findme.dto.FriendDTO;
import com.ssafy.findme.repository.PickRepository;

@Service
public class KakaoAPI implements IKakaoAPI {

	@Autowired
	private PickRepository pickRepo;

	@Override
	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=df3683c5354024c47b509ecad955f714");
			sb.append("&redirect_uri=http://localhost:8081");
//			sb.append("&redirect_uri=https://i02b204.p.ssafy.io");
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			System.out.println("laskdjfl;askjdf;klasdjfkl;");
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) {
		System.out.println("KaKaoAPIServiceImpl getUserInfo");
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			int id = element.getAsJsonObject().get("id").getAsInt();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("email", id);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return userInfo;
	}

	@Override
	public void kakaoLogout(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void profile(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/api/talk/profile";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	// 친구목록받기
	public List<FriendDTO> friends(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/api/talk/friends";
		List<FriendDTO> list = new ArrayList<FriendDTO>();
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonArray elements = element.getAsJsonObject().get("elements").getAsJsonArray();

			JsonObject tmp = null;
			for (int i = 0; i < elements.size(); i++) {
				tmp = (JsonObject) elements.get(i);

				String profile_nickname_tmp = tmp.get("profile_nickname").toString();
				String profile_thumbnail_image_tmp = tmp.get("profile_thumbnail_image").toString();
				String uuid_tmp = tmp.get("uuid").toString();
				String favorite_tmp = tmp.get("favorite").toString();

				String profile_nickname = profile_nickname_tmp.substring(1, profile_nickname_tmp.length() - 1);
				String profile_thumbnail_image = profile_thumbnail_image_tmp.substring(1,
						profile_thumbnail_image_tmp.length() - 1);
				int id = Integer.parseInt(tmp.get("id").toString());
				String uuid = uuid_tmp.substring(1, uuid_tmp.length() - 1);
				boolean favorite = false;
				if (favorite_tmp.equals("true"))
					favorite = true;

				list.add(new FriendDTO(profile_nickname, profile_thumbnail_image, id, favorite, uuid));
				System.out.println(list.get(i).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void sendToMe(String access_Token, Long recruit_id) {
//		CommandLineExecutor.execute("python src/main/python/kakaoSendToMe.py " + access_Token + " " + recruit_id);
		CommandLineExecutor
				.execute("/usr/bin/python3 /home/ubuntu/python/kakaoSendToMe.py " + access_Token + " " + recruit_id);
	}

	@Override
	public void sendToFriends(String access_Token, String recruit_id, List<String> list) {
		int num = list.size();
		String tmp = "";
		switch (num) {
		case 5:
			tmp += list.get(4) + "*";
		case 4:
			tmp += list.get(3) + "*";
		case 3:
			tmp += list.get(2) + "*";
		case 2:
			tmp += list.get(1) + "*";
		case 1:
			tmp += list.get(0);
		default:
			break;

		}
		System.out.println("recruit_id: " + recruit_id);
		System.out.println("access_Token: " + access_Token);
		System.out.println("friends_token: " + tmp);

		String pythonPath = "";
		String filePath = "";
		String str = "";

		if (System.getProperty("os.name").indexOf("Windows") > -1) {
			str = "python src/main/python/kakaoSendToFriends.py";
		} else {
			str = "/usr/bin/python3 /home/ubuntu/python/kakaoSendToFriends.py";
		}
//		CommandLineExecutor
//				.execute("python src/main/python/kakaoSendToFriends.py " + access_Token + " " + recruit_id + " " + tmp);
		int pick_cnt = (int) (long) pickRepo.countByRecruitId(Long.parseLong(recruit_id));
		System.out.println(pick_cnt);
		CommandLineExecutor.execute(str + " " + access_Token + " " + recruit_id + " " + tmp + " " + pick_cnt);
	}

	@Override
	public void sendMessagejorok(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			conn.setRequestMethod("POST");

			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			// 임시값
			String header_title = "WEEKELY MAGAZINE";
			String web_url = "http://www.daum.net";
			String mobile_web_url = "http://m.daum.net";
			String android_execution_params = "main";
			String ios_execution_params = "main";

			JSONObject obj = new JSONObject();
			try {
				obj.put("object_type", "list");
				obj.put("header_title", header_title);

				JSONObject hLinkObj = new JSONObject();
				hLinkObj.put("web_url", web_url);
				hLinkObj.put("mobile_web_url", mobile_web_url);
				hLinkObj.put("android_execution_params", android_execution_params);
				hLinkObj.put("ios_execution_params", ios_execution_params);
				obj.put("header_link", hLinkObj);

				LinkedList<JSONObject> ct = new LinkedList<JSONObject>();
				JSONObject contentObj = new JSONObject();
				contentObj.put("title", "자전거 라이더를 위한 공간");
				contentObj.put("description", "매거진");
				contentObj.put("image_url",
						"http://mud-kage.kakao.co.kr/dn/QNvGY/btqfD0SKT9m/k4KUlb1m0dKPHxGV8WbIK1/openlink_640x640s.jpg");
				contentObj.put("image_width", 640);
				contentObj.put("image_height", 640);

				JSONObject linkObj = new JSONObject();
				linkObj.put("web_url", web_url);
				linkObj.put("mobile_web_url", mobile_web_url);
				linkObj.put("android_execution_params", android_execution_params);
				linkObj.put("ios_execution_params", ios_execution_params);
				contentObj.put("link", linkObj);
				ct.add(contentObj);

				obj.put("contents", ct);

				LinkedList<JSONObject> btn = new LinkedList<JSONObject>();
				JSONObject buttonObj = new JSONObject();
				buttonObj.put("title", "웹으로 이동");
				JSONObject blinkObj = new JSONObject();
				blinkObj.put("web_url", "http://www.daum.net");
				blinkObj.put("mobile_web_url", "http://www.daum.net");
				buttonObj.put("link", blinkObj);
				btn.add(buttonObj);

				obj.put("buttons", btn);

			} catch (JSONException jex) {
				System.out.println("json error occured!");
			}

			DataOutputStream output = new DataOutputStream(conn.getOutputStream());
			System.out.println("template_object=" + obj.toString());
			output.writeUTF("template_object=" + obj.toString());
			output.flush();
			output.close();

			int responseCode = conn.getResponseCode();
			String responseReason = conn.getResponseMessage();
			System.out.println("responseCode : " + responseCode);
			System.out.println("responseReason : " + responseReason);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void secession(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/unlink";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}