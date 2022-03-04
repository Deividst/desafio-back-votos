package com.github.deividst.votos.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RsultVoteChannel {

    String RESULT_VOTE_CHANNEL_OUTPUT = "resultVoteChannelOutput";
    String RESULT_VOTE_CHANNEL_IN = "resultVoteChannelInput";

    @Output(RESULT_VOTE_CHANNEL_OUTPUT)
    MessageChannel publishResultVoteChannel();

    @Input(RESULT_VOTE_CHANNEL_IN)
    MessageChannel receiveSubscription();

}
