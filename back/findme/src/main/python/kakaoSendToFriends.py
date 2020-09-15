import pymysql
import pandas as pd
import requests
import json
import sys

def main(access_token, recruit_id, receiver_uuids, pick_cnt):
    print(receiver_uuids)
    uuids=receiver_uuids.split('*')
    
    tmp=""
    for i in uuids:
        tmp+='"'+i+'",'
    tmp=tmp[:len(tmp)-1]
    
    conn = pymysql.connect(host='localhost', user='ssafy', password='ssafy', db='findme', charset='utf8')
    curs = conn.cursor()
    # sql = "select * from recruits where recruit_id = (select recruit_id from recommend where user_id = "+user_id+" ) "
    sql = "select * from recruit where id = " + recruit_id

    curs.execute(sql)
    rows = curs.fetchall()
    rows_data = pd.DataFrame(rows)
    
    like_cnt=pick_cnt
    title = rows_data[8][0]
    image_url=rows_data[3][0]
    if(image_url == ''):
        image_url="https://user-images.githubusercontent.com/52478972/80742610-a03caa00-8b56-11ea-8cd7-4cbcffd061b2.jpg"
    description = rows_data[7][0]
    recruit_url=rows_data[9][0]

    print(recruit_url)
    
    print(title)
    print(description)
    # image_url="http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg"

    template_dict_data = str({
        "object_type": "feed",
        "content": {
            "title": title,
            "description": description,
            "image_url": image_url,
            "image_width": '30',
            "image_height": '30',
            "link": {
                "web_url": recruit_url,
                "mobile_web_url": recruit_url,
                "android_execution_params": recruit_url,
                "ios_execution_params": recruit_url
            }
        },
        "social": {
            "like_count": like_cnt
        },
        "buttons": [
            {
                "title": "보러가기",
                "link": {
                    "web_url": recruit_url,
                    "mobile_web_url": recruit_url
                }
            }
        ]
    })

    kakao_to_me_uri = 'https://kapi.kakao.com//v1/api/talk/friends/message/default/send'


    headers = {
        'Content-Type': "application/x-www-form-urlencoded",
        'Authorization': "Bearer " + access_token,
    }

    template_object = str(json.dumps(template_dict_data))
    template_object = template_object.replace("\"", "")
    template_object = template_object.replace("'", "\"")

    params = {}
    params['receiver_uuids'] = '['+tmp+']'
    params['template_object'] = template_object

    response = requests.request(method="POST", url=kakao_to_me_uri, params=params, headers=headers)
    print(response.json())

if __name__ == "__main__":
    main(sys.argv[1] , sys.argv[2] , sys.argv[3], sys.argv[4])