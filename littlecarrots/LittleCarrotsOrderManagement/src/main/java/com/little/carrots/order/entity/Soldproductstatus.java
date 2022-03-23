package com.little.carrots.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="soldproductstatus")
public class Soldproductstatus implements Serializable{
	

//	create table saleproductstatus(
//		status varchar(100),
//		constraint saleproductstatus_pk primary key (status)
//	);

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5944538899515245536L;
	@Id
	@Column(name = "status")
	private String status;

	public Soldproductstatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

// TODO Remove unused code found by UCDetector
// 	public Soldproductstatus(String status) {
// 		super();
// 		this.status = status;
// 	}

	@Override
	public String toString() {
		return "Saleproductstatus [status=" + status + "]";
	}
	
	

}
