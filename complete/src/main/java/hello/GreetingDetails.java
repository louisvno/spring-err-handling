package hello;

import org.springframework.hateoas.ResourceSupport;

public class GreetingDetails extends ResourceSupport {
    String language;
    public GreetingDetails(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
