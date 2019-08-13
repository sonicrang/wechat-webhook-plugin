package io.jenkins.plugins.wechat.model;

public class weChatData {

    String msgtype = "markdown";
    Text markdown;

    public String getMsgtype() {
        return msgtype;
    }

    public Text getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String content) {
        Text markdown = new Text();
        markdown.content = content;
        this.markdown = markdown;
    }
}

class Text {
    String content;
}