package com.java1996;
/**
 * QueryPiont���û����Բ�ѯ���������Ϣ
 * @author 17699_000
 *
 */
public class QueryPoint extends Point{
	private int id;
	public QueryPoint(int id,float longitude,float latitude) {
		super(longitude, latitude);
		this.id=id;
	}
	public int getId() {
		return id;
	}
	
}
