package com.xuexi.v2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuexi.v2.mapper.RightMapper;
import com.xuexi.v2.service.IRightService;

@Service
public class RightServiceImpl implements IRightService {

	@Autowired
	private RightMapper rightMapper;
}
