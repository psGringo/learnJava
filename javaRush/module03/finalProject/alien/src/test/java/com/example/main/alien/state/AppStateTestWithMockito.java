package com.example.main.alien.state;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class AppStateTestWithMockito {

    @Mock
    List virtualMockList;

    @Test
    public void mockitoVirtualObject() {
        virtualMockList.add("one");
        virtualMockList.add("two");
        Assertions.assertTrue(virtualMockList.size() == 0); // true
//        Assertions.assertTrue(virtualMockList.size() > 1); // false
        Mockito.when(virtualMockList.size()).thenReturn(10);
        Assertions.assertTrue(virtualMockList.size() > 1); // false
    }


    @Test
    public void mockitoWhenThenReturn() {
        virtualMockList.add("one");
        virtualMockList.add("two");

        // equivalent mocks
        Mockito.doReturn(10).when(virtualMockList).size();
        Mockito.when(virtualMockList.size()).thenReturn(10);

        Assertions.assertTrue(virtualMockList.size() > 1); // false
    }

    @Test
    public void throwTest() {
        // equivalents
        //        Mockito.doThrow(new RuntimeException()).when(virtualMockList).size();
        Mockito.when(virtualMockList.size()).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            virtualMockList.size();
        });
    }

    @Test
    public void callWithParams() {
        Mockito.doReturn("myValue").when(virtualMockList).get(10);
        Assertions.assertEquals("myValue", virtualMockList.get(10));
    }

    @Test
    void anyValue() {
        // equivalents
        // Mockito.doReturn("myValue").when(virtualMockList).get(any(int.class));
        Mockito.doReturn("myValue").when(virtualMockList).get(anyInt());

        Assertions.assertEquals("myValue", virtualMockList.get(10));
        Assertions.assertEquals("myValue", virtualMockList.get(11));
        Assertions.assertEquals("myValue", virtualMockList.get(12));
    }

    @Test
    void doAnswerExample() {
        Mockito.doAnswer(invocation -> {
            int arg = invocation.getArgument(0);
            return arg * arg;
        }).when(virtualMockList).get(anyInt());

        Assertions.assertEquals(100, virtualMockList.get(10));
    }

    @Test
    void verifyExample() {
        var value = virtualMockList.get(10);
        Mockito.verify(virtualMockList, Mockito.times(1)).get(10);
    }

    @Test
    void verifyNoMoreInteractions() {
        Mockito.verifyNoMoreInteractions(virtualMockList);
    }

    @Test
    void verifyOrder() {
        virtualMockList.get(10);
        virtualMockList.size();
        var inOrder = Mockito.inOrder(virtualMockList);
        inOrder.verify(virtualMockList).get(10);
        inOrder.verify(virtualMockList).size();
    }

    @Test
    public void spyRedirection() {

        List list = new LinkedList(); // works with LinkedList, but doesn't work with ArrayList
        List spy = Mockito.spy(list);

        //optionally, you can stub out some methods:
//        Mockito.when(spy.size()).thenReturn(100);

        //using the spy calls real methods
        spy.add("one");
        spy.add("two");

        //prints "one" - the first element of a list
        System.out.println(spy.get(0));

        //size() method was stubbed - 100 is printed
        System.out.println(spy.size());
    }

    @Test
    public void spyRedirection2() {

        List spyMockitoList2 = Mockito.spy(new ArrayList());

        spyMockitoList2.add("one");
        spyMockitoList2.add("two");

        Assertions.assertTrue(spyMockitoList2.size() > 0); // false
    }

    @Test
    void staticMethod() {
        try (MockedStatic<UtilsExample> utils = Mockito.mockStatic(UtilsExample.class)) {
            utils.when(UtilsExample::doSmth).thenReturn("hi, from static method");
            Assertions.assertEquals("hi, from static method", UtilsExample.doSmth());
        }
    }
}

class UtilsExample {
    static String doSmth() {
        return "static";
    }

}
