Here are the full commands DevOps engineers use daily, with key differences from SRE highlighted:

**Kubernetes (kubectl)** - Same commands as SRE, but with more emphasis on deployment and CI/CD integration:
```bash
# All standard kubectl commands (same as SRE)
kubectl get pods -n production
kubectl logs -f <pod-name> -n production
kubectl exec -it <pod-name> -- /bin/bash

# DevOps-specific focus on deployments
kubectl set image deployment/<deployment-name> <container>=<new-image>:<tag> -n production
kubectl patch deployment <deployment-name> -p '{"spec":{"replicas":3}}' -n production
kubectl create configmap <name> --from-file=config.yaml -n production
kubectl create secret generic <name> --from-literal=password=mypass -n production

# Helm (more common in DevOps than SRE)
helm install <release-name> <chart-name>
helm upgrade <release-name> <chart-name>
helm rollback <release-name> <revision>
helm list -n production
helm repo add <repo-name> <repo-url>
helm repo update
helm template <chart-name> --debug
```

**Docker** - Same core commands, but DevOps focuses more on building and pushing images:
```bash
# Building images (more frequent in DevOps)
docker build -t myapp:v1.0 .
docker build -t myapp:v1.0 -f Dockerfile.prod .
docker build --no-cache -t myapp:v1.0 .
docker tag myapp:v1.0 registry.example.com/myapp:v1.0
docker push registry.example.com/myapp:v1.0

# Multi-stage builds
docker build --target production -t myapp:v1.0 .

# Docker registry operations
docker login registry.example.com
docker logout

# All standard docker commands (same as SRE)
docker ps
docker logs -f <container-id>
docker exec -it <container-id> bash
```

**CI/CD Pipeline commands** - This is a MAJOR difference; DevOps spends much more time here:

**Jenkins**
```bash
# Jenkins CLI
java -jar jenkins-cli.jar -s http://jenkins.example.com/ build <job-name>
java -jar jenkins-cli.jar -s http://jenkins.example.com/ console <job-name> <build-number>
java -jar jenkins-cli.jar -s http://jenkins.example.com/ list-jobs

# Jenkinsfile (Groovy) - DevOps writes these regularly
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'npm install'
                sh 'npm run build'
            }
        }
        stage('Test') {
            steps {
                sh 'npm test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'kubectl apply -f k8s/'
            }
        }
    }
}
```

**GitLab CI**
```bash
# .gitlab-ci.yml - DevOps engineers write and maintain these
stages:
  - build
  - test
  - deploy

build:
  stage: build
  script:
    - docker build -t $CI_REGISTRY_IMAGE:$CI_COMMIT_SHA .
    - docker push $CI_REGISTRY_IMAGE:$CI_COMMIT_SHA

deploy:
  stage: deploy
  script:
    - kubectl set image deployment/myapp myapp=$CI_REGISTRY_IMAGE:$CI_COMMIT_SHA
  only:
    - main
```

**GitHub Actions**
```bash
# .github/workflows/deploy.yml - DevOps creates these workflows
name: Deploy
on:
  push:
    branches: [main]
jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Build and push
        run: |
          docker build -t myapp:${{ github.sha }} .
          docker push myapp:${{ github.sha }}
```

**Terraform** - Same commands as SRE, but DevOps uses more frequently for provisioning:
```bash
# All standard terraform commands (same as SRE)
terraform init
terraform plan
terraform apply
terraform destroy

# DevOps often works with modules more
terraform init -upgrade
terraform get
terraform providers

# Backend configuration
terraform init -backend-config="bucket=my-terraform-state"

# Variable management
terraform apply -var="environment=production"
terraform apply -var-file="production.tfvars"
```

**Ansible** - More common in DevOps than SRE:
```bash
# Playbook execution
ansible-playbook -i inventory.ini playbook.yml
ansible-playbook -i inventory.ini playbook.yml --limit webservers
ansible-playbook -i inventory.ini playbook.yml --tags deploy
ansible-playbook -i inventory.ini playbook.yml --check  # dry run

# Ad-hoc commands
ansible all -i inventory.ini -m ping
ansible webservers -i inventory.ini -m shell -a "uptime"
ansible dbservers -i inventory.ini -m service -a "name=postgresql state=restarted"

# Vault (secrets management)
ansible-vault encrypt secrets.yml
ansible-vault decrypt secrets.yml
ansible-vault edit secrets.yml
ansible-playbook playbook.yml --ask-vault-pass

# Inventory management
ansible-inventory -i inventory.ini --list
ansible-inventory -i inventory.ini --graph
```

**Configuration Management** - DevOps handles more server configuration:

**Puppet**
```bash
puppet apply manifest.pp
puppet agent --test
puppet module install puppetlabs-apache
puppet parser validate manifest.pp
```

**Chef**
```bash
chef-client
knife node list
knife cookbook upload myapp
knife ssh "role:webserver" "sudo chef-client"
```

**Infrastructure provisioning (more DevOps-focused)**
```bash
# Vagrant (local development environments)
vagrant init ubuntu/focal64
vagrant up
vagrant ssh
vagrant halt
vagrant destroy
vagrant provision

# Packer (building images)
packer build template.json
packer validate template.json
packer inspect template.json
```

**Git** - Same commands, but DevOps focuses more on branching strategies:
```bash
# All standard git commands (same as SRE)
git status
git pull origin main
git push origin main

# Branch management (DevOps manages feature branches more)
git checkout -b feature/new-feature
git checkout -b hotfix/critical-fix
git flow init  # GitFlow workflow

# Rebase workflows (keeping history clean for CI/CD)
git rebase main
git rebase -i HEAD~5  # interactive rebase

# Submodules (managing dependencies)
git submodule add <repo-url>
git submodule update --init --recursive
```

