package spatialindex.rtree;

public class SPoint {
	private double longitude;//����
	private double latitude;//γ��
	public SPoint(double longitude,double latitude) {
		this.longitude=longitude;
		this.latitude=latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
