provider "aws" {

    access_key = "${var.aws_access_key}"
    secret_key = "${var.aws_secret_key}"
    region = "${var.region}"

}

module "s3" {

    source = "<path-to-S3-folder>"
    bucket_name = "your_bucket_name"       

}

resource "aws_s3_bucket" "temps3" {

    bucket = "${var.bucket_name}" 
    acl = "${var.acl_value}"   

}