Here are the full commands SREs use most frequently on a daily basis:

**Kubernetes (kubectl)**
```bash
# Check pod status
kubectl get pods -n production
kubectl get pods --all-namespaces
kubectl get pods -o wide

# Detailed pod information
kubectl describe pod <pod-name> -n production
kubectl describe node <node-name>

# View logs
kubectl logs <pod-name> -n production
kubectl logs <pod-name> -c <container-name> -n production
kubectl logs -f <pod-name> -n production  # follow logs
kubectl logs <pod-name> --previous  # logs from previous container

# Execute commands in pods
kubectl exec -it <pod-name> -n production -- /bin/bash
kubectl exec -it <pod-name> -n production -- sh

# Resource management
kubectl top nodes
kubectl top pods -n production
kubectl get deployments -n production
kubectl get services -n production
kubectl get ingress -n production

# Rollout management
kubectl rollout status deployment/<deployment-name> -n production
kubectl rollout history deployment/<deployment-name> -n production
kubectl rollout undo deployment/<deployment-name> -n production
kubectl rollout restart deployment/<deployment-name> -n production

# Scaling
kubectl scale deployment/<deployment-name> --replicas=5 -n production

# Apply configurations
kubectl apply -f deployment.yaml
kubectl delete -f deployment.yaml

# Port forwarding for debugging
kubectl port-forward <pod-name> 8080:80 -n production

# Get events
kubectl get events -n production --sort-by='.lastTimestamp'

# Resource usage
kubectl describe quota -n production
kubectl describe limits -n production
```

**Docker**
```bash
# List containers
docker ps
docker ps -a  # include stopped containers

# View logs
docker logs <container-id>
docker logs -f <container-id>  # follow logs
docker logs --tail 100 <container-id>

# Execute commands
docker exec -it <container-id> bash
docker exec -it <container-id> sh

# Container management
docker stop <container-id>
docker start <container-id>
docker restart <container-id>
docker rm <container-id>

# Image management
docker images
docker pull <image-name>:<tag>
docker build -t <image-name>:<tag> .
docker push <image-name>:<tag>
docker rmi <image-id>

# System information
docker stats
docker system df
docker system prune -a  # cleanup

# Inspect
docker inspect <container-id>
docker inspect <image-id>

# Docker Compose
docker-compose up -d
docker-compose down
docker-compose logs -f
docker-compose ps
docker-compose restart
```

**Linux system monitoring**
```bash
# Process monitoring
top
htop
ps aux | grep <process-name>
ps -ef | grep <process-name>

# Memory usage
free -h
free -m
vmstat 1 10  # every 1 second, 10 times
cat /proc/meminfo

# Disk usage
df -h
df -i  # inodes
du -sh /var/log/*
du -h --max-depth=1 /

# I/O statistics
iostat -x 1 10
iotop

# System activity
sar -u 1 10  # CPU usage
sar -r 1 10  # memory
sar -n DEV 1 10  # network

# Kernel messages
dmesg | tail
dmesg -T  # human-readable timestamps

# Open files and connections
lsof -i :80  # what's using port 80
lsof -p <pid>  # files opened by process
lsof -u <username>  # files opened by user

# System load
uptime
w
```

**Network troubleshooting**
```bash
# Connectivity testing
ping -c 4 google.com
ping6 -c 4 google.com

# DNS resolution
dig google.com
dig @8.8.8.8 google.com
nslookup google.com
host google.com

# Route tracing
traceroute google.com
mtr google.com  # better than traceroute

# Network connections
netstat -tuln  # listening ports
netstat -tanp  # all connections
ss -tuln  # modern alternative
ss -s  # statistics

# Packet capture
tcpdump -i eth0
tcpdump -i eth0 port 80
tcpdump -i eth0 -w capture.pcap
tcpdump -r capture.pcap

# HTTP testing
curl -I https://example.com  # headers only
curl -v https://example.com  # verbose
curl -X POST -H "Content-Type: application/json" -d '{"key":"value"}' https://api.example.com
curl -o output.txt https://example.com
curl -w "@curl-format.txt" -o /dev/null -s https://example.com  # timing

# Firewall
iptables -L -n -v
iptables -L INPUT -n -v

# Network interfaces
ifconfig
ip addr show
ip link show
ip route show

# Bandwidth monitoring
iftop
nethogs
```

**Log analysis**
```bash
# Tail logs
tail -f /var/log/syslog
tail -n 100 /var/log/application.log
tail -f /var/log/nginx/access.log

# Search logs
grep "ERROR" /var/log/application.log
grep -r "ERROR" /var/log/
grep -i "error" /var/log/application.log  # case insensitive
grep -v "INFO" /var/log/application.log  # exclude

# Advanced search with context
grep -A 5 "ERROR" /var/log/application.log  # 5 lines after
grep -B 5 "ERROR" /var/log/application.log  # 5 lines before
grep -C 5 "ERROR" /var/log/application.log  # 5 lines around

# Count occurrences
grep -c "ERROR" /var/log/application.log

# Log rotation and compression
zcat /var/log/application.log.1.gz | grep "ERROR"
zgrep "ERROR" /var/log/application.log.*.gz

# Using awk
awk '{print $1}' /var/log/nginx/access.log | sort | uniq -c | sort -rn  # top IPs
awk '$9 == 500' /var/log/nginx/access.log  # 500 errors

# Using sed
sed -n '100,200p' /var/log/application.log  # lines 100-200

# Journal logs (systemd)
journalctl -u nginx.service
journalctl -u nginx.service -f  # follow
journalctl -u nginx.service --since "1 hour ago"
journalctl -u nginx.service --since "2024-01-01" --until "2024-01-02"
journalctl -p err  # errors only
```