**Cloud CLI** - Similar to SRE but with more focus on resource creation:

**AWS CLI**
```bash
# All standard AWS commands (same as SRE)
aws ec2 describe-instances
aws s3 ls

# DevOps creates more infrastructure
aws ec2 run-instances --image-id ami-12345 --instance-type t2.micro --key-name mykey
aws s3 mb s3://my-new-bucket
aws iam create-user --user-name newuser
aws iam attach-user-policy --user-name newuser --policy-arn arn:aws:iam::aws:policy/ReadOnlyAccess

# CodePipeline/CodeDeploy (CI/CD focus)
aws codepipeline start-pipeline-execution --name my-pipeline
aws codedeploy create-deployment --application-name myapp --deployment-group-name production
```

**Monitoring setup** - DevOps sets up monitoring; SRE uses it more:
```bash
# Prometheus setup
prometheus --config.file=prometheus.yml
promtool check config prometheus.yml

# Grafana
grafana-cli plugins install grafana-piechart-panel
grafana-cli plugins ls

# Setting up exporters
node_exporter
blackbox_exporter
```

**Linux commands** - Same as SRE, but DevOps does more user/permission management:
```bash
# All standard monitoring commands (same as SRE)
top
htop
df -h
free -m

# User and permission management (more DevOps)
useradd -m -s /bin/bash username
usermod -aG sudo username
passwd username
chown -R user:group /var/www
chmod 755 /var/www
chmod 600 ~/.ssh/id_rsa

# Package management (more frequent in DevOps)
apt update && apt upgrade -y
apt install nginx -y
yum update -y
yum install httpd -y

# Service installation and configuration
apt install postgresql -y
systemctl enable postgresql
systemctl start postgresql
```

**Network configuration** - DevOps configures; SRE troubleshoots:
```bash
# All troubleshooting commands (same as SRE)
ping google.com
dig example.com
curl -I https://example.com

# Network configuration (more DevOps)
ip addr add 192.168.1.10/24 dev eth0
ip route add default via 192.168.1.1
iptables -A INPUT -p tcp --dport 80 -j ACCEPT
iptables-save > /etc/iptables/rules.v4

# Load balancer configuration
nginx -t  # test config
nginx -s reload
systemctl reload nginx
```

**SSL/TLS certificate management** - More DevOps than SRE:
```bash
# Let's Encrypt
certbot --nginx -d example.com
certbot renew
certbot certificates

# OpenSSL
openssl req -new -newkey rsa:2048 -nodes -keyout server.key -out server.csr
openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt
openssl x509 -in server.crt -text -noout
```

**Database setup and migration** - DevOps handles initial setup more:
```bash
# PostgreSQL setup
sudo -u postgres createdb myapp_production
sudo -u postgres createuser myapp_user
sudo -u postgres psql -c "ALTER USER myapp_user WITH PASSWORD 'password';"

# MySQL setup
mysql -u root -p -e "CREATE DATABASE myapp_production;"
mysql -u root -p -e "CREATE USER 'myapp_user'@'localhost' IDENTIFIED BY 'password';"
mysql -u root -p -e "GRANT ALL PRIVILEGES ON myapp_production.* TO 'myapp_user'@'localhost';"

# Database migrations (application deployment)
rails db:migrate RAILS_ENV=production
alembic upgrade head
flyway migrate
```

**Backup and restore** - More DevOps responsibility:
```bash
# Database backups
pg_dump -U postgres myapp_production > backup_$(date +%Y%m%d).sql
mysqldump -u root -p myapp_production > backup_$(date +%Y%m%d).sql

# File backups
tar -czf backup_$(date +%Y%m%d).tar.gz /var/www/
rsync -avz /var/www/ backup-server:/backups/www/

# Restore
psql -U postgres myapp_production < backup_20241220.sql
mysql -u root -p myapp_production < backup_20241220.sql
```

**Container registries** - DevOps manages more:
```bash
# Docker Registry
docker run -d -p 5000:5000 --name registry registry:2
docker tag myapp localhost:5000/myapp
docker push localhost:5000/myapp

# Harbor, Artifactory, etc.
docker login harbor.example.com
docker push harbor.example.com/project/myapp:v1.0
```

**KEY DIFFERENCES SUMMARY:**

**DevOps focuses on:**
1. **Building and deploying** - More time writing Dockerfiles, CI/CD pipelines, deployment scripts
2. **Infrastructure provisioning** - Creating new servers, networks, databases from scratch
3. **Automation of workflows** - Automating entire software delivery pipeline
4. **Configuration management** - Managing server configurations across environments
5. **Developer enablement** - Creating tools and platforms for developers to deploy their own code

**SRE focuses on:**
1. **Monitoring and observability** - Deep analysis of metrics, logs, traces
2. **Incident response** - Troubleshooting production issues, postmortems
3. **Reliability engineering** - SLIs, SLOs, error budgets, chaos engineering
4. **Performance optimization** - Finding bottlenecks, capacity planning
5. **Toil reduction** - Eliminating repetitive operational work

**Command usage differences:**
- **DevOps**: More `docker build/push`, `terraform apply`, `ansible-playbook`, pipeline configurations
- **SRE**: More `kubectl logs/describe`, PromQL queries, `perf`, `strace`, performance analysis

**Time allocation:**
- **DevOps**: 60% building/deploying, 40% maintaining/troubleshooting
- **SRE**: 40% engineering (automation), 30% monitoring/analysis, 30% incident response

Both roles share many tools and commands, but the **intent and frequency** differ significantly. DevOps engineers are builders and enablers of the deployment pipeline, while SREs are guardians and optimizers of production reliability.
