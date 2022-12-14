eksctl create cluster -f eks-cluster.yaml --kubeconfig=C:\Users\{user}\.kube\config

# https://docs.aws.amazon.com/eks/latest/userguide/service_IAM_role.html#create-service-role
# iam role for worker node
# node in public or private subnet

aws eks update-kubeconfig --region eu-west-2 --name movie-eks-cluster

aws eks update-kubeconfig --name movie-eks-cluster --region eu-west-2 --role-arn arn:aws:iam::095182200769:user/cli  
kubectl config view --minify

aws eks create-cluster --region eu-west-2 --name movie-eks-cluster --kubernetes-version 1.24 --role-arn arn:aws:iam::095182200769:user/cli --resources-vpc-config subnetIds=subnet-065e4eb2044b9e6b6,subnet-07d0f36d0d83e6d84,securityGroupIds=sg-0b724afb2a6a6e6cc 

aws eks describe-cluster --region eu-west-2 --name movie-eks-cluster --query "cluster.status"

eksctl create nodegroup --cluster movie-eks-cluster --region eu-west-2 --name movie-node-group --node-type t3.micro --nodes 2 --nodes-min 2 --nodes-max 2

