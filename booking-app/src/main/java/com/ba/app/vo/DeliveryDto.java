package com.ba.app.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@Value
public class DeliveryDto {

	private BookingVo bookingVo;
	private InventoryVo inventoryVo;

	public DeliveryDto(BookingVo bookingVo, InventoryVo inventoryVo) {
		this.bookingVo = bookingVo;
		this.inventoryVo = inventoryVo;
	}
}
