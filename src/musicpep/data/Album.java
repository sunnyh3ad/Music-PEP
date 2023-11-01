package musicpep.data;

public class Album {
	private int id;
	private String album_name;
	private String artist;
	private String genre;
	private int track_count;

	public Album(int id, String album_name, String artist, String genre, int track_count) {
		super();
		this.id = id;
		this.album_name = album_name;
		this.artist = artist;
		this.genre = genre;
		this.track_count = track_count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getTrack_count() {
		return track_count;
	}

	public void setTrack_count(int track_count) {
		this.track_count = track_count;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", album_name=" + album_name + ", artist=" + artist + ", genre=" + genre
				+ ", track_count=" + track_count + "]";
	}

}
