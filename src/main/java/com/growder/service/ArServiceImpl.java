package com.growder.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.growder.binding.CitizenApp;
import com.growder.entity.CitizenAppEntity;
import com.growder.repository.CitizenAppRepository;

@Service
public class ArServiceImpl implements ArService {

	@Autowired
	private CitizenAppRepository appRepo;

	@Override
	public Integer createApplication(CitizenApp app) {

		// make rest call to ssa-web api with ssn as input

		String endpointUrl = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";

		RestTemplate rt = new RestTemplate();
		ResponseEntity<String> resEntity = rt.getForEntity(endpointUrl, String.class, app.getSsn());

		String stateName = resEntity.getBody();

		if ("New Jersey".equals(stateName)) {
			// create application
			CitizenAppEntity entity = new CitizenAppEntity();
			BeanUtils.copyProperties(app, entity);

			entity.setStateName(stateName);

			CitizenAppEntity save = appRepo.save(entity);

			return save.getAppId();
		}
		return 0;
	}

}
