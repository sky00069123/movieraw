aws ecr create-repository --repository-name movie --image-scanning-configuration scanOnPush=true --region eu-west-2

aws configure

aws ecr get-login-password --region eu-west-2 | docker login --username AWS --password-stdin 095182200769.dkr.ecr.eu-west-2.amazonaws.com

#docker build -t movie .

#docker tag movie:latest 095182200769.dkr.ecr.eu-west-2.amazonaws.com/movie:latest

#docker push 095182200769.dkr.ecr.eu-west-2.amazonaws.com/movie:latest
