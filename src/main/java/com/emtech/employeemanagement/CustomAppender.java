package com.emtech.employeemanagement;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CustomAppender extends AppenderBase<ILoggingEvent> {
    List<ILoggingEvent> logs = new ArrayList<>();

    @Override
    protected void append(ILoggingEvent event){
        logs.add(event);
    }

}
