package com.app.beans;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomResponseBean {

	private Long roomId;

	private String roomName;

	private String description;

	private boolean active;

	private Boolean ownerChecked;

	private Boolean userChecked;

	private Boolean faultOwner;

	private Boolean faultUser;
 
}
