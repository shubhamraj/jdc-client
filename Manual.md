# 前提要求 #

因为整合了jpcap，但是jpcap依赖于pcap，所以需要先安装:<br><br>
<b>Windows用户</b>：winpcap(4.1.1版本或更高)<br>
<blockquote>下载地址：<a href='http://www.winpcap.org/install/default.htm'>http://www.winpcap.org/install/default.htm</a><em>或直接从dependencies文件夹里找到并安装.</em><br><br><br>
<b>Linux用户</b>：libpcap(1.1.1版本或更高)<br>
下载地址：<a href='http://www.tcpdump.org/'>http://www.tcpdump.org/</a><br>
<b>(小提示：libpcap需要用gcc编译器来make编译安装，如果闲麻烦或不会，可以直接将bin目录下面已经编译好的动静态链接库复制到/usr/lib/下)</b><br>
</blockquote><blockquote><b>要复制的文件列表如下：</b><br>
libjpcap.so<br>
libpcap.a<br>
libpcap.so<br>
libpcap.so.1<br>
libpcap.so.1.1.1<br>
<br><br>
<h1>使用方法</h1></blockquote>

<h4>建议在Java虚拟机版本1.6或更高下运行：</h4><br>
<b>Windows用户：</b><br>
<blockquote>打开命令提示符cmd,cd 到JDC-Client.jar文件目录下：<br>
执行java -jar JDC-Client.jar<br>
根据运行提示信息首先输入网卡号  <b>(注：您的电脑可能有多个网卡，选择要您使用的网卡，确    保输入的一定是数字)</b><br>
根据提示输入认证帐号<br>
根据提示输入认证密码<br>
认证成功或失败<br>
<br>
<b>Linux用户：</b><br>
打开终端,cd 到JDC-Client.jar文件目录下：<br>
执行java -jar JDC-Client.jar<br>
根据运行提示信息首先输入网卡号  <b>(注：您的电脑可能有多个网卡，选择要您使用的网卡，确保输入的一定是数字)</b><br>
根据提示输入认证帐号<br>
根据提示输入认证密码<br>
认证成功或失败<br>