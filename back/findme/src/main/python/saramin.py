import urllib.request
from bs4 import BeautifulSoup
import json
import sys
import pandas as pd
import re
import pymysql
from sqlalchemy import create_engine


def main(max_id):
    # MySQL Connector using pymysql
    pymysql.install_as_MySQLdb()
    # import MySQLdb
    db_data = 'mysql+mysqldb://' + 'ssafy' + ':' + 'ssafy' + '@' + 'localhost' + ':3306/' \
              + 'findme' + '?charset=utf8mb4'
    engine = create_engine(db_data, encoding='utf-8')
    conn = pymysql.connect(host='localhost',
                           user='ssafy',
                           password='ssafy',
                           db='findme',
                           connect_timeout=1)
    # https://oapi.saramin.co.kr/job-search/?access-key=0Q5ESrsPZNoxQPN98JpXKSFYmIHImsAyLfHbS2hUMGQUlxZ5O&start=0&count=110&job_category=4&sort=pd
    access_key = "0Q5ESrsPZNoxQPN98JpXKSFYmIHImsAyLfHbS2hUMGQUlxZ5O"
    start = 0
    isEnd = False
    while True:
        url = "https://oapi.saramin.co.kr/job-search/?access-key=" + access_key + "&start=" + str(
            start) + "&count=110&job_category=4&sort=pd"
        request = urllib.request.Request(url)
        response = urllib.request.urlopen(request)
        rescode = response.getcode()
        if (rescode == 200):
            response_body = response.read()
            # print(response_body.decode('utf-8'))

            language=['Java','C','Python','C++','C#','Visual Basic .NET','JavaScript','PHP','SQL','Go','R','Assembly','Swift','Ruby','MATLAB','PL/SQL','Perl','Visual Basic','Objective-C','Delphi','자바스크립트']
            dict = json.loads(response_body.decode('utf-8'))
            count = dict['jobs']['count']
            start = dict['jobs']['start']
            total = dict['jobs']['total']
            if count == 0:
                break
            if isEnd:
                break
            recruit_ids = []
            comp_names = []
            recruit_utilitys = []
            post_dates = []
            start_dates = []
            due_dates = []
            recruit_titles = []
            job_categorys = []
            tech_stacks = []
            urls = []
            img_urls = []
            salarys = []
            javas = []
            cs = []
            pythons = []
            cpps = []
            csharps = []
            vbns = []
            jss = []
            phps = []
            sqls = []
            gos = []
            rs = []
            assemblys = []
            swifts = []
            rubys = []
            matlabs = []
            pls = []
            perls = []
            vbs = []
            ocs = []
            delphis = []
            # print(re.split('[,·]','java,c·c++'))
            for i in range(0, count):
                recruit_id = dict['jobs']['job'][i]['id']
                if recruit_id <= max_id:
                    isEnd = True
                    break
                try:
                    comp_name = dict['jobs']['job'][i]['company']['detail']['name']
                except KeyError:
                    comp_name = ''
                try:
                    tmp_url = dict['jobs']['job'][i]['company']['detail']['href']
                    data = urllib.request.urlopen(tmp_url)
                    # 검색이 용이한 soup객체를 생성합니다.
                    soup = BeautifulSoup(data, 'html.parser')
                    findheader = soup.find('div', attrs={'class': 'header_info'})
                    try:
                        img_url = findheader.find('img')['src']
                    except:
                        img_url = ''
                except KeyError:
                    img_url = ''
                try:
                    tech_stack = dict['jobs']['job'][i]['keyword']
                except KeyError:
                    tech_stack = ''
                try:
                    recruit_utility = dict['jobs']['job'][i]['active']
                except KeyError:
                    recruit_utility = ''
                try:
                    salary = dict['jobs']['job'][i]['salary']['code']
                except KeyError:
                    salary = ''
                try:
                    post_date = dict['jobs']['job'][i]['posting-timestamp']
                except KeyError:
                    post_date = ''
                try:
                    start_date = dict['jobs']['job'][i]['opening-timestamp']
                except KeyError:
                    start_date = ''
                try:
                    due_date = dict['jobs']['job'][i]['expiration-timestamp']
                except KeyError:
                    due_date = ''
                try:
                    recruit_title = dict['jobs']['job'][i]['position']['title']
                except KeyError:
                    recruit_title = ''
                try:
                    job_category = dict['jobs']['job'][i]['position']['job-category']['name']
                except KeyError:
                    job_category = ''
                try:
                    url = dict['jobs']['job'][i]['url']
                except KeyError:
                    url = ''
                keyword = re.split('[,·]',tech_stack)
                lang_list = list(set(language)-set(set(language)-set(keyword)))
                mylist=[False]*20
                tech_stack=''
                for l in lang_list:
                    if(l=='자바스크립트'):
                        mylist[6]=True
                        tech_stack+="JavaScript, "
                    elif(mylist[language.index(l)]==False):
                        mylist[language.index(l)]=True
                        tech_stack+= l + ", "
                tech_stack=tech_stack[:-2]
                
                recruit_ids.append(recruit_id)
                comp_names.append(comp_name)
                img_urls.append(img_url)
                recruit_utilitys.append(recruit_utility)
                salarys.append(salary)
                post_dates.append(post_date)
                start_dates.append(start_date)
                due_dates.append(due_date)
                recruit_titles.append(recruit_title)
                job_categorys.append(job_category)
                tech_stacks.append(tech_stack)
                urls.append(url)
                javas.append(mylist[0])
                cs.append(mylist[1])
                pythons.append(mylist[2])
                cpps.append(mylist[3])
                csharps.append(mylist[4])
                vbns.append(mylist[5])
                jss.append(mylist[6])
                phps.append(mylist[7])
                sqls.append(mylist[8])
                gos.append(mylist[9])
                rs.append(mylist[10])
                assemblys.append(mylist[11])
                swifts.append(mylist[12])
                rubys.append(mylist[13])
                matlabs.append(mylist[14])
                pls.append(mylist[15])
                perls.append(mylist[16])
                vbs.append(mylist[17])
                ocs.append(mylist[18])
                delphis.append(mylist[19])
                # print(comp_name)
                # print()
            my_dict = {
                'id': recruit_ids,
                'comp_name': comp_names,
                'img_url': img_urls,
                'utility': recruit_utilitys,
                'post_date': post_dates,
                'start_date': start_dates,
                'due_date': due_dates,
                'title': recruit_titles,
                'job_category': job_categorys,
                'tech_stack': tech_stacks,
                'url': urls,
                'salary': salarys,
                'Java': javas,
                'C': cs,
                'Python': pythons,
                'C++': cpps,
                'C#': csharps,
                'VB.NET': vbns,
                'Java_Script': jss,
                'PHP': phps,
                'SQL': sqls,
                'Go': gos,
                'R': rs,
                'Assembly': assemblys,
                'Swift': swifts,
                'Ruby': rubys,
                'MATLAB': matlabs,
                'PL/SQL': pls,
                'Perl': perls,
                'Visual Basic': vbs,
                'Objective-C': ocs,
                'Delphi': delphis
            }
            my_df = pd.DataFrame(my_dict)
            # print(my_df)
            # Execute the to_sql for writting DF into SQL
            my_df.to_sql('temp', engine, if_exists='replace', index=False)
            # create cursor
            cursor = conn.cursor()
            # Execute query
            sql1 = "INSERT IGNORE INTO `recruit` (`COMP_NAME`, `ID`, `TITLE`, `JOB_CATEGORY`, `TECH_STACK`, `URL`, `UTILITY`, `POST_DATE`, `START_DATE`, `DUE_DATE`, `IMG_URL`) SELECT `COMP_NAME`, `ID`, `TITLE`, `JOB_CATEGORY`, `TECH_STACK`, `URL`, `UTILITY`, `POST_DATE`, `START_DATE`, `DUE_DATE`, `IMG_URL` FROM `temp`"
            cursor.execute(sql1)
            conn.commit()
            sql2 = "INSERT IGNORE INTO `saramin_data` (`ID`, `COMP_NAME`, `UTILITY`, `POST_DATE`, `DUE_DATE`, `SALARY`, `JAVA`, `C`, `PYTHON`, `C++`, `C#`, `VB_NET`, `JAVA_SCRIPT`, `PHP`, `SQL`, `GO`, `R`, `ASSEMBLY`, `SWIFT`, `RUBY`, `MATLAB`, `PL/SQL`, `PERL`, `VISUAL BASIC`, `OBJECTIVE-C`, `DELPHI`) SELECT `ID`, `COMP_NAME`, `UTILITY`, `POST_DATE`, `DUE_DATE`, `SALARY`, `JAVA`, `C`, `PYTHON`, `C++`, `C#`, `VB.NET`, `JAVA_SCRIPT`, `PHP`, `SQL`, `GO`, `R`, `ASSEMBLY`, `SWIFT`, `RUBY`, `MATLAB`, `PL/SQL`, `PERL`, `VISUAL BASIC`, `OBJECTIVE-C`, `DELPHI` FROM `temp`"
            cursor.execute(sql2)
            conn.commit()
            start += 1
        else:
            print("Error Code:" + rescode)
    engine.dispose()
    conn.close()


if __name__ == "__main__":
    main('1')