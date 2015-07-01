# 介绍 #

这家伙很懒，还是看看源代码，再抓抓包看看吧。

@see Auth.java

else if(receivedPacket.data[4](4.md) == EAPPacket.REQUEST && receivedPacket.data[8](8.md) == EAPPacket.HANDSHAKE\_TYPE) {
> for(int i = 0; i < 4; i++) {
> > handshakeKeys[i](i.md) = receivedPacket.data[i+9];

> }
> handshakePacket.fillEncryptedHandshake(receivedPacket.data[5](5.md), username, handshakeKeys);
> sender.sendPacket(handshakePacket);