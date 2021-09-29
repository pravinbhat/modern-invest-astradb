package com.datastax.astra.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.datastax.astra.dao.SessionManager;
import com.datastax.astra.entity.ActivityLog;
import com.datastax.astra.service.AstraService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@Api(value = "/api/activities")
@RequestMapping("/api/activities")
public class ActivityLogController {

	/** Logger for the class. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityLogController.class);

	private AstraService astraService;

	public ActivityLogController(AstraService astraService) {
		this.astraService = astraService;
	}

	/**
	 * POST on /api/activities
	 */
	@PostMapping
	@ApiOperation(value = "Save an activity log", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Activity log saved"),
			@ApiResponse(code = 401, message = "Invalid Activity"),
			@ApiResponse(code = 400, message = "Invalid or missing parameters"),
			@ApiResponse(code = 500, message = "Internal error - cannot save activity") })
	public ResponseEntity<String> saveActivity(@RequestBody ActivityLog activityLog) throws IOException {
		if (null != activityLog) {
			activityLog.setUserId(UUID.randomUUID());
			LOGGER.info("Saving activity for user " + activityLog.getUserId());
			// activityLog.setActivityId(UUID.fromString(System.currentTimeMillis() + ""));
			activityLog.setActivityId(UUID.randomUUID());
			astraService.insertActivity(activityLog);
		}
		return ResponseEntity.ok(activityLog.toString());
	}

	/**
	 * Check if system is initialized and connected.
	 */
	@RequestMapping(method = GET)
	@ApiOperation(value = "Status for component", response = String.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "System is connected"),
			@ApiResponse(code = 401, message = "Invalid Credentials or not initialized") })
	public ResponseEntity<String> checkConnection() {
		SessionManager.getInstance().checkConnection();
		LOGGER.info("Session is successfully initialized and connected");
		return ResponseEntity.ok("Connection Successful");
	}

}
