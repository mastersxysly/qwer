package com.java1996;

import java.awt.FlowLayout;

/**
 * ����
 * @author 17699_000
 *
 */
public class Point {
	private float longitude;//����
	private float latitude;//γ��
	public Point(float longitude,float latitude) {
		this.longitude=longitude;
		this.latitude=latitude;
	}
	
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
}
