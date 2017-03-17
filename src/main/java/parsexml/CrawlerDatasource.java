package parsexml;

import java.util.ArrayList;
import java.util.List;

public class CrawlerDatasource {
	
	private String id;
	private List<CrawlerPath> pathes = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CrawlerPath> getPathes() {
		return pathes;
	}
	public void setPathes(List<CrawlerPath> pathes) {
		this.pathes = pathes;
	}
	
	
}
