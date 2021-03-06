package com.company.project.core.schedule;

import com.company.project.dto.PraiseListDTO;
import com.company.project.dto.PraiseListQueryDTO;
import com.company.project.model.HotWords;
import com.company.project.model.Users;
import com.company.project.service.HotWordsService;
import com.company.project.service.PraiseService;
import com.company.project.service.UsersService;
import io.jsonwebtoken.lang.Collections;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author tangzhi
 */
@Configuration
@EnableScheduling
public class HotWordsApp {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private UsersService usersService;

	@Resource
	private PraiseService praiseService;

	@Resource
	private HotWordsService hotWordsService;
	/**
	 * 定时热词分析
	 * 周二周四晚上7.30分析一次
	 * todo 多线程实现
	 */
	//@Scheduled(cron = "0 0/1 * * * ?")
	//@Scheduled(cron = "0 30 19 * * 2-4")
	public void operationWords() {
		logger.info("热词分析定时任务启动" + new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
		List<Users> list = usersService.findAll();
		list.forEach(user->{
			PraiseListQueryDTO plq = new PraiseListQueryDTO();
			plq.setPraiseTo(user.getUserName());
			List<PraiseListDTO> praiseList = praiseService.getPraiseDetail(plq);
			StringBuilder praiseContent = new StringBuilder();
			praiseList.forEach(p -> praiseContent.append(p.getContent()));

			Map<String,Integer> wordsList = getAnalyzer(praiseContent.toString());
			List<HotWords> hotWordsList = new ArrayList<>(5);

			hotWordsService.deleteByUserName(user.getUserName());
			wordsList.forEach((key,value)->{
				Date date = new Date();
				HotWords hotWords = new HotWords();
				hotWords.setUserName(user.getUserName());
				hotWords.setCreateBy("Schedule");
				hotWords.setLastUpdateBy("Schedule");
				hotWords.setWords(key);
				hotWords.setCounter(value);
				hotWords.setCreateDate(date);
				hotWords.setLastUpdateDate(date);
				hotWordsList.add(hotWords);
			});
			hotWordsService.save(hotWordsList);
		});
		logger.info("热词分析定时任务结束" + new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
	}

	//@Scheduled(cron = "0 0/1 * * * ?")
	public void hotWords(){
		logger.info("热词分析定时任务启动" + new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
		//定义线程总数
		int threadCount = 4;

		//定义每个线程处理的数据量
		int dataCount;
		List<Users> list = usersService.findAll();
		int total = list.size();
		if(total < threadCount){
			threadCount = total;
		}

		BlockingQueue<Runnable> task = new LinkedBlockingQueue<>(100);
		ExecutorService pool = new ThreadPoolExecutor(threadCount, threadCount,0L, TimeUnit.MILLISECONDS,task);
		dataCount = (int)Math.floor(list.size()/threadCount);

		List<Future<String>> futureList = new ArrayList<>();

		for(int i = 0; i < threadCount; i++){
			int toIndex;
			toIndex = (i+1)*dataCount;
			if(i+1 == threadCount){
				toIndex = total;
			}
			List<Users> subList = list.subList(i*dataCount,toIndex);
			HotWordsAnalysis hotWordsAnalysis = new HotWordsAnalysis(subList);

			Future<String> successCount = pool.submit(hotWordsAnalysis);
			futureList.add(successCount);
		}
		pool.shutdown();

		ExecutorService service = Executors.newSingleThreadExecutor();
		service.execute(()->{
			for (Future<String> future : futureList) {
				try {
					while (true) {
						if (future.isDone() && !future.isCancelled()) {
							System.out.println("Future:" + future
									+ ",Result:" + future.get());
							break;
						} else {
							Thread.sleep(1000);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		service.shutdown();
		logger.info("热词分析定时任务结束" + new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(new Date()));
	}

	/**
	 * 采用IK进行独立IK分词
	 */
	private class HotWordsAnalysis implements Callable<String>{

		private List<Users> users;
		public HotWordsAnalysis(List<Users> users) {
			this.users = users;
		}


		@Override
		public String call() {
			for (Users user:users) {
				PraiseListQueryDTO plq = new PraiseListQueryDTO();
				plq.setPraiseTo(user.getUserName());
				List<PraiseListDTO> praiseList = praiseService.getPraiseDetail(plq);
				StringBuilder praiseContent = new StringBuilder();
				praiseList.forEach(p -> praiseContent.append(p.getContent()));

				// 如果点赞词为空,不用分析了
				if(StringUtils.isBlank(praiseContent.toString())){
					continue;
				}

				Map<String,Integer> wordsList = getAnalyzer(praiseContent.toString());
				List<HotWords> hotWordsList = new ArrayList<>(5);

				hotWordsService.deleteByUserName(user.getUserName());
				wordsList.forEach((key,value)->{
					Date date = new Date();
					HotWords hotWords = new HotWords();
					hotWords.setUserName(user.getUserName());
					hotWords.setCreateBy("Schedule");
					hotWords.setLastUpdateBy("Schedule");
					hotWords.setWords(key);
					hotWords.setCounter(value);
					hotWords.setCreateDate(date);
					hotWords.setLastUpdateDate(date);
					hotWordsList.add(hotWords);
				});
				hotWordsService.save(hotWordsList);
			}

			return users.stream().map(Users::getUserName).collect(Collectors.joining(""));
		}
	}

	/**
	 * 采用IK进行独立IK分词
	 */
	private static Map<String,Integer> getAnalyzer(String str){
		Map<String,Integer> resultMap = new HashMap<>(5);
		//如果str为空，返回空值
		if(str == null){
			return resultMap;
		}
		try{
			//直接使用 StringReader 读取字符串
			StringReader sr = new StringReader(str);
			//true智能切分
			IKSegmenter ik = new IKSegmenter(sr, true);
			Lexeme lex;
			Map<String,Integer> map = new HashMap<>(5);
			while((lex=ik.next())!=null){
				String text = lex.getLexemeText();
				if(map.containsKey(text)){
					map.put(text,map.get(text) + 1);
				}else{
					map.put(text,1);
				}
			}
			map = sortMapByValue(map);
			int index = 0;
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				if(index >= 15){
					break;
				}
				if(entry.getKey().length() == 1){
					continue;
				}
				resultMap.put(entry.getKey(),entry.getValue());
				index++;
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		return resultMap;
	}

	/**
	 * 使用 Map按value进行排序
	 * @param oriMap 原始Map
	 * @return 排序后的数组
	 */
	public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, Integer> sortedMap = new LinkedHashMap<>();
		List<Map.Entry<String, Integer>> entryList = new ArrayList<>(
				oriMap.entrySet());
		entryList.sort(new MapValueComparator());

		Iterator<Map.Entry<String, Integer>> inter = entryList.iterator();
		Map.Entry<String, Integer> tmpEntry;
		while (inter.hasNext()) {
			tmpEntry = inter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}
}
