package com.the.insomniacsdream.controller;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.the.insomniacsdream.model.Album;
import com.the.insomniacsdream.service.AlbumService;

@Component
@Path("/albums")
public class AlbumController {
	
	@Autowired
	AlbumService as;
	//API Endpoint to get a list of all films in the database
	@GET
	@Produces("application/json")
	public Collection<Album> albums() {
		return as.getAllAlbums();
	}
	
	//API endpoint to get a specific album by id
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Album getAlbum(@PathParam("id") String id) {
		return as.getAlbum(id);
	}
	
	//API endpoint to add a new album to database
	@POST
	@Consumes("application/json")
	public Response add(Album album, @Context UriInfo info) {
		as.addAlbum(album);
		return Response.created(URI.create(info.getAbsolutePath().toString()+"/"+album.getId())).build();
	}
	
	//API endpoint to modify an album
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Album update(@PathParam("id") String id, Album album) {
		as.updateAlbum(id, album);
		return album;
	}
	
	//API endpoint to delete an album
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) {
		as.removeAlbum(id);
		return Response.ok(id).build();
	}
}
