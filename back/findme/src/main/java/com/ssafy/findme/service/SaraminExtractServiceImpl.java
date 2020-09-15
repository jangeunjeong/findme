package com.ssafy.findme.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.ssafy.findme.domain.SaraminData;
import com.ssafy.findme.domain.SaraminExtract;
import com.ssafy.findme.dto.SaraminExtractDTO;
import com.ssafy.findme.repository.SaraminExtractRepository;
import com.ssafy.findme.repository.SaraminRepository;

@Service
public class SaraminExtractServiceImpl implements ISaraminExtractService {

	@Autowired
	private SaraminRepository saraminrepo;
	@Autowired
	private SaraminExtractRepository saraminexreop;
	@Autowired
	private ModelMapper modelMapper;

	private int salary(int code) {
		int salary = 0;
		switch (code) {
		case 0:
			salary = 0;
			break;
		case 3:
			salary = 1400;
			break;
		case 4:
			salary = 1600;
			break;
		case 5:
			salary = 1800;
			break;
		case 6:
			salary = 2000;
			break;
		case 7:
			salary = 2200;
			break;
		case 8:
			salary = 2400;
			break;
		case 9:
			salary = 2600;
			break;
		case 10:
			salary = 2800;
			break;
		case 11:
			salary = 3000;
			break;
		case 12:
			salary = 3200;
			break;
		case 13:
			salary = 3400;
			break;
		case 14:
			salary = 3600;
			break;
		case 15:
			salary = 3800;
			break;
		case 16:
			salary = 4000;
			break;
		case 17:
			salary = 5000;
			break;
		case 18:
			salary = 6000;
			break;
		case 19:
			salary = 7000;
			break;
		case 20:
			salary = 8000;
			break;
		case 21:
			salary = 9000;
			break;
		case 22:
			salary = 10000;
			break;
		case 23:
			salary = 12000;
			break;
		case 99:
			salary = 0;
			break;
		}
		return salary;
	}

