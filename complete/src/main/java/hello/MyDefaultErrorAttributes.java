package hello;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import java.util.Map;

@Component
public class MyDefaultErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> attributes = super.getErrorAttributes(webRequest,includeStackTrace);
        attributes.put("country",webRequest.getLocale().getCountry());
        return attributes;
    }

}
