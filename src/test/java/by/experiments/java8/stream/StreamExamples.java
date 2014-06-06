package by.experiments.java8.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Simple examples of using Stream API.
 * 
 * @author Tsimafei_Shchytkavets
 */
public class StreamExamples
{
    private Collection<String> list;
    private Collection<Double> bigList;
    private ArrayList<String> stringResults;
    private ArrayList<Integer> intResults;

    @Before
    public void setUp()
    {
        list = Arrays.asList("Bb", "BBB", "AAAA", "Aaa");
        stringResults = new ArrayList<>();
        intResults = new ArrayList<>();

        final int max = 10000000;
        bigList = new ArrayList<>(max);
        for (int i = max; i > 0; i--)
        {
            bigList.add(Math.random());
        }
    }

    @Test
    public void filter()
    {
        list.stream().filter((s) -> s.startsWith("A")).forEach((s) -> stringResults.add(s));

        assertThat("Unexpected size", stringResults.size(), is(2));
    }

    @Test
    public void sorted()
    {
        list.stream().sorted().forEach((s) -> stringResults.add(s));

        assertThat("Unexpected result", stringResults.get(0), is("AAAA"));
    }

    @Test
    public void map()
    {
        list.stream().map((s) -> s.length()).forEach((i) -> intResults.add(i));

        assertThat("Unexpected result", intResults.get(0), is(2));
    }

    @Test
    public void match()
    {
        assertTrue(list.stream().anyMatch((s) -> s.startsWith("B")));

        assertFalse(list.stream().allMatch((s) -> s.startsWith("A")));

        assertTrue(list.stream().noneMatch((s) -> s.startsWith("X")));
    }

    @Test
    public void count()
    {
        assertThat("Unexpected result", list.stream().count(), is(4L));
    }

    @Test
    public void reduce()
    {
        final Optional<String> reduced = list.stream().reduce((s1, s2) -> s1 + " then " + s2);

        reduced.ifPresent(System.out::println);
    }

    @Test
    public void parallel()
    {
        long start2 = System.nanoTime();
        long count2 = bigList.stream().parallel().sorted().count();
        long finish2 = System.nanoTime();

        System.out.println(count2);
        System.out.println("Parallel: " + TimeUnit.NANOSECONDS.toMillis(finish2-start2) + "ms");

        long start1 = System.nanoTime();
        long count1 = bigList.stream().sorted().count();
        long finish1 = System.nanoTime();

        System.out.println(count1);
        System.out.println("Sequential: " + TimeUnit.NANOSECONDS.toMillis(finish1-start1) + "ms");
    }
}
