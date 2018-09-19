# 意图识别

## C3服务器工程结构
### 147 155 156 161 162

|工程名 | 端口 |服务器 |说明 |
|---|---|---|---|
| api-utils | - | - | Rest工具类 |
| apis | - | - |URL定义类 |
| common-bean | - | - | bean类 |
| admin-s-server| 8888 | 147 | 项目配置后台 |
| config-server | 9999 | 147 | 配置服务器 |
| docker-client |8887|147|镜像服务|
| api-gateway | 9000-9009 | 155+156+157+161+162 | API网关 |
| rule-server | 9010-9059 | 155+156+157+161+162 | 规则服务器 |
| model-server | 9060-9079 | 155+156+157+161+162 | 模型Java服务器 |
| api-token | 9080-9089 | 155+156+157+161+162 | 分词服务 |
| rule-server | 9058 | 162 | **正式规则批量服务器** |
| allocate-server | 9100 | 145 | 分配服务器 |
## 外呼服务器工程结构

|工程名 | 端口 | 服务器|说明 |
|---|---|---|---|
| voice-server | 9090-9099,8800-8810 | 155+156+157+161+162 | 语音处理服务器 |

## 其他服务
|工程名 | 端口 | 服务器|说明 |
|---|---|---|---|
| ELK | *** | 147 | 日志服务 |
| Kafka | 9092 | 147 | 消息队列 |
| Harbor | 7777 | 147 | docker hub |
| Nginx File | 6666 | 147 | 文件服务 |

## 测试服配置 169

|工程名 | 端口 |服务器 |说明 |
|---|---|---|---|
| admin-s-server| 8886 | 169 | 项目配置后台 |
| config-server | 9998 | 169 | 配置服务器 |
| docker-client | 8885 | 169| docker打包服务器 |
| model-server | 9075-9079 | 169 | 模型Java服务器 |
| api-gateway | 9009 | 169 | API网关 |
| rule-server | 9059,9057,9056,9055 | 169 | 规则服务器 |
| voice-server | 9098-9099 | 169| 语音处理服务器 |
| rule-server | 9058 | 169 | **测试规则批量服务器** |

## 数据库配置

| 模块 | 分支 | 地址 |
|---|---|---|
| mysql| 测试 | 192.168.2.135：33161  |
| mysql| 正式 | 192.168.2.208：33161  |
| redis| 测试 | 192.168.2.135：6379  |
| redis| 正式 | 192.168.2.104：6379  |



## 版本描述
|版本 | 描述 |切出时间|
|---|---|---|
|master | Developing+测试|-|
|1.0.0.RC|云平台正式联测第一版|2018.08.05|
|1.1.0.RC|云平台正式联测第二版:万能话术|2018.08.10|
|1.2.0.RC|云平台正式联测第三版:allocate+puqiang 500|2018.09.06|

