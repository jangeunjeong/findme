import pymysql
import pandas as pd
import math
import sys
# pd.set_option('display.max_rows', 500)
# pd.set_option('display.max_columns', 500)
# pd.set_option('display.width', 1000)

# 피어슨 유사도
def sim_pearson(data, id1, id2):
    avg_id1 = 0
    avg_id2 = 0
    count = 0

    for tech in data[id1]:
        if tech in data[id2]:
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

    return sum_id1_id2 / (math.sqrt(sum_id1) * math.sqrt(sum_id2))

# 가장 유사도 높은 거 뽑기
def top_match(data, id, index=3, sim_function=sim_pearson):
    li = []

    for i in data:  # 딕셔너리를 돌고
        if id != i:  # 자기 자신이 아닐때만
            li.append((sim_function(data, id, i), i))  # sim_function()을 통해 상관계수를 구하고 li[]에 추가
    li.sort()  # 오름차순
    li.reverse()  # 내림차순
    return li[:index]

def makeMatchRecruit(userId, index, recruitInfo, job_columns, tech_columns):
    data = []

    for i in range(0, len(recruitInfo)):
        tmp = [int(recruitInfo['id'][i])]
        data.append(tmp)
        jobList = recruitInfo['job_category'][i].split(',')
        techList = recruitInfo['tech_stack'][i].split(',')

        for j in range(1, len(job_columns)):
            flag = False

            for k in range(0, len(jobList)):
                if jobList[k].find(job_columns[j]) != -1:
                    flag = True
                    break
            if flag:
                data[i].append(1)
            else:
                data[i].append(0)

        for j in range(1, len(tech_columns)):
            flag = False
            str = tech_columns[j]

            if tech_columns[j] == 'JavaScript':
                str = '자바스크립트'

            for k in range(0, len(techList)):
                if techList[k].find(str) != -1:
                    flag = True
                    break
            if flag:
                data[i].append(1)
            else:
                data[i].append(0)

    # for i in range(0, len(data)):
    #     if data[i][0] == userId:
    #         print(data[i])
    #     if data[i][0] == 38049812:
    #         print(data[i])

    dataFrame = pd.DataFrame(data=data)
    dataDict = dataFrame.set_index(0).T.to_dict('dict')

    top = top_match(dataDict, userId, len(dataDict), sim_function=sim_pearson)
    top = top[:index]
    matchRecruit = []

    for i in top:
        matchRecruit.append(i[1])

    return matchRecruit

def start(userId):
    userId = int(userId)

    # 파이썬-MySQL 연결
    # MySQL 설정
    HOST = '127.0.0.1'
    PORT = 3306
    USER = 'ssafy'
    PASSWORD = 'ssafy'
    DB = 'findme'
    CHARSET = 'utf8'

    # MySQL Connection 연결
    conn = pymysql.connect(host=HOST, port=PORT, user=USER, password=PASSWORD, db=DB, charset=CHARSET)

    # Connection 으로부터 Cursor 생성
    curs = conn.cursor()

    # 유저 데이터 수집
    sql = "select id, wish_job, tech_stack from user where id = %s"
    val = userId

    # SQL 문 실행
    curs.execute(sql, val)

    # 데이터 Fetch
    userRows = curs.fetchall()
    # 데이터 프레임 생성
    myInfo = pd.DataFrame(list(userRows))
    myInfo.columns = ['id', 'job_category', 'tech_stack']

    # 공고 데이터 수집
    # sql = "select id, comp_name, job_category, tech_stack from recruit where tech_stack REGEXP 'java|c|python|visual|basic|.NET|php|sql|go|r|assembly|swift|ruby|delphi' and tech_stack REGEXP %s and job_category REGEXP %s"
    sql = "select id, comp_name, job_category, tech_stack from recruit where id not in (select id from recruit where (title like '%경력%' and title not like '%신입%') or (title like '%sr.%' and title not like '%jr.%')) and id not in (select id from recruit where (title like '%과정%') or (title like '%교육%') or (title like '%과정%') or (title like '%대리%') or (title like '%과장%'))"

    # 내 기술 스택 문자열
    myTechStackStr = ""
    myTechStackList = myInfo['tech_stack'][0].split(',')

    addedSQL = ""

    for i in range(0, len(myTechStackList)):
        tmpStr = myTechStackList[i].strip()

        if tmpStr == 'JavaScript':
            tmpStr = "자바스크립트"
        if i == 0:
            sql += " and (tech_stack like '%" + tmpStr + "%'"
        elif i != len(myTechStackList) - 1:
            sql += " or tech_stack like '%" + tmpStr + "%'"
            myTechStackStr += myTechStackList[i].strip() + "|"
        else:
            sql += " or tech_stack like '%" + tmpStr + "%'"
            myTechStackStr += myTechStackList[i].strip()
    sql += ")"


    # 내 희망 업무 문자열
    myJobCateStr = ""
    myJobCateList = myInfo['job_category'][0].split(',')

    for i in range(0, len(myJobCateList)):
        if i == 0:
            sql += " and (job_category like '%" + myJobCateList[i].strip() + "%'"
        elif i != len(myJobCateList) - 1:
            sql += " or job_category like '%" + myJobCateList[i].strip() + "%'"
            myJobCateStr += myJobCateList[i] + "|"
        else:
            sql += " or job_category like '%" + myJobCateList[i].strip() + "%'"
            myJobCateStr += myJobCateList[i]
            
    sql += ")"

    # val = (myTechStackStr, myJobCateStr)
    # val = (myTechStackStr)
    # print(val)
    #  and `number` not in (select `number` from tmp_findme.recruit where (title like '%경력%' and title not like '%신입%') or (title like '%sr.%' and title not like '%jr.%')) and job_category REGEXP '웹개발'
    # curs.execute(sql, val)
    # sql += addedSQL
    # print(sql)
    curs.execute(sql)

    # 데이터 Fetch
    recruitRows = curs.fetchall()

    # print(recruitRows)

    # 데이터 프레임 생성
    recruitInfo = pd.DataFrame(list(recruitRows))
    recruitInfo.columns = ['id', 'comp_name', 'job_category', 'tech_stack']

    # 데이터 전처리
    # ·를 ,로 변경
    recruitInfo['job_category'] = recruitInfo['job_category'].str.replace(pat='·', repl=',')
    recruitInfo['tech_stack'] = recruitInfo['tech_stack'].str.replace(pat='·', repl=',')
    recruitInfo.drop('comp_name', axis=1, inplace=True)

    # 전체 데이터에 내 데이터 추가
    recruitInfo.loc[len(recruitInfo)] = [myInfo['id'][0], myInfo['job_category'][0], myInfo['tech_stack'][0]]

    # 기술 스택 목록
    tech_columns = (
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

    # 희망 업무 목록
    job_columns = (
        'id',
        '웹',
        '응용프로그램',
        'QA',
        '테스트',
        '인공지능',
        '빅데이터',
        '블록체인',
        '보안',
        '데이터베이스',
        '네트워크',
        'PM',
        'ERP',
        '분석',
        '설계'
    )
    print(makeMatchRecruit(userId, 60, recruitInfo, job_columns, tech_columns))

if __name__=='__main__':
    start(sys.argv[1])
    sys.exit(220)
