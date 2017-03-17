package chinaunicom;

import java.util.List;

public class CrawlerPage {
	
	private String attribute;
	private List<CrawlerPath> pathes;
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public List<CrawlerPath> getPathes() {
		return pathes;
	}
	public void setPathes(List<CrawlerPath> pathes) {
		this.pathes = pathes;
	}
	
	
}