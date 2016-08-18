package com.ibm.watson.service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;

@Service
public class AlchemyDataNewsService extends BasicService {
	@Autowired
	private WatsonKeysService watsonKeysService;
	private AlchemyDataNews alchemyDataNews;
	private final String[] fields = { "enriched.url.title", "enriched.url.url", "enriched.url.author",
			"enriched.url.publicationDate", "enriched.url.enrichedTitle.entities",
	"enriched.url.enrichedTitle.docSentiment" };

	@PostConstruct
	public void init() {
		log.info("Iniciando alchemy data news");
		alchemyDataNews = new AlchemyDataNews();
		alchemyDataNews.setApiKey(watsonKeysService.getAlchemyServiceKey());
	}

	public String getNewsBetween(LocalDate startDate, LocalDate endDate, int total) {
		if (startDate.isAfter(endDate) || total < 0) {
			throw new IllegalArgumentException("starDate debe ser menor a endDate y total mayor a 0");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyDataNews.RETURN, StringUtils.join(fields, ","));
		params.put(AlchemyDataNews.START, toUTCSecond(startDate));
		params.put(AlchemyDataNews.END, toUTCSecond(endDate));
		params.put(AlchemyDataNews.COUNT, total);
		return alchemyDataNews.getNewsDocuments(params).execute().toString();
	}
	public long toUTCSecond(LocalDate localDate){
		return localDate.atStartOfDay(ZoneOffset.UTC).toInstant().getEpochSecond();
	}
}
