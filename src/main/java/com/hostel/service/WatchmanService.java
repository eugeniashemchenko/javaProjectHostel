package com.hostel.service;

import static org.apache.commons.codec.digest.DigestUtils.sha3_256Hex;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostel.dao.WatchmanDao;
import com.hostel.model.Watchman;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WatchmanService {
    private final WatchmanDao watchmanDao;

    public Watchman findCurrentWatchman(Watchman watchman) {
        Watchman result = watchmanDao.findByUsername(watchman.getUsername());
        if (result == null) {
            return null;
        }
        if (!result.getPassword()
                .equals(sha3_256Hex(watchman.getPassword()))) {
            return null;
        }
        return result;
    }
}
