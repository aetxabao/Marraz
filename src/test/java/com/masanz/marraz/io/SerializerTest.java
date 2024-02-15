package com.masanz.marraz.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.masanz.marraz.enums.EForm;
import com.masanz.marraz.model.*;
import org.junit.jupiter.api.Test;

class SerializerTest {

//(1.00,2.00)_(3.00,4.00)
//{"p1":{"x":1.0,"y":2.0},"p2":{"x":3.0,"y":4.0},"strokeColor":{"red":0.0,"green":0.0,"blue":0.0,"opacity":1.0}}
//(1.00,2.00)\(3.00,4.00)
//{"corner":{"x":1.0,"y":2.0},"width":2.0,"height":2.0,"strokeColor":{"red":0.0,"green":0.0,"blue":0.0,"opacity":1.0},"fillColor":{"red":1.0,"green":1.0,"blue":1.0,"opacity":1.0}}
//(2.00,3.00)_r=1.41
//{"center":{"x":2.0,"y":3.0},"radius":1.4142135623730951,"strokeColor":{"red":0.0,"green":0.0,"blue":0.0,"opacity":1.0},"fillColor":{"red":1.0,"green":1.0,"blue":1.0,"opacity":1.0}}
//(1.00,2.00)-(3.00,4.00)-(5.00,6.00)
//{"p1":{"x":1.0,"y":2.0},"p2":{"x":3.0,"y":4.0},"p3":{"x":5.0,"y":6.0},"strokeColor":{"red":0.0,"green":0.0,"blue":0.0,"opacity":1.0},"fillColor":{"red":1.0,"green":1.0,"blue":1.0,"opacity":1.0}}
//(10.00,13.50)\(16.00,23.50)
//{"corner":{"x":10.0,"y":13.5},"width":6.0,"height":10.0,"strokeColor":{"red":0.0,"green":0.0,"blue":0.0,"opacity":1.0},"fillColor":{"red":1.0,"green":1.0,"blue":1.0,"opacity":1.0}}
    @Test
    void fromJSON() {
        String[] jsons = {
                "{\"p1\":{\"x\":1.0,\"y\":2.0},\"p2\":{\"x\":3.0,\"y\":4.0},\"strokeColor\":{\"red\":0.0,\"green\":0.0,\"blue\":0.0,\"opacity\":1.0}}",
                "{\"corner\":{\"x\":1.0,\"y\":2.0},\"width\":2.0,\"height\":2.0,\"strokeColor\":{\"red\":0.0,\"green\":0.0,\"blue\":0.0,\"opacity\":1.0},\"fillColor\":{\"red\":1.0,\"green\":1.0,\"blue\":1.0,\"opacity\":1.0}}",
                "{\"center\":{\"x\":2.0,\"y\":3.0},\"radius\":1.4142135623730951,\"strokeColor\":{\"red\":0.0,\"green\":0.0,\"blue\":0.0,\"opacity\":1.0},\"fillColor\":{\"red\":1.0,\"green\":1.0,\"blue\":1.0,\"opacity\":1.0}}",
                "{\"p1\":{\"x\":1.0,\"y\":2.0},\"p2\":{\"x\":3.0,\"y\":4.0},\"p3\":{\"x\":5.0,\"y\":6.0},\"strokeColor\":{\"red\":0.0,\"green\":0.0,\"blue\":0.0,\"opacity\":1.0},\"fillColor\":{\"red\":1.0,\"green\":1.0,\"blue\":1.0,\"opacity\":1.0}}",
                "{\"corner\":{\"x\":10.0,\"y\":13.5},\"width\":6.0,\"height\":10.0,\"strokeColor\":{\"red\":0.0,\"green\":0.0,\"blue\":0.0,\"opacity\":1.0},\"fillColor\":{\"red\":1.0,\"green\":1.0,\"blue\":1.0,\"opacity\":1.0}}"
        };
        EForm formas [] = {EForm.LINE, EForm.RECTANGLE, EForm.CIRCLE, EForm.TRIANGLE, EForm.HOUSE};
        try {
            for (int i = 0; i < jsons.length; i++) {
                AForm forma = Serializer.fromJSON(jsons[i], formas[i]);
                System.out.println(forma);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void toJSON() throws JsonProcessingException {
        AForm formas [] = {new Line(new Point(1, 2), new Point(3, 4)),
                            new Rectangle(new Point(1, 2), new Point(3, 4)),
                            new Circle(new Point(1, 2), new Point(3, 4)),
                            new Triangle(new Point(1, 2), new Point(3, 4), new Point(5, 6)),
                            new House(new Point(10, 20), new Point(16, 24), new Point(5, 30)) };
        for (AForm forma : formas) {
            System.out.println(forma);
            String json = Serializer.toJSON(forma);
            System.out.println(json);
        }
    }
}