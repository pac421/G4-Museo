# G4-Museo

# CRUD Example 

DAO<Artist> artistDAO = new DAOFactory().getArtistDAO();

// CREATE
Artist artist = new Artist("Solange", "Baron", "1988-");
System.out.println(artistDAO.create(artist));

// FIND
Artist artist = artistDAO.find("448c6cec-243a-48e7-8c93-8460c7ee18df");
System.out.println(artist);

// UPDATE
artist.setFirstname("Rini");
Artist updatedArtist = artistDAO.update(artist);
System.out.println(updatedArtist);

// DELETE
artistDAO.delete(artist);