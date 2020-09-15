import pandas as pd
from pytrends.request import TrendReq
from sqlalchemy import create_engine
from datetime import datetime
import math
import calendar

languages = [
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
]

sql_language = [
	'java',
	'c',
	'python',
	'c++',
	'c#',
	'vb_net',
	'java_script',
	'php',
	'sql',
	'go',
	'r',
	'assembly',
	'swift',
	'ruby',
	'matlab',
	'pl/sql',
	'perl',
	'visual basic',
	'objective-c',
	'delphi'
]


# 날짜 변경 함수
def trans_date(year, month, day):
	if month == 1: # start_date가 전년도 12월일 수 있다
		if day-6 <= 0:
			year -= 1
			month_ago = 12
		else:
			month_ago = month - 1

		last_day_month_ago = calendar.monthrange(year, month)[1] + (day-6)
		if month_ago < 10: month_ago = '0' + str(month_ago)
		else: month_ago = str(month_ago)
		start_date = str(year)+'-'+month_ago+'-'+str(last_day_month_ago)
		# start_date = datetime.strptime(start_day, '%Y-%m-%d')
	
	else:
		if day-6 <= 0:
			month_ago = month - 1
			last_day_month_ago = calendar.monthrange(year, month_ago)[1]
			if month_ago < 10: month_ago = '0' + str(month_ago)
			else: month_ago = str(month_ago)
			start_date = str(year)+'-'+month_ago+'-'+str(last_day_month_ago)
			# start_date = datetime.strptime(start_day, '%Y-%m-%d')
		else:
			this_month_day = day - 6
			if this_month_day < 10: this_month_day = '0' + str(this_month_day)
			else: this_month_day = str(this_month_day)

			if month < 10: month = '0' + str(month)
			else: month = str(month)

			start_date = str(year)+'-'+month+'-'+this_month_day
			# start_date = datetime.strptime(start_day, '%Y-%m-%d')
	return start_date

def main():
	engine = create_engine("mysql+pymysql://ssafy:"+"ssafy"+"@localhost:3306/findme?charset=utf8", encoding='utf-8')
	conn = engine.connect()

	year = datetime.today().year
	month = datetime.today().month
	day = datetime.today().day - 4 #매주 금요일에 업데이트
	start_date = trans_date(year, month, day)
	end_day = str(year)+'-'+str(month)+'-'+str(day)
	end_date = datetime.strptime(end_day, '%Y-%m-%d')
	end_date = end_date.strftime('%Y-%m-%d')
	result_date = start_date + ' ' + end_date

	pytrends = TrendReq(hl='ko', tz=540)


	keywords = []
		
	for j in range(0, 5):
		keywords.append(languages[j])    
		
	pytrends.build_payload(keywords, cat=0, timeframe=result_date, geo='KR', gprop='')
	getdatainfo = pytrends.interest_over_time()
	del getdatainfo['isPartial']

	for i in range(1, 4):
		keywords = []
		start = i*5
		for j in range(start, start+5):
			keywords.append(languages[j])    
		
		pytrends.build_payload(keywords, cat=0, timeframe=result_date, geo='KR', gprop='')
		getdata = pytrends.interest_over_time()
		for k in range(len(keywords)):
			getdatainfo[keywords[k]] = getdata[keywords[k]]

	my_dict = {}
	my_dict["date"] = "insert_date"
	for i in range(len(languages)):
		my_dict[languages[i]] = sql_language[i]

	getdatainfo = getdatainfo.reset_index().rename(columns=my_dict)

	for i in range(len(getdatainfo["insert_date"])):
		tmp = str(getdatainfo["insert_date"][i])
		tmp = tmp[:10]
		getdatainfo["insert_date"][i] = tmp

	getdatainfo.to_sql(name='gtrend_data', con=engine, if_exists='append', index=False)



	# 지역별로 저장
	keywords = []

	for j in range(0, 5):
		keywords.append(languages[j])

	pytrends.build_payload(keywords, cat=0, timeframe='today 1-m', geo='KR', gprop='')
	getdataregion = pytrends.interest_by_region(resolution='COUNTRY', inc_low_vol=True, inc_geo_code=False)

	for i in range(1, 4):
		keywords = []
		start = i*5
		for j in range(start, start+5):
			keywords.append(languages[j])    
		
		pytrends.build_payload(keywords, cat=0, timeframe='today 1-m', geo='KR', gprop='')
		getdata = pytrends.interest_by_region(resolution='COUNTRY', inc_low_vol=True, inc_geo_code=False)
		for k in range(len(keywords)):
			getdataregion[keywords[k]] = getdata[keywords[k]]

	my_dict = {}
	my_dict["geoName"] = "geo_name"
	index = [1,2,3,4,5,6,7,8,9,19,11,12,13,14,15,16]

	for i in range(len(languages)):
		my_dict[languages[i]] = sql_language[i]

	getdataregion = getdataregion.reset_index().rename(columns=my_dict)
	getdataregion["id"] = index
	getdataregion.set_index("id",inplace=True)
	getdataregion.to_sql(name='gtrend_geo_data', con=engine, if_exists='replace', index=True)
	conn.close()

if __name__ == "__main__":
	main()