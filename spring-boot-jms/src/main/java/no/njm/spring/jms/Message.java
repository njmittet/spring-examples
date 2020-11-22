package no.njm.spring.jms;

import com.google.common.base.MoreObjects;

public final class Message {

    private String to;
    private String body;

    public Message() {
        // Default constructor required by com.fasterxml.jackson.
    }

    Message(String to, String body) {
        this.to = to;
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("to", to)
                .add("body", body)
                .toString();
    }
}
