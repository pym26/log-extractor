package service.impl;


import dto.LogMessage;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import service.ILogService;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogServiceImpl implements ILogService
{

	public void read(final String from, final String to) throws IOException
	{
		final Settings settings = Settings.builder()
				.put("cluster.name","log-extractor")
				.put("client.transport.sniff", true).build();
		final TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName("0.0.0.0"), 9300));

		final QueryBuilder qb = QueryBuilders.rangeQuery("time").from(from).to(to);
		final SearchRequestBuilder searchRequestBuilder = client.prepareSearch("logs");
		final SearchResponse searchResponse = searchRequestBuilder
				.setFetchSource(new String[]{"message"}, null)
				.setQuery(qb).execute().actionGet();
		final SearchHits searchHits = searchResponse.getHits();

		final List<LogMessage> rs = Arrays.stream(searchHits.getHits()).map(hit -> {
			LogMessage log = new LogMessage();
			log.setMessage(hit.getSourceAsString());
			return log;
		}).collect(Collectors.toList());
		rs.stream().forEach(a ->{
			System.out.println(a.getMessage());
			}
		);
	}
}
