package com.template.useractivitylog.domains.board.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    @GetMapping
    public String getBoards() {
        return "getBoards";
    }

    @PostMapping
    public String createBoard() {
        return "createBoard";
    }
}