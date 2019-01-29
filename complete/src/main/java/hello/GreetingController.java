package hello;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping(value = "greetings/{id}", method = {RequestMethod.GET})
    public HttpEntity<String> greeting(@PathVariable("id") Long id, HttpServletResponse httpServletResponse) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, id));

        httpServletResponse.setHeader("Cache-Control","max-age=60");
        httpServletResponse.setHeader("ETag","abcd");

        return new ResponseEntity<>("<!DOCTYPE html><html><head></head>" +
                "<body>" +
                    "<a href='http://localhost:8080/greetings/1'>load again</a>" +
                "</body></html>", HttpStatus.OK);
    }

    @RequestMapping(value = "planets/{name}", method = {RequestMethod.GET}, produces = "application/json")
    public HttpEntity<Planet> planet(@PathVariable("name") String name) {
        String cleanName = StringUtils.trimAllWhitespace(name).toUpperCase();

        try{
            return new ResponseEntity<>(Planet.valueOf(cleanName), HttpStatus.OK);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"planet: " + name +  " not found");
        }
    }

    @RequestMapping(value = "planetz/{name}", method = {RequestMethod.GET}, produces = "application/json")
    public HttpEntity<Planet> planetz(@PathVariable("name") String name) {
        String cleanName = StringUtils.trimAllWhitespace(name).toUpperCase();

        throw new IllegalStateException("Error message");
    }

    @RequestMapping(value = "users/{id}", method = {RequestMethod.GET}, produces = "application/json")
    public HttpEntity<User> planet(@PathVariable("id") Integer id) {

        User u1 = new User(id, "Joop", new ArrayList<>());
        List<Item> items = Arrays.asList(new Item("PC",u1,id));
        u1.setUserItems(items);
        return new ResponseEntity<>(u1, HttpStatus.OK);
    }

    @RequestMapping(value = "items/{id}", method = {RequestMethod.GET}, produces = "application/json")
    public HttpEntity<Item> item(@PathVariable("id") Integer id) {

        User u1 = new User(id, "Joop", new ArrayList<>());
        Item item = new Item("PC",u1,id);
        item.setOwner(u1);
        u1.setUserItems(Arrays.asList(item));
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    private String getRequestMethods(String methodName, Class... parameterTypes) {
        try {
            return Arrays.stream(this.getClass().getMethod(methodName, parameterTypes).getDeclaredAnnotations())
                    .filter(ann -> ann instanceof RequestMapping)
                    .map(ann -> ((RequestMapping) ann).method())
                    .flatMap(Arrays :: stream)
                    .map(RequestMethod :: toString)
                    .reduce((a, b) -> a + "," + b)
                    .orElse("");
        } catch (NoSuchMethodException exception){
            return "";
        }
    }

}