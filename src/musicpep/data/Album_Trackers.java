package musicPEP.data;

public class Album_Trackers {
	
	private int album_id;
	private int tracker_id;
	private int completed_tracks;
	
	@Override
	public String toString() {
		return "Album_Trackers [album_id=" + album_id + ", tracker_id=" + tracker_id + ", completed_tracks="
				+ completed_tracks + "]";
	}
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public int getTracker_id() {
		return tracker_id;
	}
	public void setTracker_id(int tracker_id) {
		this.tracker_id = tracker_id;
	}
	public int getCompleted_tracks() {
		return completed_tracks;
	}
	public void setCompleted_tracks(int completed_tracks) {
		this.completed_tracks = completed_tracks;
	}
}
