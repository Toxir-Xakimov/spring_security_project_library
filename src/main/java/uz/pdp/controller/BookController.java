package uz.pdp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/book")
@Controller
@ResponseBody
public class BookController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String createBookPage() {
        return "Welcome to create book page";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/my_books")
    public String myBooksPage() {
        return "Welcome to ur books";
    }

}
