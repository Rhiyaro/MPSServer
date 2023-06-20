package com.mps.MPSServer.service;

import com.mps.MPSServer.CustomExceptions.ObjectNotFoundInDBException;
import com.mps.MPSServer.MPSUtil.DatetimeManager;
import com.mps.MPSServer.domain.Channel;
import com.mps.MPSServer.domain.Measure;
import com.mps.MPSServer.domain.Panel;
import com.mps.MPSServer.repo.ChannelRepo;
import com.mps.MPSServer.repo.MeasureRepo;
import com.mps.MPSServer.repo.PanelRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasureService {

    @Autowired
    private MeasureRepo measureRepo;

    @Autowired
    private ChannelRepo channelRepo;

    @Autowired
    private PanelRepo panelRepo;

    public void insertMeasurement(String panelName,
                                  String channelName,
                                  float value) throws ObjectNotFoundInDBException {
        Optional<Panel> optionalPanel = panelRepo.findPanelByPanelName(panelName);
        if(optionalPanel.isEmpty()) {
            throw new ObjectNotFoundInDBException("Panel " + panelName + " not found in DB");
        }

        Optional<Channel> optionalChannel = channelRepo.findChannelByChannelName(channelName);
        if(optionalChannel.isEmpty()) {
            throw new ObjectNotFoundInDBException("Channel " + channelName + " not found in DB");
        }

        Measure measure = new Measure(new Date(), optionalPanel.get(), optionalChannel.get(), value);

        measureRepo.save(measure);
    }

    public void insertMeasurement(String panelName,
                                  String channelName,
                                  float value,
                                  String ts) throws ObjectNotFoundInDBException, ParseException {
        Optional<Panel> optionalPanel = panelRepo.findPanelByPanelName(panelName);
        if(optionalPanel.isEmpty()) {
            throw new ObjectNotFoundInDBException("Panel " + panelName + " not found in DB");
        }

        Optional<Channel> optionalChannel = channelRepo.findChannelByChannelName(channelName);
        if(optionalChannel.isEmpty()) {
            throw new ObjectNotFoundInDBException("Channel " + channelName + " not found in DB");
        }

        ts = ts.replace("_", " ");

        Measure measure = new Measure(DatetimeManager.df.parse(ts), optionalPanel.get(), optionalChannel.get(), value);

        measureRepo.save(measure);
    }

}
