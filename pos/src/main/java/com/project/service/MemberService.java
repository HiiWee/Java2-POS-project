package com.project.service;

import com.project.repository.MemberRepository;

public class MemberService {
    MemberRepository memberRepository=MemberRepository.getInstance();

    public String getPassword(){
        return memberRepository.findPasswordByAuth();
    }
}
