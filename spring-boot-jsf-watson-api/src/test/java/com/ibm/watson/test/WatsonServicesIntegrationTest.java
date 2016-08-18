package com.ibm.watson.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.watson.app.WatsonExampleApplication;
import com.ibm.watson.config.JavaConfig;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyVision;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;
import com.ibm.watson.developer_cloud.alchemy.v1.model.ImageKeywords;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.Intent;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.service.WatsonKeysService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { WatsonExampleApplication.class, JavaConfig.class })
public class WatsonServicesIntegrationTest {

	@Autowired
	private WatsonKeysService watsonKeysService;

	@Test
	@Ignore
	public void alchemyLanguageTest() {
		AlchemyLanguage service = new AlchemyLanguage();
		service.setApiKey(watsonKeysService.getAlchemyServiceKey());

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.TEXT, "IBM Watson won the Jeopardy television show hosted by Alex Trebek");
		DocumentSentiment sentiment = service.getSentiment(params).execute();

		System.out.println(sentiment);
	}

	@Test
	@Ignore
	public void alchemyVisionTest() {
		AlchemyVision service = new AlchemyVision();
		service.setApiKey(watsonKeysService.getAlchemyServiceKey());

		File image = new File("src/test/resources/obama.jpg");
		Boolean forceShowAll = false;
		Boolean knowledgeGraph = false;
		ImageKeywords keywords = service.getImageKeywords(image, forceShowAll, knowledgeGraph).execute();

		System.out.println(keywords);
	}

	@Test
	@Ignore
	public void alchemyDataNewsTest() {
		AlchemyDataNews service = new AlchemyDataNews();
		service.setApiKey(watsonKeysService.getAlchemyServiceKey());
		Map<String, Object> params = new HashMap<String, Object>();

		String[] fields = new String[] { "enriched.url.title", "enriched.url.url", "enriched.url.author",
				"enriched.url.publicationDate", "enriched.url.enrichedTitle.entities",
		"enriched.url.enrichedTitle.docSentiment" };
		params.put(AlchemyDataNews.RETURN, StringUtils.join(fields, ","));
		params.put(AlchemyDataNews.START, "1440720000");
		params.put(AlchemyDataNews.END, "1441407600");
		params.put(AlchemyDataNews.COUNT, 7);

		DocumentsResult result = service.getNewsDocuments(params).execute();

		System.out.println(result);
	}

	@Test
	@Ignore
	public void conversationTest() {
		ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_07_11);
		service.setUsernameAndPassword(watsonKeysService.getConversationServiceUsername(),
				watsonKeysService.getConversationServicePassword());

		MessageRequest newMessage = new MessageRequest.Builder().inputText("What's the weather like?").intent(new Intent("wheather", 0.8))
				.build();
		MessageResponse response = service.message(watsonKeysService.getConversationServiceName(), newMessage).execute();
		System.out.println(response);
		newMessage = new MessageRequest.Builder().inputText("Thanks").context(response.getContext()).build();
		response = service.message(watsonKeysService.getConversationServiceName(), newMessage).execute();
		System.out.println(response);
	}

	@Test
	public void visualRecognitionTest() {
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey(watsonKeysService.getVisualRecognitionServiceKey());

		System.out.println("Classify an image");
		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
				.images(new File("src/test/resources/signal.jpg"))
				.build();
		VisualClassification result = service.classify(options).execute();
		System.out.println(result);
	}
}