**Git**
```bash
# Basic operations
git status
git pull origin main
git push origin main
git fetch origin

# Branching
git branch
git checkout -b feature-branch
git checkout main
git merge feature-branch

# Viewing history
git log --oneline
git log --graph --oneline --all
git log -p  # with diffs
git log --since="2 weeks ago"

# Rollback and revert
git revert <commit-hash>
git reset --hard HEAD~1
git reset --soft HEAD~1
git checkout -- <file>  # discard changes

# Stashing
git stash
git stash list
git stash pop
git stash apply

# Cherry-pick
git cherry-pick <commit-hash>

# Tags
git tag
git tag v1.0.0
git push origin v1.0.0

# Remote management
git remote -v
git remote add upstream <url>
```

**AWS CLI**
```bash
# EC2
aws ec2 describe-instances
aws ec2 describe-instances --filters "Name=tag:Environment,Values=production"
aws ec2 start-instances --instance-ids i-1234567890abcdef0
aws ec2 stop-instances --instance-ids i-1234567890abcdef0

# S3
aws s3 ls
aws s3 ls s3://my-bucket/
aws s3 cp file.txt s3://my-bucket/
aws s3 sync /local/dir s3://my-bucket/
aws s3 rm s3://my-bucket/file.txt

# CloudWatch Logs
aws logs tail /aws/lambda/my-function --follow
aws logs filter-log-events --log-group-name /aws/lambda/my-function --filter-pattern "ERROR"

# ECS
aws ecs list-clusters
aws ecs list-services --cluster my-cluster
aws ecs describe-services --cluster my-cluster --services my-service

# CloudFormation
aws cloudformation describe-stacks
aws cloudformation create-stack --stack-name my-stack --template-body file://template.yaml

# Systems Manager
aws ssm start-session --target i-1234567890abcdef0
aws ssm get-parameter --name /prod/db/password --with-decryption
```

**GCP (gcloud)**
```bash
# Compute Engine
gcloud compute instances list
gcloud compute instances describe <instance-name>
gcloud compute instances start <instance-name>
gcloud compute instances stop <instance-name>

# GKE
gcloud container clusters list
gcloud container clusters get-credentials <cluster-name> --zone <zone>

# Logging
gcloud logging read "resource.type=gce_instance AND severity>=ERROR" --limit 50
gcloud logging tail "resource.type=k8s_container"

# Projects
gcloud projects list
gcloud config set project <project-id>

# IAM
gcloud iam service-accounts list
gcloud iam roles list
```

**Terraform**
```bash
# Initialize
terraform init

# Plan changes
terraform plan
terraform plan -out=tfplan

# Apply changes
terraform apply
terraform apply tfplan
terraform apply -auto-approve

# Destroy resources
terraform destroy
terraform destroy -auto-approve

# State management
terraform state list
terraform state show <resource>
terraform state rm <resource>
terraform state pull

# Workspace management
terraform workspace list
terraform workspace new production
terraform workspace select production

# Formatting and validation
terraform fmt
terraform validate

# Import existing resources
terraform import <resource-type>.<name> <id>
```

**Prometheus queries (PromQL)**
```bash
# Used in Prometheus UI or Grafana

# CPU usage
100 - (avg by (instance) (rate(node_cpu_seconds_total{mode="idle"}[5m])) * 100)

# Memory usage
(node_memory_MemTotal_bytes - node_memory_MemAvailable_bytes) / node_memory_MemTotal_bytes * 100

# HTTP request rate
rate(http_requests_total[5m])

# Error rate
rate(http_requests_total{status=~"5.."}[5m]) / rate(http_requests_total[5m])

# Request latency (95th percentile)
histogram_quantile(0.95, rate(http_request_duration_seconds_bucket[5m]))

# Pod restarts
rate(kube_pod_container_status_restarts_total[15m])
```

**Service management (systemd)**
```bash
# Service status
systemctl status nginx
systemctl status nginx.service

# Start/stop/restart
systemctl start nginx
systemctl stop nginx
systemctl restart nginx
systemctl reload nginx

# Enable/disable at boot
systemctl enable nginx
systemctl disable nginx

# View logs
journalctl -u nginx -f
journalctl -u nginx --since "1 hour ago"
journalctl -u nginx -n 100

# List all services
systemctl list-units --type=service
systemctl list-units --type=service --state=running
```

**Performance profiling**
```bash
# CPU profiling
perf top
perf record -p <pid>
perf report

# Strace (system calls)
strace -p <pid>
strace -c -p <pid>  # count calls
strace -e trace=open,read,write -p <pid>

# Load testing
ab -n 1000 -c 10 http://example.com/  # Apache Bench
```

**Database commands**
```bash
# PostgreSQL
psql -h localhost -U postgres -d mydb
\l  # list databases
\dt  # list tables
\d table_name  # describe table
SELECT * FROM pg_stat_activity;  # active connections
SELECT pg_size_pretty(pg_database_size('mydb'));  # database size

# MySQL
mysql -h localhost -u root -p
SHOW DATABASES;
SHOW TABLES;
DESCRIBE table_name;
SHOW PROCESSLIST;
SELECT table_schema, SUM(data_length + index_length) / 1024 / 1024 AS size_mb FROM information_schema.tables GROUP BY table_schema;

# Redis
redis-cli
INFO
KEYS *
GET key_name
MONITOR  # watch commands in real-time
```

These commands form the core toolkit that SREs use throughout the day for monitoring, troubleshooting, deployment, and maintaining system reliability.
