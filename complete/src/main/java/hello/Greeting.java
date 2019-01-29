package hello;

import org.springframework.hateoas.ResourceSupport;

public class Greeting extends ResourceSupport {

    private final String content;
    private Long id;

    public Greeting( String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

