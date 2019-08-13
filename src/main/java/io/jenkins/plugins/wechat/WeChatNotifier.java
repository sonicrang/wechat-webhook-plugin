package io.jenkins.plugins.wechat;

import hudson.Launcher;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;

import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;

import jenkins.tasks.SimpleBuildStep;
import org.jenkinsci.Symbol;

public class WeChatNotifier extends Notifier implements SimpleBuildStep {

    private final String url;
    private final String content;

    @DataBoundConstructor
    public WeChatNotifier(String url, String content) {
        this.url = url;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener)
            throws InterruptedException, IOException {
        WeChatMessage sw = new WeChatMessage();
        try {
            String postdata = sw.createpostdata(content);
            String resp = sw.post(url, postdata);
            listener.getLogger().println("resp:" + resp);

        } catch (Exception e) {
            e.printStackTrace();
            listener.getLogger().println("err:" + e.getMessage());
        }
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Symbol("wechatWebhook")
    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "WeChat Webhook Plugin";
        }
    }
}
