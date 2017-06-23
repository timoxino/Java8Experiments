package by.experiments.java8.functionalinterface;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Simple examples of using Functional Interfaces.
 * 
 * @author Tsimafei_Shchytkavets
 */
public class FunctionalInterfaceExamples
{
    @Test
    public void predicate()
    {
        Predicate<String> notEmpty = (s) -> s != null && s.length() > 0;

        assertFalse(notEmpty.test(null));
        assertTrue(notEmpty.test("Something"));
        assertFalse(notEmpty.test(""));

        Predicate<String> notNull = Objects::nonNull;

        assertFalse(notNull.test(null));
        assertTrue(notNull.negate().test(null));
    }

    @Test
    public void function()
    {
        Function<String, Integer> toInteger = Integer::valueOf;
        assertThat("Unexpected result", toInteger.apply("123"), is(123));

        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        assertThat("Unexpected result", backToString.apply("123"), is("123"));
    }

    @Test
    public void supplier()
    {
        Supplier<Object> objectSupplier = Object::new;

        assertThat("Unexpected result", objectSupplier.get(), is(notNullValue()));
    }

    @Test
    public void consumer()
    {
        Consumer<Object> hashCodePrinter = (o) -> System.out.println(o.hashCode());

        hashCodePrinter.accept("test");
    }

    @Test
    public void comparator()
    {
        Comparator<String> stringComparator = (s1, s2) -> s1.compareTo(s2);

        assertThat("Unexpected result", stringComparator.compare("Aaa", "Aaa"), is(0));
        assertThat("Unexpected result", stringComparator.compare("Aaa", "Bbb"), is(-1));
        assertThat("Unexpected result", stringComparator.reversed().compare("Bbb", "Aaa"), is(-1));
    }

    @Test
    public void optional()
    {
        Optional<String> optionalNull = Optional.ofNullable(null);

        assertFalse(optionalNull.isPresent());
        assertThat("Unexpected result", optionalNull.orElse("alernative"), is("alernative"));

        Optional<String> optionalString = Optional.ofNullable("test");

        assertTrue(optionalString.isPresent());
        assertThat("Unexpected result", optionalString.get(), is("test"));
        assertThat("Unexpected result", optionalString.orElse("alernative"), is("test"));
    }

    @Test
    public void flatMap()
    {
        class Order
        {
            class LineItem
            {
            }

            List<LineItem> lineItemList = Arrays.asList(new LineItem(), new LineItem());
        }

        Stream<Order> orderStream = Stream.of(new Order[]{new Order(), new Order()});
        long lineItemCount = orderStream.flatMap(order -> order.lineItemList.stream()).count();
        assertThat("Unexpected result", lineItemCount, is(4L));
    }
}
