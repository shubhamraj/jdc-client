# Introduction #

<h1>1.EAPOL 消息的封装</h1>

<h2>POL 数据包的格式</h2>

EAPOL是802.1X协议定义的一种报文封装格式，主要用于在客户端和设备端之间传送EAP协议报
文，以允许EAP协议报文在LAN上传送。EAPOL数据包的格式如图


> <h3>EAPOL 数据包格式</h3>

> <table width='500' border='1'>
<blockquote><tr>
<blockquote><th>Ehernet Type</th></blockquote></blockquote></li></ul>

> </tr>
<blockquote><tr>
<blockquote><th align='center'>Protocol Version </th>
<th align='center'>Type</th>
</blockquote></tr>
> <tr>
<blockquote><th align='center'> Length</th>
</blockquote></tr>
> <tr>
<blockquote><th align='center'> Packet Body</th>
</blockquote></tr>

</table>

Ethernet Type：表示协议类型，承载于EthernetII，类型值为0x888e。(2 bytes)<br>
Protocol Version：表示EAPOL帧的发送方所支持的协议版本号(1 byte)。<br>
Type：表示EAPOL数据帧类型(1 byte)。<br>
<h4>Type类型</h4>

<table width='500' border='1'>
<tr>
<blockquote><th>类型</th>
<th width='250'>说明</th>
</blockquote></tr>
<tr>
<blockquote><th align='center'>EAP-Packet（值为0x00）：认证信息帧，用于承载认证信息 </th>
<th align='center'>该帧在设备端重新封装并承载于 RADIUS 协议上，便于穿越复杂的网络到达认证服务器</th>
</blockquote></tr>
<tr>
<blockquote><th align='center'> EAPOL-Start（值为0x01）：认证发起帧</th>
<th align='center'> 这两种类型的帧仅在客户端和设备端之间存在，通常Length值为0，后面没有数据域</th>
</blockquote></tr>
<tr>
<blockquote><th align='center'> EAPOL-Logoff（值为0x02）：退出请求帧</th>
<th align='center'> </th>
</blockquote></tr></blockquote>

</table>


<h3>说明：</h3>该帧在设备端重新封装并承载于 RADIUS 协议上，便于穿越复杂的网络到达认证服务器<br>
<br>
<blockquote>i.  EAP-Packet（值为0x00）：认证信息帧，用于承载认证信息<br>
ii. EAPOL-Start（值为0x01）：认证发起帧<br>
iii. EAPOL-Logoff（值为0x02）：退出请求帧<br></blockquote>

Length：表示数据长度，也就是“Packet Body”字段的长度，单位为字节。如果为0，则表示没<br>
有后面的数据域。<br>
Packet Body：表示数据内容，根据不同的Type 有不同的格式。<br>
<br>
<h1>2. EAP 数据包的格式</h1>
当EAPOL数据包格式Type域为EAP-Packet时，Packet Body为EAP数据包结构.<br>
<br>
<br>
<table width='500' border='1'>
<blockquote><tr>
<blockquote><th>Code</th>
<th width='250'>Identifier</th>
</blockquote></tr>
<tr>
<blockquote><th align='center'>Length </th></blockquote></blockquote>

<blockquote></tr>
<tr>
<blockquote><th align='center'> Date</th>
</tr></blockquote></blockquote>


</table>

Code：指明EAP 包的类型，共有4 种：Request、Response、Success、Failure。(1 byte)<br>
1.Success 和Failure 类型的包没有Data 域，相应的Length 域的值为4。<br>
2.Request和Response类型数据包的Data域的格式所示。Type为EAP的认证类型，Typedata的内容由类型决定。例如，Type值为1 时代表Identity，用来查询对方的身份；Type值为4 时，代表MD5-Challenge，类似于PPP CHAP协议，包含质询消息。<br><br>
Request 和Response 类型数据包的Data 域的格式:<br>
<blockquote>Identifier：用于匹配Request 消息和Response 消息。<br>
Length：EAP 包的长度，包含Code、Identifier、Length 和Data 域，，由Code 类型决定,单位为字节。<b>(注意这里的长度是不包含EAPOL的包头，即：不包含EAPOL的version，type，length<br>
Data：EAP 包的内容。)</b><br>
<br>
<br>
<b>EAP 属性的封装</b></blockquote>

RADIUS 为支持EAP 认证增加了两个属性：EAP-Message（EAP 消息）和Message-Authenticator<br>
（消息认证码）。<br>
<br>
<b>1. EAP-Message</b>

这个属性用来封装EAP数据包，类型代码为79，String域最长253 字节，如果EAP数据包长度大于253 字节，可以对其进行分片，依次封装在多个EAP-Message属性中。<br>
<br>
<b>EAP-Message 属性封装</b>

<table width='500' border='1'>
<blockquote><tr>
<blockquote><th>Type</th>
<th>Length</th>
<th>String(封装了EAP 数据包)</th>
</blockquote></tr>
</table></blockquote>


<b>2. Message-Authenticator</b>

这个属性用于在使用EAP、CHAP等认证方法的过程中，避免接入请求包被窃听。在<br>
含有EAP-Message属性的数据包中，必须同时也包含Message-Authenticator，否则该数据包会被<br>
认为无效而被丢弃。<br>
<br>
<b>Message-Authenticator 属性<br>
<blockquote></b><table width='500' border='1'>
<blockquote><tr>
<blockquote><th>Type</th>
<th>Length</th>
<th>String</th>
</blockquote></tr>
</table></blockquote></blockquote>

