package com.dad;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@Autowired
	private UserRepository usuarioRepositorio;
	
    private static final String template = "Hello, %s!";
    private static final String estaOnline = "¡%s ha iniciado sesión!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/online-pops")
    public Greeting greetingP(@RequestParam(value="nombre", defaultValue="Padre") String nombre) {
        return new Greeting(counter.incrementAndGet(),String.format(estaOnline, nombre));
    }
    
    
}
