package com.template.useractivitylog.domains.member.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @PostMapping
    public String signup() {
        return "signup";
    }

    @GetMapping
    public String getMembers() {
        return "getMembers";
    }

}