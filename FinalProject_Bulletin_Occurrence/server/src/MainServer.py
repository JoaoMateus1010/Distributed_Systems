import socket

class SocketServer:
    def __init__(self,serverPort=2000,serverHost="127.0.0.1"):
        self.PortServer = serverPort
        self.HostServer = serverHost
        self.socket = socket.socket()
        self.socket.bind((self.HostServer,self.PortServer))
        self.socket.listen(5)
        pass

    def read(self,conn):
        msg=str()
        length_of_message = int.from_bytes(conn.recv(2), byteorder='big')
        msg = conn.recv(length_of_message).decode("UTF-8")
        return msg

    def send(self,msg,conn):
        conn.send(len(msg.encode("UTF-8")).to_bytes(2, byteorder='big'))
        conn.send(msg.encode("UTF-8"))

sc=SocketServer(serverPort=2002,serverHost="127.0.0.1")
(conn,addr)=sc.socket.accept()
print("Cliente -> ",addr)
print(sc.read(conn=conn))
ms = "Resposta do server"
sc.send(msg=ms,conn=conn)
conn.close()


