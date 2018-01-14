# JavaEE | AWS
My exercises with Amazon Web Services

#### Sep 14, 2017
I successfully registered in the Amazon Web Services in the limits of the Free Tier. A conditional $1 was withdrawn from my Yandex card, which was returned a month later.

#### Jan 09, 2017
I have launched a Linux Virtual Machine following steps on link https://aws.amazon.com/getting-started/tutorials/launch-a-virtual-machine. Unfortunately step 3 (Connect to your Instance) was fail. Connection to the Instance via ssh didn't happen. Moreover, I couldn't ping IP address of my Instance. I wrote to the support.

#### Jan 10, 2017
The answer from the support didn't help: "here in Basic Support we handle Billing and Account inquiries, and our area of expertise does not include technical support". However, my colleague, who had experience with servers AWS, suggested a solution. I configured the settings in the Security Groups column: I chose the Inbound tab and allowed all traffic.

I connected to my Instance/Server via ssh and and remembered the skills Linux command line:
cat /proc/version
sudo yum install java-1.8.0-openjdk-devel
sudo update-alternatives --config java