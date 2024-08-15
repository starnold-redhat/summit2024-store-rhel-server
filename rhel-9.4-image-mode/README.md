# RHEL9.4-image 


#getting started

There are a number of build artefacts in this repo

The embed script to build a customised RHEL9.4 boot image that includes a custom kickstart file. 
The kickstart file holds the quay auth information and copies it to /etc/ostree/auth.json in the installer ramdisk at the start of installation. ostreecontainer kickstart directive can then pull the lamp-bootc example image from quay and install it. 

The containerfile to build the custom rhel-bootc image that you push to your quay private repo

the build process for the RHEL9.4 image is: 

1. build a system that can be used as the build host
2. install podman and lorax-composer (mkkiso is used to inject the ks.cfg into the rhel-9.4 boot image. This allows a user to either install a vm automatically using kickstart by binding the image to the cd on boot or a baremetal system by using dd to write a usb drive and booting the baremetal system from that (requires suitable dhcp network server or additional information in the network section of the kickstart file defining a static address if wired
3. use the containerfile and podman to build a suitable custom rhel-9.4 bootc image based on the Red Hat image. This requires loging in to redhat.registry.io using podman login 
4. Once the image has been built push it to your quay repo 
5. edit the config file changing ostreecontainer to reflect your container image in quay thsi WILL need a robot account to be created in the quay repo and the auth info to be copied to the kickstart pre section to be used to populate /etc/ostree/auth.json as outlined above. 
6. build the system: vm or baremetal using the custom boot image 

additional: The embed bootable iso helper script that will require tweaking (I scp the image back to my host system to enable it to be bound to a new vm to test) that builds the custom boot image injecting the ks.cfg file using mkkiso. 
