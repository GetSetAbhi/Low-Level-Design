package com.demo.evlevtor;

import com.demo.evlevtor.state.DownState;
import com.demo.evlevtor.state.ElevatorState;
import com.demo.evlevtor.state.IdleState;
import com.demo.evlevtor.state.Upstate;

public class ElevatorStateContext {

	private ElevatorState upstate;
	private ElevatorState downState;
	private ElevatorState idlestate;
	
	public ElevatorStateContext() {
		this.upstate = new Upstate();
		this.downState = new DownState();
		this.idlestate = new IdleState();
	}

	public ElevatorState getUpstate() {
		return upstate;
	}

	public ElevatorState getDownState() {
		return downState;
	}

	public ElevatorState getIdlestate() {
		return idlestate;
	}
	
	
}
