# How to install ELK ? 

## Prerequisite 
- JDK (Used 1.8.0_161 here)
- Linux (CentOS 7 used here)


http://devopspy.com/devops/install-elk-stack-centos-7-logs-analytics/

https://www.elastic.co/guide/en/beats/filebeat/current/regexp-support.html
https://www.elastic.co/guide/en/beats/filebeat/current/multiline-examples.html

### filebeat.yml (/etc/filebeat/filebeat.yml)
```
filebeat.prospectors:
- document_type: tomcat_log
  paths:
  - /opt/sitakant/apache-tomcat-9.0.7/logs/application.log
  multiline.pattern: '[\w]{8}(-[\w]{4}){3}-[\w]{12}]'
  multiline.negate: true
  multiline.match: after

output.elasticsearch:
  hosts: ['http://localhost:9200']
logstash:
  hosts: ["localhost:5044"]
  bulk_max_size: 1024
  certificate_authorities: ["/etc/pki/tls/certs/logstash-forwarder.crt"]
files:
  rotateeverybytes: 10485760 # = 10MB

```


### logstash and filebeat connector configuration (/etc/logstash/conf.d/logstash_tomcat.conf)
```
input {
	beats {
		type => "tomcat_log"
		host => "localhost"
		port => 5044
	}
}

filter {
	if [type] == "tomcat_log" {
		grok {
			patterns_dir => "/etc/logstash/patterns"
			match =>  { "message" => "%{TIMESTAMP_ISO8601:timestamp} %{DATA:transactionId} %{DATA:thread} *%{LOGLEVEL:level} *%{GREEDYDATA:class} - %{GREEDYDATA:message}" }
		}
		date {
			match => [ "timestamp", "yyyy-MM-dd HH:mm:ss,SSS" ]
			remove_field => [ "timestamp" ]
		}
	}
}

output {
	stdout { codec => json  }
	if [type] == "tomcat_log" {
		elasticsearch {
			manage_template => false
			hosts => ["localhost:9200"]
			index => "tomcat"
		}
	}
}

```
