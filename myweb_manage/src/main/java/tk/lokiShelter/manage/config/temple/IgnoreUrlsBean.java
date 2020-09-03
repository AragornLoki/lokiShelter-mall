package tk.lokiShelter.manage.config.temple;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsBean {
    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }
}
