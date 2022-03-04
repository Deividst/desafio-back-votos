package com.github.deividst.votos.stream;

import com.github.deividst.votos.dtos.ResultVoteDto;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(RsultVoteChannel.class)
public class ResultVotePublisher {

    private final RsultVoteChannel resultVoteChannel;

    public ResultVotePublisher(RsultVoteChannel resultVoteChannel) {
        this.resultVoteChannel = resultVoteChannel;
    }

    public void publish(ResultVoteDto resultVoteDto) {
        resultVoteChannel.publishResultVoteChannel().send(MessageBuilder.withPayload(resultVoteDto).build());
    }
}
