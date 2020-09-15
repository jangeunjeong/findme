# -*- coding: utf8 -*-

import pymysql
import pandas as pd
import numpy as np
import math
import sys

def sim_pearson(data, id1, id2): # 피어슨 유사도
    avg_id1 = 0
    avg_id2 = 0
    count = 0
    for tech in data[id1]:
        if tech in data[id2]: # 같은 기술스택을 가지고있다면
            avg_id1 = data[id1][tech]
            avg_id2 = data[id2][tech]
            count += 1
    
    avg_id1 = avg_id1 / count
    avg_id2 = avg_id2 / count
    
    sum_id1 = 0
    sum_id2 = 0
    sum_id1_id2 = 0
    count = 0
    for tech in data[id1]:
        if tech in data[id2]:
            sum_id1 += pow(data[id1][tech] - avg_id1, 2)
            sum_id2 += pow(data[id2][tech] - avg_id2, 2)
            sum_id1_id2 += (data[id1][tech] - avg_id1) * (data[id2][tech] - avg_id2)
    try:
        return sum_id1_id2 / (math.sqrt(sum_id1)*math.sqrt(sum_id2))
    except:
        pass


def top_match(data, id, index=3, sim_function=sim_pearson):
    li=[]
    for i in data: #딕셔너리를 돌고
        if id!=i: #자기 자신이 아닐때만
            li.append((sim_function(data,id,i),i)) #sim_function()을 통해 상관계수를 구하고 li[]에 추가
    li.sort() #오름차순
    li.reverse() #내림차순
    return li[:index]


def main(register_id):
    print(register_id)
    register_id = int(register_id)
    language_columns = (
        'id',
        'Java',
        'C',
        'Python',
        'C++',
        'C#',
        'VB.NET',
        'JavaScript',
        'PHP',
        'SQL',
        'Go',
        'R',
        'Assembly',
        'Swift',
        'Ruby',
        'MATLAB',
        'PL/SQL',
        'Perl',
        'Visual Basic',
        'Objective-C',
        'Delphi'
    )

    conn = pymysql.connect(host='localhost', user='ssafy', password='ssafy', db='findme', charset='utf8')

    # Connection 으로부터 Cursor 생성
    curs = conn.cursor()

    # SQL문 실행
    sql = "select * from user"
    curs.execute(sql)

    # 데이타 Fatch
    rows = curs.fetchall()
    # rows = users
    # print(rows) # 전체 rows
    # print(rows[0]) # 첫번째 row
    
    rows_data = pd.DataFrame(rows)
    user_id = []
    data = []
    for u in rows_data[0]:
        user_id.append(u)

    teck_stack = []
    for t in rows_data[7]:
        tmp = t.split(",")
        teck_stack.append(tmp)

    for i in range(len(user_id)):
        tmp = []
        tmp.append(user_id[i])

        for lang in language_columns:
            if lang == "id":
                continue

            flag = False
            for tech in teck_stack[i]:
                if lang == tech:
                    flag = True
                    break
                else:
                    flag = False

            if flag:
                tmp.append(1)
            else:
                tmp.append(0)

        data.append(tmp)


    data_frame = pd.DataFrame(data=data, columns=language_columns)
    data_dict = data_frame.set_index('id').T.to_dict('dict')
    top = top_match(data_dict, register_id, len(data_dict), sim_function=sim_pearson)
    result = top[0]
    conn.commit()
    print(result[0])
    if result[0] >= 0.8:
        sql = "insert into similar(child, parent) values(%s, %s)"
        val = (str(register_id), str(result[1]))
        curs.execute(sql, val)
        conn.commit()
        print("match")
    else:
        print("dismatch")

    # connection 닫기
    conn.close()

if __name__ == "__main__":
    main(sys.argv[1])