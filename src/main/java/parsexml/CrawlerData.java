package parsexml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrawlerData {

	private String name;
	private String className;
	private String type;
	private CrawlerPage page;
	private Map<String, List<CrawlerAttribute>> attributeMap = new HashMap<>();
	private Map<String, CrawlerDatasource> dataMap = new HashMap<>();
	private String template;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public CrawlerPage getPage() {
		return page;
	}
	public void setPage(CrawlerPage page) {
		this.page = page;
	}
	public Map<String, CrawlerDatasource> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, CrawlerDatasource> dataMap) {
		this.dataMap = dataMap;
	}
	public Map<String, List<CrawlerAttribute>> getAttributeMap() {
		return attributeMap;
	}
	public void setAttributeMap(Map<String, List<CrawlerAttribute>> attributeMap) {
		this.attributeMap = attributeMap;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
