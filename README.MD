# Jenkins 企业微信Webhook插件

利用企业微信群组WebHook，通过Jenkins向企业微信发送Markdown格式信息

区别[“Jenkins企业微信通知插件”](https://github.com/sonicrang/wechat-notification-plugin)

**使用前请先阅读：**

[企业微信基本概念介绍](https://work.weixin.qq.com/api/doc#90000/90135/90665)

**准备：**

- 企业微信群组-右键-添加群机器人（群组管理员）
- 群组右下角进入机器人
  - 查询webhook url
  - 查询相关文档
  
  ![](../master/static/guide01.png)

**安装：**

- Jenkins-Plugin Manager-Advanced-Upload Plugin
- 上传target目录的wechat-webhook-plugin.hpi

**使用：**

- FreeStyle

  ![](../master/static/guide02.png)

- Pipeline

  ```groovy
  node {
     wechatWebhook url:"webhook地址",content:"消息内容"
  }
  ```

