package com.br.cvc.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Checkin {
	
	
	 
	private Long cityCode; 
	
	private String checkin;

    private String checkout;
	 
    private Long qtd_adult;
	 
    private Long qtd_child;

	public Long getCityCode() {
		return cityCode;
	}

	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public Long getQtd_adult() {
		return qtd_adult;
	}

	public void setQtd_adult(Long qtd_adult) {
		this.qtd_adult = qtd_adult;
	}

	public Long getQtd_child() {
		return qtd_child;
	}

	public void setQtd_child(Long qtd_child) {
		this.qtd_child = qtd_child;
	}

	@Override
	public String toString() {
		return "Checkin [cityCode=" + cityCode + ", checkin=" + checkin + ", checkout=" + checkout + ", qtd_adult="
				+ qtd_adult + ", qtd_child=" + qtd_child + "]";
	}
}
