package com.smart.assignment.controller;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author osamah kanaan <osamah.kan3an@gmail.com>
 * Created on Oct 1, 2021
 */
@RestController
public class TomcatController {

    @GetMapping("/hello")
    public Collection<String> sayHello() {
        return IntStream.range(0, 10)
          .mapToObj(i -> "Hello number " + i)
          .collect(Collectors.toList());
    }
}
