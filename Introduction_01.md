# 802.1X #

IEEE802 LAN/WAN委员会为解决无线局域网网络安全问题，提出了802.1X协议。后来，802.1X协议作为局域网端口的一个普通接入控制机制在以太网中被广泛应用，主要解决以太网内认证和安全方面的问题。

802.1X协议是一种基于端口的网络接入控制协议（port based network access control protocol）。“基于端口的网络接入控制”是指在局域网接入设备的端口这一级对所接入的用户设备进行认证和控制。连接在端口上的用户设备如果能通过认证，就可以访问局域网中的资源；如果不能通过认证，则无法访问局域网中的资源。

# 802.1X的认证方式 #

802.1X认证系统使用EAP（Extensible Authentication Protocol，可扩展认证协议），来实现客户端、设备端和认证服务器之间认证信息的交换。

l              在客户端与设备端之间，EAP协议报文使用EAPOL封装格式，直接承载于LAN环境中。

l              在设备端与RADIUS服务器之间，可以使用两种方式来交换信息。一种是EAP协议报文由设备端进行中继，使用EAPOR（EAP over RADIUS）封装格式承载于RADIUS协议中；另一种是EAP协议报文由设备端进行终结，采用包含PAP（Password Authentication Protocol，密码验证协议）或CHAP（Challenge Handshake Authentication Protocal，质询握手验证协议）属性的报文与RADIUS服务器进行认证交互。

认证过程如下：

(1)        当用户有访问网络需求时打开802.1X客户端程序，输入已经申请、登记过的用户名和密码，发起连接请求（EAPOL-Start报文）。此时，客户端程序将发出请求认证的报文给设备端，开始启动一次认证过程。

(2)        设备端收到请求认证的数据帧后，将发出一个请求帧（EAP-Request/Identity报文）要求用户的客户端程序发送输入的用户名。

(3)        客户端程序响应设备端发出的请求，将用户名信息通过数据帧（EAP-Response/Identity报文）发送给设备端。设备端将客户端发送的数据帧经过封包处理后（RADIUS Access-Request报文）送给认证服务器进行处理。

(4)        RADIUS服务器收到设备端转发的用户名信息后，将该信息与数据库中的用户名表对比，找到该用户名对应的密码信息，用随机生成的一个加密字对它进行加密处理，同时也将此加密字通过RADIUS Access-Challenge报文发送给设备端，由设备端转发给客户端程序。

(5)        客户端程序收到由设备端传来的加密字（EAP-Request/MD5 Challenge报文）后，用该加密字对密码部分进行加密处理（此种加密算法通常是不可逆的），生成EAP-Response/MD5 Challenge报文，并通过设备端传给认证服务器。

(6)        RADIUS服务器将收到的已加密的密码信息（RADIUS Access-Request报文）和本地经过加密运算后的密码信息进行对比，如果相同，则认为该用户为合法用户，反馈认证通过的消息（RADIUS Access-Accept报文和EAP-Success报文）。

(7)        设备收到认证通过消息后将端口改为授权状态，允许用户通过端口访问网络。在此期间，设备端会通过向客户端定期发送握手报文的方法，对用户的在线情况进行监测。缺省情况下，两次握手请求报文都得不到客户端应答，设备端就会让用户下线，防止用户因为异常原因下线而设备无法感知。

(8)        客户端也可以发送EAPOL-Logoff报文给设备端，主动要求下线。设备端把端口状态从授权状态改变成未授权状态，并向客户端发送EAP-Failure报文。