package com.the.insomniacsdream.service;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.the.insomniacsdream.model.Album;

@Service
public class AlbumService {

	private final ConcurrentMap<String, Album> db;
	
	
	public AlbumService() {
		this.db = new ConcurrentHashMap<>();
	}
	
	//Get all the albums stored in the database
	
	public Collection<Album> getAllAlbums(){
		Collection<Album> all = this.db.values();
		if(all.isEmpty()) {
			return Collections.emptyList();
		} else {
			return all;
		}
	}	
	
	//Add an album to database
	
	public void addAlbum(Album a) {
		if(a.getId() == null) {
			a.setId(String.valueOf(this.db.size()+1));
		}
		this.db.put(a.getId(), a);
	}
	
	//Get an album by id

	
	public Album getAlbum(String id) {
		return this.db.get(id);
	}
	
	//Modify an album attributes

	public Album updateAlbum(String id, Album a) {
		if(!this.db.containsKey(id)) {
			throw new IllegalArgumentException("Invalid Album or Album does not exist!");
		}
		if((a.getId() == null) || (id != a.getId())) {
			a.setId(id);
		}
		return this.db.put(a.getId(), a);
	}
	
	//Delete an album from database
	
	public void removeAlbum(String id) {
		if(!this.db.containsKey(id)) {
			throw new IllegalArgumentException("Invalid Album or Album does not exist!");	
		}
		this.db.remove(id);
	}
}

