package com.interview.simpledb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleDBImpl implements SimpleDB {
	int size = 0;
	List<Map<String, Integer>> storage = new ArrayList<>();
	public SimpleDBImpl() {
		this.storage.add(new HashMap<>());
	}
	

	@Override
	public void Set(String key, Integer value) {
		//int size = this.storage.size();
		this.storage.get(size).put(key, value);
	}

	@Override
	public Integer Get(String key) {
		//int size = this.storage.size();
		return this.storage.get(size).get(key);
	}

	@Override
	public void Unset(String key) {
		int size = this.storage.size();
		this.storage.get(size-1).remove(key);
	}

	@Override
	public void Begin() {
		//int size = this.storage.size();
		Map<String, Integer> top = this.storage.get(size);
		Map<String, Integer> copy = new HashMap<>(top);
		this.storage.add(copy);
		this.size++;
	}

	@Override
	public void Commit() throws Exception {
		//int size = this.storage.size();
		if(size <1) {
			throw new IllegalStateException("Error");
		}
		
		Map<String, Integer> top = this.storage.get(size);
		this.storage.set(0, top);
		this.size = 0;
	}

	@Override
	public void Rollback() throws Exception {
		if(size <1) {
			throw new IllegalStateException("Error");
		}
		Map<String, Integer> top = this.storage.get(size);
		this.storage.set(0, top);
		this.size--;
	}

}
