import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalculateTest {
    @Test
    void findMedianInOddArray() {
        List<Double> list = new ArrayList<>();
        {
            list.add(8.2);
            list.add(9.4);
            list.add(3.6);
            list.add(2.8);
            list.add(3.2);
        }
        Collections.sort(list);

        Assertions.assertEquals(3.6, list.get(list.size() / 2));
    }

    @Test
    void findMedianInEvenArray() {
        List<Integer> list = new ArrayList<>();
        {
            list.add(9);
            list.add(6);
            list.add(4);
            list.add(1);
            list.add(2);
            list.add(1);
        }
        Collections.sort(list); // 1 1 2 4 6 9

        Assertions.assertEquals(3, (list.get((list.size() / 2) - 1) + list.get(list.size() / 2)) / 2);
    }

    @Test
    void mediumPrice() {
        List<Double> values = new ArrayList<>();
        {
            values.add(2.3);
            values.add(3.5);
            values.add(5d);
            values.add(3.2);
            values.add(4d);
            values.add(9.1);
        }
        double totalAmount = 0.0;

        for (Double value : values) {
            totalAmount += value;
        }

        Assertions.assertEquals(27.1 / 6, totalAmount / values.size());
    }

}