	@Scheduled(cron = "0 48 4 * * *")
	public void collectSaraminExtractData() {
		System.out.println("start to collect saramin extract data!! " + new Date());
		List<SaraminData> assemblys = saraminrepo.findByAssemblysTrue();
		List<SaraminData> cpps = saraminrepo.findByCppsTrue();
		List<SaraminData> cs = saraminrepo.findByCsTrue();
		List<SaraminData> csharps = saraminrepo.findByCsharpsTrue();
		List<SaraminData> delphis = saraminrepo.findByDelphisTrue();
		List<SaraminData> gos = saraminrepo.findByGosTrue();
		List<SaraminData> javas = saraminrepo.findByJavasTrue();
		List<SaraminData> javascripts = saraminrepo.findByJssTrue();
		List<SaraminData> matlabs = saraminrepo.findByMatlabsTrue();
		List<SaraminData> objectives = saraminrepo.findByOcsTrue();
		List<SaraminData> perls = saraminrepo.findByPerlsTrue();
		List<SaraminData> phps = saraminrepo.findByPhpsTrue();
		List<SaraminData> pls = saraminrepo.findByPlsTrue();
		List<SaraminData> pythons = saraminrepo.findByPythonsTrue();
		List<SaraminData> rs = saraminrepo.findByRsTrue();
		List<SaraminData> rubys = saraminrepo.findByRubysTrue();
		List<SaraminData> sqls = saraminrepo.findBySqlsTrue();
		List<SaraminData> swifts = saraminrepo.findBySwiftsTrue();
		List<SaraminData> vbs = saraminrepo.findByVbnsTrue();
		List<SaraminData> visuals = saraminrepo.findByVbsTrue();
		System.out.println(javas.size());
		// 많이 사용하는 순

		Map<String, Integer> adopted_map = new HashMap<String, Integer>();
		adopted_map.put("Assembly", assemblys.size());
		adopted_map.put("C++", cpps.size());
		adopted_map.put("C", cs.size());
		adopted_map.put("C#", csharps.size());
		adopted_map.put("Delphi", delphis.size());
		adopted_map.put("Go", gos.size());
		adopted_map.put("Java", javas.size());
		adopted_map.put("JavaScript", javascripts.size());
		adopted_map.put("MATLAB", matlabs.size());
		adopted_map.put("Objective-C", objectives.size());
		adopted_map.put("Perl", perls.size());
		adopted_map.put("PHP", phps.size());
		adopted_map.put("PL/SQL", pls.size());
		adopted_map.put("Python", pythons.size());
		adopted_map.put("R", rs.size());
		adopted_map.put("Ruby", rubys.size());
		adopted_map.put("SQL", sqls.size());
		adopted_map.put("Swift", swifts.size());
		adopted_map.put("Visual Basic .NET", vbs.size());
		adopted_map.put("Visual Basic", visuals.size());

		// value 내림차순으로 정렬하고, value가 같으면 key 오름차순으로 정렬
		List<Map.Entry<String, Integer>> adopted_list = new LinkedList<>(adopted_map.entrySet());

		Collections.sort(adopted_list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				int comparision = (o1.getValue() - o2.getValue()) * -1;
				return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
			}
		});

		SaraminExtract adopted = new SaraminExtract();
		// 순서유지를 위해 LinkedHashMap을 사용
		Map<String, Integer> sortedAdopted = new LinkedHashMap<>();
		int index = 1;
		for (Iterator<Map.Entry<String, Integer>> iter = adopted_list.iterator(); iter.hasNext();) {
			Map.Entry<String, Integer> entry = iter.next();
			sortedAdopted.put(entry.getKey(), entry.getValue());
			adopted.setLanguage(entry.getKey());
			adopted.setAdopted(entry.getValue());
			adopted.setAdoptedRank(index);
			saraminexreop.save(adopted);
			index += 1;
		}

		System.out.println(sortedAdopted);

		// 연봉순
		Map<String, Integer> paid_map = new HashMap<String, Integer>();
		paid_map.put("Assembly", getSalary(assemblys));
		paid_map.put("C++", getSalary(cpps));
		paid_map.put("C", getSalary(cs));
		paid_map.put("C#", getSalary(csharps));
		paid_map.put("Delphi", getSalary(delphis));
		paid_map.put("Go", getSalary(gos));
		paid_map.put("Java", getSalary(javas));
		paid_map.put("JavaScript", getSalary(javascripts));
		paid_map.put("MATLAB", getSalary(matlabs));
		paid_map.put("Objective-C", getSalary(objectives));
		paid_map.put("Perl", getSalary(perls));
		paid_map.put("PHP", getSalary(phps));
		paid_map.put("PL/SQL", getSalary(pls));
		paid_map.put("Python", getSalary(pythons));
		paid_map.put("R", getSalary(rs));
		paid_map.put("Ruby", getSalary(rubys));
		paid_map.put("SQL", getSalary(sqls));
		paid_map.put("Swift", getSalary(swifts));
		paid_map.put("Visual Basic .NET", getSalary(vbs));
		paid_map.put("Visual Basic", getSalary(visuals));

		// value 내림차순으로 정렬하고, value가 같으면 key 오름차순으로 정렬
		List<Map.Entry<String, Integer>> paid_list = new LinkedList<>(paid_map.entrySet());

		Collections.sort(paid_list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				int comparision = (o1.getValue() - o2.getValue()) * -1;
				return comparision == 0 ? o1.getKey().compareTo(o2.getKey()) : comparision;
			}
		});
		SaraminExtract paid = new SaraminExtract();
		// 순서유지를 위해 LinkedHashMap을 사용
		Map<String, Integer> sortedPaid = new LinkedHashMap<>();
		index = 1;
		for (Iterator<Map.Entry<String, Integer>> iter = paid_list.iterator(); iter.hasNext();) {
			Map.Entry<String, Integer> entry = iter.next();
			sortedPaid.put(entry.getKey(), entry.getValue());
			paid.setLanguage(entry.getKey());
			paid.setSalary(entry.getValue());
			paid.setSalaryRank(index);
			saraminexreop.update((long) entry.getValue(), (long) index, entry.getKey());
			index += 1;
		}

	}

	@Override
	public List<SaraminExtractDTO> ranking() {
		Type listType = new TypeToken<List<SaraminExtractDTO>>() {
		}.getType();
		List<SaraminExtractDTO> adoptedList = modelMapper.map(saraminexreop.findAll(), listType);
		return adoptedList;
	}

	private int getSalary(List<SaraminData> lang) {
		if (lang.size() == 0)
			return 0;
		long sum = 0;
		int cnt = 0;
		for (int i = 0; i < lang.size(); i++) {
			int salary = salary((int) (long) lang.get(i).getSalary());
			if (salary > 0) {
				sum += salary;
				cnt++;
			}
		}
		if (cnt == 0)
			return 0;
		int avg_salary = (int) sum / cnt;
		return avg_salary;
	}

}
