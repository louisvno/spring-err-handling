package hello;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape =  JsonFormat.Shape.OBJECT)
public enum Planet {
    MARS("Mars", 39399330L),
    VENUS("Venus", 9330L);

    private Long diameter;
    private String name;

    Planet(String name, Long diameter) {
        this.diameter = diameter;
        this.name = name;
    };

    public Long getDiameter() {
        return this.diameter;
    }

    public String getName() {
        return this.name;
    }
}
