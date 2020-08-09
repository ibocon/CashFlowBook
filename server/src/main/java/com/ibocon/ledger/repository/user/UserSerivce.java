package com.ibocon.ledger.repository.user;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSerivce {
    
    private final UserRepository userRepository;

    @Transactional
    public User getUserById(Long id) throws NotFoundException {
        var user = userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException(id + "에 해당하는 사용자를 찾을 수 없습니다."));

        return user;
    }
}