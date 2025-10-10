package com.demo.alerting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MonitoringService {

	public Map<String, Machine> machineMap;
	private SensorEngine sensorEngine;
	private NotificationService notificationService;
	private ScheduledExecutorService executorService;

	public MonitoringService(SensorEngine sensorEngine, NotificationService notificationService) {
		this.machineMap = new HashMap<>();
		this.sensorEngine = sensorEngine;
		this.notificationService = notificationService;
		this.executorService = Executors.newSingleThreadScheduledExecutor();
	}

	public void registerMachine(Machine machine) {
		this.machineMap.put(machine.getName(), machine);
	}

	public void checkMachines() {
		List<AlertEvent> events = sensorEngine
				.getAlertsForMachines(this.machineMap.entrySet().stream().map(e -> e.getValue()).toList());
		for (AlertEvent e : events) {
			notificationService.sendAlert(e);
		}
	}
	
	public void start() {
		this.executorService.scheduleAtFixedRate(() -> checkMachines(), 0, 5, TimeUnit.SECONDS);
	}
}
