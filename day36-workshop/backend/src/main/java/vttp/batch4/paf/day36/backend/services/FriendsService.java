package vttp.batch4.paf.day36.backend.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Service;

import vttp.batch4.paf.day36.backend.models.Friend;

@Service
public class FriendsService {

	private Map<String, Friend> friends = new HashMap<>();

	private ReadWriteLock rwLock = new ReentrantReadWriteLock(); 

	public boolean add(Friend friend) {
		Lock writeLock = rwLock.writeLock();
		writeLock.lock();
		try {
			if (friends.containsKey(friend.email().trim()))
				return false;
			friends.put(friend.email().trim(), friend);
			return true;
		} finally {
			writeLock.unlock();
		}
	}

	public List<Friend> getFriends() {
		Lock readLock = rwLock.readLock();
		readLock.lock();
		try {
			return friends.keySet().stream()
				.map(e -> friends.get(e))
				.toList();
		} finally {
			readLock.unlock();
		}
	}

}
