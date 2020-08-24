package com.ibocon.ledger.repository.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserSerivce {
    
    private final UserRepository userRepository;

    @Transactional
    public User getUserById(Long id) {
        var user = userRepository.findById(id)
        .orElseGet(() -> {
            log.warn(id + "에 해당하는 사용자를 찾을 수 없습니다.");
            return null;
        });

        return user;
    }

    @Transactional
    public Boolean isUserExist(Long id) {
        if(getUserById(id) == null) {
            return false;
        } else {
            return true;
        }
    }
}