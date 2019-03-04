package com.serviceinfotech.schedulers.quartzschedulers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleJobService {

    private static Logger LOG = LoggerFactory
            .getLogger(SimpleJobService.class);

    public void printAndEmail() {

        LOG.info("*** Printing and send Email ***");

    }
}
