package com.cts.vehicle.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class VehicleMakeDetails {

	@Id
	private Integer vehicleId;
	private String make;
	private String model;
	private String variant;
	private String idv;
	public VehicleMakeDetails() {
		super();
	}
	public VehicleMakeDetails(Integer vehicleId, String make, String model, String variant, String idv) {
		super();
		this.vehicleId = vehicleId;
		this.make = make;
		this.model = model;
		this.variant = variant;
		this.idv = idv;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVariant() {
		return variant;
	}
	public void setVariant(String variant) {
		this.variant = variant;
	}
	public String getIdv() {
		return idv;
	}
	public void setIdv(String idv) {
		this.idv = idv;
	}
	@Override
	public String toString() {
		return "VehicleMakeDetails [vehicleId=" + vehicleId + ", make=" + make + ", model=" + model + ", variant="
				+ variant + ", idv=" + idv + "]";
	}
	
	
}
