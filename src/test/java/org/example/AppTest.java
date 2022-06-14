package org.example;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    private List<String> dataValues = Arrays.asList("The", "quick", "brown", "fox", "jump", "over", "the", "lazy", "dog");

    private Flux dataSrc;
 //   private let

    @BeforeEach
    public void init(){
        this.dataSrc = Flux.fromIterable(this.dataValues);
      //  this.ctr =
    }

    @Test
    public void shouldAnswerWithTrue()
    {
    //    assertTrue( true );
    }


}
