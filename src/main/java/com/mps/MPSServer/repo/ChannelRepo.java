package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {

    Optional<Channel> findChannelByChannelName(String channelName);
}
