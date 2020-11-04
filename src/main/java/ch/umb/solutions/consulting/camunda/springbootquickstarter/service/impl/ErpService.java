package ch.umb.solutions.consulting.camunda.springbootquickstarter.service.impl;

import ch.umb.solutions.consulting.camunda.springbootquickstarter.service.IErpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ErpService implements IErpService {


    final static Logger logger = LoggerFactory.getLogger(ErpService.class);

    @Override
    public void updateErp(String variable) {
        logger.debug("update erp: {}", variable);

    }
}
